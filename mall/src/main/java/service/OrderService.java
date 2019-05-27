package service;

import java.util.List;

import entity.Order;
import entity.OrderDetail;
import entity.User;
import util.Pagination;

public interface OrderService {

	List<OrderDetail> generateOrder(User user, Integer[] cartIds);//生成订单
	
	boolean deleteOrder(int orderId);//删除订单

	List<Order> showOrder(User user);//展示订单
	
	List<Order> showOrder(User user, Pagination p);//分页展示订单

	List<OrderDetail> searchByOrderId(int id);//根据订单id查询订单详情

	boolean confirmOrder(Integer[] cartIds,int orderId);//确认订单
	
	boolean confirmReceipt(Order order);//订单确认收货

	int getDataCount(int id);//获取订单总数
}
