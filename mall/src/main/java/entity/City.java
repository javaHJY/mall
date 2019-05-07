package entity;

public class City {
	private int id;
	private String name;
	private Province provice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Province getProvice() {
		return provice;
	}
	public void setProvice(Province provice) {
		this.provice = provice;
	}
	
}
