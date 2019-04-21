package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Goods;

public interface GoodsDao {
	
	int updateStock(@Param("goods")Goods goods);//改变商品库存和销售量
	int reduceStock(@Param("goods")Goods goods);//减少商品库存并增加销售量
	int restoreGoods(@Param("g")Goods goods,@Param("num")int num);//购物车删除商品后恢复商品库存
	List<Goods> searchAll();//查询商品的所有信息
	Goods searchById(int id);//按照商品id查询商品信息
	
	
	
}
