package service;

import entity.Cart;
import entity.Goods;

public interface CartService {

	boolean addGoods(Cart cart);//详情页商品添加到购物车
	boolean increaseGoods(int gId, int num);//购物车中商品增加
	boolean deleteGoods(Goods g,int num);//购物车中商品减少
	boolean decreaseGoods(int gId, int num);//购物车中商品删除
	Cart searchAll();//查询所有购物车商品
	
	
}
