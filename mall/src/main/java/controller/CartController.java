package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.User;
import service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cService;
	
	//向购物车添加商品
	@RequestMapping("addGoods")
	public String addGoods(Cart cart) {
		User user=new User();
		user.setId(1);
		cart.setUser(user);
		cService.addGoods(cart);
		return "redirect:list.do";
	}
	
	//改变购物车中的商品
	@RequestMapping(value="updateCart",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Cart updateCart(Cart cart) {
		User user=new User();
		user.setId(1);
		cart.setUser(user);
		Cart c =cService.updateCart(cart);
		return c;
	}
	
	//删除购物车中的商品
	@RequestMapping("delete")
	public String delete(Cart cart) {
		User user=new User();
		user.setId(1);
		cart.setUser(user);
		cService.deleteGoods(cart);
		return "redirect:list.do";
	}
	
	//展示购物车商品
	@RequestMapping("list")
	public ModelAndView showList() {
		ModelAndView mav=new ModelAndView("cart/cart");
		User user=new User();
		user.setId(1);
		List<Cart> cartList=cService.searchAllByUser(user);
		mav.addObject("cartList",cartList);
		return mav;
	}
}
