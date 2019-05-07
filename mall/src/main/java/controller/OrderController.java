package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Order;
import entity.OrderDetail;
import entity.User;
import service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//生成订单
	@RequestMapping("generateOrder")
	public ModelAndView generateOrder(Integer[] cartIds) {
		ModelAndView mav=new ModelAndView("order/order");
		User user=new User();
		user.setId(1);
		List<OrderDetail> odList=orderService.generateOrder(user,cartIds);
		mav.addObject("odList", odList);
		mav.addObject("orderId", odList.get(0).getOrder().getId());
		String cIds="";
		int len=cartIds.length;
		for(int i=0;i<len;i++) {
			if(i==len-1) {
				cIds+=cartIds[i];
			}else {
				cIds+=cartIds[i]+",";
			}
		}
		mav.addObject("cIds", cIds);
		return mav;
	}
	
	//确认订单
	@RequestMapping("confirmOrder")
	public ModelAndView confirmOrder(Integer[] cartIds,int orderId) {
		ModelAndView mav=new ModelAndView("order/pay");
		orderService.confirmOrder(cartIds,orderId);
		return mav;
	}
	
	//展示订单
	@RequestMapping("showOrder")
	public ModelAndView showOrder() {
		ModelAndView mav=new ModelAndView("order/showOrder");
		User user=new User();
		user.setId(1);
		//查询订单
		List<Order> orderList=orderService.showOrder(user);
		//查询订单详情
		List<List<OrderDetail>> odList=new ArrayList<>();
		int orderSize=orderList.size();
		for(int i=0;i<orderSize;i++) {
			List<OrderDetail> odList1=orderService.searchByOrderId(orderList.get(i).getId());
			odList.add(odList1);
		}
		mav.addObject("orderList", orderList);
		mav.addObject("odList", odList);
		return mav;
	}
}
