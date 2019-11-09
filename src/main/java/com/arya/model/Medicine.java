package com.arya.model;

public class Medicine {
	String medid;
	int quantity;
	int threshold;
	int price;
	String low;
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
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
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Medicine(String medid, int quantity, int threshold, int price, String low) {
		super();
		this.medid = medid;
		this.quantity = quantity;
		this.threshold = threshold;
		this.price = price;
		this.low = low;
	}
	public Medicine() {
		super();
	}
	
	
}
