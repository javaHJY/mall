package entity;

public class OrderDetail {
	private int id;
	private double price;//商品原价
	private int count;//商品数量
	private double lAmount;//单个订单小计
	private Order order;
	private Goods goods;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getlAmount() {
		return lAmount;
	}
	public void setlAmount(double lAmount) {
		this.lAmount = lAmount;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
