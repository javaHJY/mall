package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;
import util.UserException;

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
	public ModelAndView login(User user,HttpServletRequest request) {
		ModelAndView mav=null;
		HttpSession session=request.getSession();
		try {
			User searchUser=userService.login(user);
			mav=new ModelAndView();
			session.setAttribute("user", searchUser);
		}catch(UserException e){
			mav=new ModelAndView();
			mav.addObject("user", user);
			mav.addObject("error", e.getMessage());
		}
		return mav;
	}
	
	//用户注册
	@RequestMapping("regist")
	public ModelAndView regist(User user,HttpServletRequest request) {
		ModelAndView mav=null;
		HttpSession session=request.getSession();
		try {
			userService.regist(user);
			mav=new ModelAndView("login");
			session.setAttribute("user", user);
		}catch(UserException e) {
			mav=new ModelAndView("regist");
			mav.addObject("user", user);
			mav.addObject("error", e.getMessage());
		}
		return mav;
	}
	
	//忘记密码
}
