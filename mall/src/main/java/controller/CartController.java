package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.Goods;
import service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cService;
	
	//向购物车添加商品
	@RequestMapping("add")
	public String add(Goods g,int num) {
		cService.addGoods(g,num);
		return "redirect:list.do";
	}
	
	//增加购物车中的商品
	@RequestMapping("increaseGoods")
	@ResponseBody
	public String increaseGoods(int gId,int num) {
		cService.increaseGoods(gId,num);
//		Goods g=cService.searchGoodsById(gId);
		return "redirect:list.do";
	}

	//减少购物车中的商品
	@RequestMapping("decreaseGoods")
	@ResponseBody
	public String decreaseGoods(int gId,int num) {
		cService.decreaseGoods(gId,num);
//		Goods g=cService.searchGoodsById(gId);
		return "redirect:list.do";
	}
	
	//删除购物车中的商品
	@RequestMapping("delete")
	public String delete(Goods g,int num) {
		cService.deleteGoods(g,num);
		return "redirect:list.do";
	}
	
	//展示购物车商品
	@RequestMapping("list")
	public ModelAndView showList() {
		ModelAndView mav=new ModelAndView("cart/cart");
		Cart cart=cService.searchAll();
		mav.addObject("cart",cart);
		return mav;
	}
}
