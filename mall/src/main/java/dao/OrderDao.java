package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Order;
import util.Pagination;

public interface OrderDao {

	int addOrder(Order order);//添加订单

	int updateOrder(Order order);//修改订单
	
	Order searchById(int id);//根据订单id查询订单
	
	List<Order> searchAllOrder(int id);//查询所有订单
	
	List<Order> searchOrders(@Param("id")int id, @Param("p")Pagination p);//分页查询订单

	int getDataCount();//获取订单总数

}
