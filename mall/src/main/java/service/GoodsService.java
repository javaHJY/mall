package service;

import java.util.List;

import entity.Goods;

public interface GoodsService {
	List<Goods> searchAll();
	Goods searchById(int id);
}
