package service;

import entity.Cart;
import entity.Goods;

public interface CartService {

	boolean addGoods(Goods g,int num);
	boolean increaseGoods(int gId, int num);
	boolean deleteGoods(Goods g,int num);
	boolean decreaseGoods(int gId, int num);
	Cart searchAll();
	
	
}
