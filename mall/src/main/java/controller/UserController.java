package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;
import util.UserException;
import util.VerifyCode;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//跳转至登录页面
	@RequestMapping("goLogin")
	public String goLogin() {
		return "user/login";
	}

	//跳转至注册页面
	@RequestMapping("goRegist")
	public String goRegist() {
		return "user/regist";
	}
	
	//用户登录
	@RequestMapping("login")
	public ModelAndView login(User user,String verifycode,HttpServletRequest request) {
		ModelAndView mav=null;
		HttpSession session=request.getSession();
		try {
			if(!((String)(session.getAttribute("verifycode"))).equalsIgnoreCase(verifycode)) {
				throw new UserException("验证码不一致");
			}
			User searchUser=userService.login(user);
			mav=new ModelAndView("user/user");
			session.setAttribute("user", searchUser);
		}catch(UserException e){
			mav=new ModelAndView("user/login");
			mav.addObject("user", user);
			mav.addObject("error", e.getMessage());
		}
		return mav;
	}
	
	//用户注册
	@RequestMapping("regist")
	public ModelAndView regist(User user,String verifycode,HttpServletRequest request) {
		ModelAndView mav=null;
		HttpSession session=request.getSession();
		try {
			if(!((String)(session.getAttribute("verifycode"))).equalsIgnoreCase(verifycode)) {
				throw new UserException("验证码不一致");
			}
			userService.regist(user);
			mav=new ModelAndView("user/login");
			session.setAttribute("user", user);
		}catch(UserException e) {
			mav=new ModelAndView("user/regist");
			mav.addObject("user", user);
			mav.addObject("error", e.getMessage());
		}
		return mav;
	}
	
	//生成验证码
	@RequestMapping("createVerifyCode")
	public void createVerify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		VerifyCode vc=VerifyCode.getInstance();
		BufferedImage image=vc.getImage();
		vc.output(image, response.getOutputStream());
		request.getSession().setAttribute("verifycode", vc.getText());
	}
	
	//忘记密码
}
