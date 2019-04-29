package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Cart;
import entity.User;

public interface CartDao {
	
	int addGoods(@Param("cart")Cart cart);//新增购物车中没有的商品
	int updateCart(@Param("cart")Cart cart);//改变购物车信息
	int deleteGoods(@Param("cart")Cart cart);//删除购物车中的商品
	List<Cart> searchAllByUser(@Param("user")User user);//根据用户id查询购物车的所有商品
	Cart searchByCart(@Param("cart")Cart cart);//根据商品id查询购物车中该商品信息
}
