package com.arya.model;

public class Cart {
	String bookerid;
	String medid;
	int quantity;
	int totalcost;
	public Cart(String bookerid, String medid, int quantity, int totalcost) {
		super();
		this.bookerid = bookerid;
		this.medid = medid;
		this.quantity = quantity;
		this.totalcost = totalcost;
	}
	public String getBookerid() {
		return bookerid;
	}
	public void setBookerid(String bookerid) {
		this.bookerid = bookerid;
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
	public int getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}
	public Cart() {
		super();
	}
	
	
}
