package service;

import java.util.List;

import entity.Cart;
import entity.User;

public interface CartService {

	boolean addGoods(Cart cart);//详情页商品添加到购物车
	Cart updateCart(Cart cart);//购物车中商品改变
	boolean deleteCart(int id);//购物车中商品删除
	List<Cart> searchAllByUser(User user);//根据用户id查询所有购物车商品
}
