package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import dao.GoodsDao;
import entity.Cart;
import entity.Goods;
import entity.User;
import service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private GoodsDao goodsDao;
	
	//详情页商品添加到购物车
	public boolean addGoods(Cart cart) {
		int rs=0;
		Cart searchCart=cartDao.searchByCart(cart);
		Goods g=goodsDao.searchById(cart.getGoods().getId());
		double price=g.getPrice();
		int count=cart.getCount();
		if(searchCart==null) {
			//添加购物车信息
			double l_amount=price*count;
			cart.setL_amount(l_amount);
			rs=cartDao.addGoods(cart);
		}else {
			//修改购物车信息
			searchCart.setCount(searchCart.getCount()+count);
			double l_amount=searchCart.getL_amount()+price*count;
			searchCart.setL_amount(l_amount);
			rs=cartDao.updateCart(searchCart);
		}
		return rs>0;
	}
	
	//购物车中商品改变
	public Cart updateCart(Cart cart) {
		Goods goods=goodsDao.searchById(cart.getGoods().getId());
		double price=goods.getPrice();
		int count=cart.getCount();
		Cart searchCart=cartDao.searchByCart(cart);
		//改变购物车信息
		searchCart.setCount(count);
		searchCart.setL_amount(count*price);
		cartDao.updateCart(searchCart);
		return searchCart;
	}

	//购物车中商品删除
	public boolean deleteCart(int id) {
		int rs=cartDao.deleteCart(id);
		return rs>0;
	}
	
	//查询所有购物车商品
	public List<Cart> searchAllByUser(User user) {
		List<Cart> cartList=cartDao.searchAllByUser(user);
		return cartList;
	}
}
