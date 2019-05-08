package service;

import entity.User;

public interface UserService {

	User login(User user);//用户登录

	boolean regist(User user);//用户注册

}
