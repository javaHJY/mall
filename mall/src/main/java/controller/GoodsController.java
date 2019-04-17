package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Goods;
import service.GoodsService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="list")
	public ModelAndView showList() {
		ModelAndView mav=new ModelAndView("goods/list");
		List<Goods> list=goodsService.searchAll();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("detail")
	public ModelAndView showDetail(Goods g) {
		ModelAndView mav=new ModelAndView("goods/detail");
		Goods goods=goodsService.searchById(g.getId());
		mav.addObject("goods", goods);
		return mav;
	}
}
