package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Goods;

public interface GoodsDao {
	
	int updateStock(@Param("goods")Goods goods);//改变商品库存和销售量
	List<Goods> searchAll();//查询商品的所有信息
	Goods searchById(int id);//按照商品id查询商品信息
}
