package com.arya.model;

public class InvTrans {
	int prodid;
	String staffid;
	int quantity;
	String comments;
	String datntime;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDatntime() {
		return datntime;
	}
	public void setDatntime(String datntime) {
		this.datntime = datntime;
	}
	public InvTrans(int prodid, String staffid, int quantity, String comments, String datntime) {
		super();
		this.prodid = prodid;
		this.staffid = staffid;
		this.quantity = quantity;
		this.comments = comments;
		this.datntime = datntime;
	}
	public InvTrans() {
		super();
	}
	
}
