package com.arya.model;

public class Orders {
	int orderid;
	String bookerid;
	int totalprice;
	String address;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getBookerid() {
		return bookerid;
	}
	public void setBookerid(String bookerid) {
		this.bookerid = bookerid;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Orders(int orderid, String bookerid, int totalprice, String address) {
		super();
		this.orderid = orderid;
		this.bookerid = bookerid;
		this.totalprice = totalprice;
		this.address = address;
	}
	public Orders() {
		super();
	}
	
	
	

}
