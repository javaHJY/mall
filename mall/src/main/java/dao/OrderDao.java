package dao;

import java.util.List;

import entity.Order;

public interface OrderDao {

	int addOrder(Order order);//添加订单

	int updateOrder(Order order);//修改订单
	
	Order searchById(int id);//根据订单id查询订单
	
	List<Order> searchAllOrder(int id);//查询所有订单

	

}
