package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;
import util.Encript;
import util.UserException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	//用户登录
	public User login(User user)throws UserException {
		String username=user.getUsername();
		String password=user.getPassword();
		if(null==username||"".equals(username)) {
			throw new UserException("用户名不能为空");
		}
		if(null==password||"".equals(password)) {
			throw new UserException("密码不能为空");
		}
		User searchUser=userDao.searchByUsername(user.getUsername());
		if(searchUser!=null) {
			if(!searchUser.getPassword().equals(Encript.getMd5(password))) {
				throw new UserException("密码不正确");
			}
		}else {
			throw new UserException("该用户不存在");
		}
		return searchUser;
	}

	//用户注册
	public boolean regist(User user) {
		int rs=0;
		String username=user.getUsername();
		String password=user.getPassword();
		if(null==username||"".equals(username)) {
			throw new UserException("用户名不能为空");
		}
		if(username.length()<2||username.length()>6) {
			throw new UserException("用户名长度不能小于2且不能大于6");
		}
		if(null==password||"".equals(password)) {
			throw new UserException("密码不能为空");
		}
		if(password.length()<3||password.length()>16) {
			throw new UserException("密码长度不能小于3且不能大于16");
		}
		User searchUser=userDao.searchByUsername(username);
		if(searchUser!=null) {
			throw new UserException("该用户已存在");
		}else {
			user.setPassword(Encript.getMd5(user.getPassword()));
			rs=userDao.addUser(user);
		}
		return rs>0;
	}
	
//	//判断用户是否合法
//	private void judge(User user) {
//		
//	}
}
