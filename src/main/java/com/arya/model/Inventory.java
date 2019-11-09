package com.arya.model;

public class Inventory {
	
	int prodid;
	int quantity;
	
	int limit;
	String low;
	
	public Inventory(int prodid, int quantity, String low, int limit) {
		super();
		this.prodid = prodid;
		this.quantity = quantity;
		this.low = low;
		this.limit = limit;
	}
	public Inventory() {
		super();
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
}
