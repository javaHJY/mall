package service;

import java.util.List;

import entity.Order;
import entity.OrderDetail;
import entity.User;

public interface OrderService {

	List<OrderDetail> generateOrder(User user, Integer[] cartIds);//生成订单

	List<Order> showOrder(User user);//展示订单

	List<OrderDetail> searchByOrderId(int id);//根据订单id查询订单详情

	boolean confirmOrder(Integer[] cartIds,int orderId);//确认订单

}
