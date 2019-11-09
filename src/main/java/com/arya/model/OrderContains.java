package com.arya.model;

public class OrderContains {
	int orderid;
	String medid;
	int quantity;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getMedid() {
		return medid;
	}
	public void setMedid(String medid) {
		this.medid = medid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderContains(int orderid, String medid, int quantity) {
		super();
		this.orderid = orderid;
		this.medid = medid;
		this.quantity = quantity;
	}
	public OrderContains() {
		super();
	}
	

}
