package service;

import entity.Cart;
import entity.Goods;

public interface CartService {

	boolean addGoods(Goods g,int num);
	boolean deleteGoods(Goods g,int num);
	Cart searchAll();
}
