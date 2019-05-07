package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Cart;
import entity.Goods;
import entity.Order;
import entity.OrderDetail;
import entity.User;
import service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailDao odDao;

	//生成订单
	public List<OrderDetail> generateOrder(User user, Integer[] cartIds) {
		Order order=new Order();
		String no="";
		String status="尚未确认";
		double totalAmount=0;
		//生成订单号
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		no=sdf.format(date)+user.getId();
		//计算订单金额，生成订单详情
		int len=cartIds.length;
		List<OrderDetail> odList=new ArrayList<>();
		for(int i=0;i<len;i++) {
			Cart cart=cartDao.searchById(cartIds[i]);
			totalAmount+=cart.getL_amount();
			OrderDetail od=new OrderDetail();
			od.setPrice(cart.getGoods().getPrice());
			od.setCount(cart.getCount());
			od.setlAmount(cart.getL_amount());
			od.setGoods(cart.getGoods());
			odList.add(od);
		}
		order.setNo(no);
		order.setStatus(status);
		order.setUser(user);
		order.setTotalAmount(totalAmount);
		orderDao.addOrder(order);
		//生成订单详情
		generateOrderDetail(order,odList);
		int odSize=odList.size();
		for(int i=0;i<odSize;i++) {
			odList.get(i).setOrder(order);
		}
		return odList;
	}
	
	//生成订单详情
	private boolean generateOrderDetail(Order order, List<OrderDetail> odList) {
		int rs=0;
		rs=odDao.addOrderDetail(order,odList);
		return rs>0;
	}

	//确认订单
	public boolean confirmOrder(Integer[] cartIds,int orderId) {
		int rs=0;
		int len=cartIds.length;
		for(int i=0;i<len;i++) {
			Cart cart=cartDao.searchById(cartIds[i]);
			//改变商品表数据
			Goods goods=goodsDao.searchById(cart.getGoods().getId());
			goods.setStock(goods.getStock()-cart.getCount());
			goods.setSaleNum(goods.getSaleNum()+cart.getCount());
			rs=goodsDao.updateStock(goods);
			//改变订单状态
			Order order=orderDao.searchById(orderId);
			order.setStatus("订单已确认，等待商家发货");
			orderDao.updateOrder(order);
		}
		//删除购物车
		rs=cartDao.deleteCarts(cartIds);
		return rs>0;
	}
	
	//展示订单
	public List<Order> showOrder(User user) {
		List<Order> orderList=orderDao.searchAllOrder(user.getId());
		return orderList;
	}

	//根据订单id查询订单详情
	public List<OrderDetail> searchByOrderId(int id) {
		List<OrderDetail> odList=odDao.searchByOrderId(id);
		return odList;
	}
}
