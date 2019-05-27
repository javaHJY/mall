package service;

import java.util.List;

import entity.Goods;

public interface GoodsService {
	List<Goods> searchAll();// 查询所有商品

	Goods searchById(int id);// 根据商品id查询商品详情

	List<Goods> searchByName(String goodsName);// 根据商品名称模糊查询商品
}
