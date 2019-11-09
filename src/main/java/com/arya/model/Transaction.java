package com.arya.model;

public class Transaction {
	
	
	int prodid;
	String staffid;
	int quantity;
	String datntim;
	String comments;
	public Transaction(int prodid, String staffid, int quantity, String datntim, String comments) {
		super();
		this.prodid = prodid;
		this.staffid = staffid;
		this.quantity = quantity;
		this.datntim = datntim;
		this.comments = comments;
	}
	public Transaction() {
		super();
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDatntim() {
		return datntim;
	}
	public void setDatntim(String datntim) {
		this.datntim = datntim;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	

}
