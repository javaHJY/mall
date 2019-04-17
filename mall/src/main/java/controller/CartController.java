package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.Goods;
import service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cService;
	
	@RequestMapping("add")
	public String add(Goods g,int num) {
		cService.addGoods(g,num);
		return "redirect:list.do";
	}
	
	@RequestMapping("delete")
	public String delete(Goods g,int num) {
		cService.deleteGoods(g,num);
		return "redirect:list.do";
	}
	
	@RequestMapping("list")
	public ModelAndView showList() {
		ModelAndView mav=new ModelAndView("cart/cart");
		Cart cart=cService.searchAll();
		mav.addObject("cart",cart);
		return mav;
	}
}
