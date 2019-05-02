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
	
	//购物车中商品改变
	public Cart updateCart(Cart cart) {
		Goods goods=gDao.searchById(cart.getGoods().getId());
		double price=goods.getPrice();
		int count=cart.getCount();
		Cart searchCart=cDao.searchByCart(cart);
		int prevCount=searchCart.getCount();
		//改变购物车信息
		searchCart.setCount(count);
		searchCart.setL_amount(count*price);
		cDao.updateCart(searchCart);
		//改变商品信息
		goods.setStock(goods.getStock()+prevCount-count);
		goods.setSaleNum(goods.getSaleNum()-prevCount+count);
		gDao.updateStock(goods);
		//改变用户总计
		User user=cart.getUser();
		double t_amount=user.getT_amount()-prevCount*price+price*count;
		user.setT_amount(t_amount);
		userDao.updateT_amount(user);
		return searchCart;
	}

	//购物车中商品删除
	public boolean deleteCart(int id) {
		int rs=0;
		Cart cart=cDao.searchById(id);
		//删除购物车表中的信息
		rs=cDao.deleteCart(id);
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
