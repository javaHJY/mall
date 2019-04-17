package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import dao.GoodsDao;
import entity.Cart;
import entity.Goods;
import service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cDao;
	@Autowired
	private GoodsDao gDao;
	
	public boolean addGoods(Goods g,int num) {
		int rs=0;
		Goods goods=cDao.searchGoodsById(g.getId());
		if(goods==null) {
			Goods goods2=gDao.searchById(g.getId());
			rs=cDao.addGoods(goods2,num);
			gDao.reduceStock(goods2,num);
		}else {
			rs=cDao.increaseGoods(goods,num);
			gDao.reduceStock(goods,num);
		}
		return rs>0;
	}

	public boolean increaseGoods(int gId, int num) {
		Goods g=gDao.searchById(gId);
		int rs=0;
		rs=cDao.increaseGoods(g, num);
		rs=gDao.reduceStock(g, num);
		return rs>0;
	}
	
	public boolean decreaseGoods(int gId, int num) {
		Goods g=gDao.searchById(gId);
		int rs=0;
		rs=cDao.decreaseGoods(g, num);
		rs=gDao.restoreGoods(g, num);
		return rs>0;
	}
	
	public boolean deleteGoods(Goods g,int num) {
		int rs=cDao.deleteGoods(g);
		Goods goods=cDao.searchGoodsById(g.getId());
		gDao.restoreGoods(goods,num);
		return rs>0;
	}
	
	public Cart searchAll() {
		Cart cart=cDao.searchAll();
		return cart;
	}

}
