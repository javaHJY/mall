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
	
	@Override
	public List<Goods> searchAll() {
		List<Goods> list=goodsDao.searchAll();
		return list;
	}

	@Override
	public Goods searchById(int id) {
		Goods g=goodsDao.searchById(id);
		return g;
	}

}
