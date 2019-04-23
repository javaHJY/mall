package service.impl;

import java.util.List;

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
		int count=cart.getCount();
		if(searchCart==null) {
			//添加商品
			double l_amount=price*count;
			cart.setL_amount(l_amount);
			rs=cDao.addGoods(cart);
			//对应商品修改库存和销售量
			g.setStock(g.getStock()-count);
			g.setSaleNum(g.getSaleNum()+count);
			rs=gDao.updateStock(g);
			//用户总计改变
			User user=cart.getUser();
			double t_amount=user.getT_amount();
			user.setT_amount(t_amount+l_amount);
			rs=userDao.updateT_amount(user);
		}else {
			//对应商品修改库存和销售量
			searchCart.setCount(searchCart.getCount()+count);
			double l_amount=searchCart.getL_amount()+price*count;
			searchCart.setL_amount(l_amount);
			rs=cDao.updateCart(searchCart);
			//对应商品修改库存和销售量
			g.setStock(g.getStock()-count);
			g.setSaleNum(g.getSaleNum()+count);
			rs=gDao.updateStock(g);
			//用户总计改变
			User user=cart.getUser();
			double t_amount=user.getT_amount();
			user.setT_amount(t_amount+price*count);
			rs=userDao.updateT_amount(user);
		}
		return rs>0;
	}

	//购物车中商品增加
	public boolean increaseGoods(Cart cart) {
		int rs=0;
		Goods goods=gDao.searchById(cart.getGoods().getId());
		double price=goods.getPrice();
		int count=cart.getCount();
		//改变购物车信息
		Cart searchCart=cDao.searchByCart(cart);
		searchCart.setCount(searchCart.getCount()+count);
		searchCart.setL_amount(searchCart.getL_amount()+count*price);
		rs=cDao.updateCart(searchCart);
		//改变商品信息
		goods.setStock(goods.getStock()-count);
		goods.setSaleNum(goods.getSaleNum()+count);
		rs=gDao.updateStock(goods);
		//改变用户总计
		User user=cart.getUser();
		double t_amount=user.getT_amount()+price*count;
		user.setT_amount(t_amount);
		userDao.updateT_amount(user);
		return rs>0;
	}
	
	//购物车中商品减少
	public boolean decreaseGoods(Cart cart) {
		int rs=0;
		Goods goods=gDao.searchById(cart.getGoods().getId());
		double price=goods.getPrice();
		int count=cart.getCount();
		//改变购物车信息
		Cart searchCart=cDao.searchByCart(cart);
		searchCart.setCount(searchCart.getCount()-count);
		searchCart.setL_amount(searchCart.getL_amount()-count*price);
		rs=cDao.updateCart(cart);
		//改变商品信息
		goods.setStock(goods.getStock()+count);
		goods.setSaleNum(goods.getSaleNum()-count);
		rs=gDao.updateStock(goods);
		//改变用户总计
		User user=cart.getUser();
		user.setT_amount(user.getT_amount()-price*count);
		userDao.updateT_amount(user);
		return rs>0;
	}
	
	//购物车中商品删除
	public boolean deleteGoods(Cart cart) {
		int rs=0;
		//删除购物车表中的信息
		rs=cDao.deleteGoods(cart);
		//改变商品表数据
		Goods goods=gDao.searchById(cart.getGoods().getId());
		goods.setStock(goods.getStock()+cart.getCount());
		goods.setSaleNum(goods.getSaleNum()-cart.getCount());
		rs=gDao.updateStock(goods);
		//改变用户总计
		User user=cart.getUser();
		user.setT_amount(user.getT_amount()-goods.getPrice()*cart.getCount());
		rs=userDao.updateT_amount(user);
		return rs>0;
	}
	
	//查询所有购物车商品
	public List<Cart> searchAllByUser(User user) {
		List<Cart> cartList=cDao.searchAllByUser(user);
		return cartList;
	}
	
	//根据购物车查询购物车
	public Cart searchByCart(Cart cart) {
		Cart c=cDao.searchByCart(cart);
		return c;
	}

}
