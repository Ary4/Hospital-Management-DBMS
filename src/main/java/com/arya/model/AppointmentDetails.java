package com.arya.model;

public class AppointmentDetails {
	int appid;
	String details;
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public AppointmentDetails(int appid, String details) {
		super();
		this.appid = appid;
		this.details = details;
	}
	public AppointmentDetails() {
		super();
	}
	
}
