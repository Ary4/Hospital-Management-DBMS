package com.arya.model;

public class Product {
	
	int prodid;
	String prodname;
	int threshold;
	String description;
	
	
	//
	public Product(int prodid, String prodname, int threshold, String description) {
		super();
		this.prodid = prodid;
		this.prodname = prodname;
		this.threshold = threshold;
		this.description = description;
	}
	
	public Product(int prodid, String prodname, int threshold) {
		super();
		this.prodid = prodid;
		this.prodname = prodname;
		this.threshold = threshold;
	}
	
	//
	public Product() {
		super();
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
