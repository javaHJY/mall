package dao;

import org.apache.ibatis.annotations.Param;

import entity.Cart;
import entity.Goods;

public interface CartDao {
	
	int addGoods(@Param("g")Goods g,@Param("num")int num);//新增购物车中没有的商品
	int increaseGoods(@Param("g")Goods g,@Param("num")int num);//增加购物车中已有的商品
	int deleteGoods(@Param("g")Goods g);//删除购物车中的商品
	
	Cart searchAll();//查询购物车的所有商品
	Goods searchGoodsById(int id);//根据商品id查询购物车的商品
	
}
