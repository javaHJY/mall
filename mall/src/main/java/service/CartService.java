package service;

import java.util.List;

import entity.Cart;
import entity.User;

public interface CartService {

	boolean addGoods(Cart cart);//详情页商品添加到购物车
	boolean increaseGoods(Cart cart);//购物车中商品增加
	boolean decreaseGoods(Cart cart);//购物车中商品减少
	boolean deleteGoods(Cart cart);//购物车中商品删除
	List<Cart> searchAllByUser(User user);//根据用户id查询所有购物车商品
	Cart searchByCart(Cart cart);//根据商品id查询购物车中该商品信息
	
}
