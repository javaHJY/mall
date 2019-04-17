package entity;

import java.util.List;

public class Cart {
	private int id;
	private double t_amount;
	private List<Goods> goodsList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getT_amount() {
		return t_amount;
	}
	public void setT_amount(double t_amount) {
		this.t_amount = t_amount;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
}
