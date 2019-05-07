package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Order;
import entity.OrderDetail;

public interface OrderDetailDao {

	int addOrderDetail(@Param("order")Order order, @Param("odList")List<OrderDetail> odList);//添加订单详情
	List<OrderDetail> searchByOrderId(int id);//根据订单id查询订单详情
}
