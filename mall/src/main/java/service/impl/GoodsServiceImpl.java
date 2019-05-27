package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GoodsDao;
import entity.Goods;
import service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	//查询所有商品
	public List<Goods> searchAll() {
		List<Goods> list=goodsDao.searchAll();
		return list;
	}

	//根据商品id查询商品详情
	public Goods searchById(int id) {
		Goods g=goodsDao.searchById(id);
		return g;
	}

	//根据商品名称模糊查询商品列表
	public List<Goods> searchByName(String goodsName) {
		List<Goods> list=goodsDao.searchByName(goodsName);
		return list;
	}

}
