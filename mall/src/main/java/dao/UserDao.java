package dao;

import entity.User;

public interface UserDao {

	User searchById(int id);//根据id查找用户
}
