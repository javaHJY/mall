package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import dao.GoodsDao;
import dao.UserDao;
import entity.Cart;
import entity.Goods;
import entity.User;
import service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cDao;
	@Autowired
	private GoodsDao gDao;
	@Autowired
	private UserDao userDao;
	
	//详情页商品添加到购物车
	public boolean addGoods(Cart cart) {
		int rs=0;
		Cart searchCart=cDao.searchByCart(cart);
		Goods g=gDao.searchById(cart.getGoods().getId());
		double price=g.getPrice();
		if(searchCart==null) {
			//添加商品
			double l_amount=price*cart.getCount();
			cart.setL_amount(l_amount);
			rs=cDao.addGoods(cart);
			//对应商品修改库存和销售量
			g.setStock(g.getStock()-cart.getCount());
			g.setSaleNum(g.getSaleNum()+cart.getCount());
			rs=gDao.updateStock(g);
			//用户总计改变
			User user=cart.getUser();
			double t_amount=user.getT_amount();
			user.setT_amount(t_amount+l_amount);
			rs=userDao.updateT_amount(user);
		}else {
			//对应商品修改库存和销售量
			searchCart.setCount(searchCart.getCount()+cart.getCount());
			double l_amount=searchCart.getL_amount()+price*cart.getCount();
			searchCart.setL_amount(l_amount);
			rs=cDao.updateCart(searchCart);
			//对应商品修改库存和销售量
			g.setStock(g.getStock()-cart.getCount());
			g.setSaleNum(g.getSaleNum()+cart.getCount());
			rs=gDao.updateStock(g);
			//用户总计改变
			User user=cart.getUser();
			double t_amount=user.getT_amount();
			user.setT_amount(t_amount+price*cart.getCount());
			rs=userDao.updateT_amount(user);
		}
		return rs>0;
	}

	//购物车中商品增加
	public boolean increaseGoods(int gId, int num) {
		Goods g=gDao.searchById(gId);
		int rs=0;
		rs=cDao.increaseGoods(g, num);
		rs=gDao.reduceStock(g, num);
		return rs>0;
	}
	
	//购物车中商品减少
	public boolean decreaseGoods(int gId, int num) {
		Goods g=gDao.searchById(gId);
		int rs=0;
		rs=cDao.decreaseGoods(g, num);
		rs=gDao.restoreGoods(g, num);
		return rs>0;
	}
	
	//购物车中商品删除
	public boolean deleteGoods(Goods g,int num) {
		int rs=cDao.deleteGoods(g);
		Goods goods=cDao.searchByCart(g.getId());
		gDao.restoreGoods(goods,num);
		return rs>0;
	}
	
	//查询所有购物车商品
	public Cart searchAll() {
		Cart cart=cDao.searchAll();
		return cart;
	}

}
