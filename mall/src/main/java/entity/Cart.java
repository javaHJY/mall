package entity;

public class Cart {
	private int id;
	private Goods goods;
	private int count;
	private double l_amount;
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getL_amount() {
		return l_amount;
	}
	public void setL_amount(double l_amount) {
		this.l_amount = l_amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
