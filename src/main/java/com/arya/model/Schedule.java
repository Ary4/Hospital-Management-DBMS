package com.arya.model;

public class Schedule {
	String doctorid;
	String day;
	int timein;
	int timeout;
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getTimein() {
		return timein;
	}
	public void setTimein(int timein) {
		this.timein = timein;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public Schedule(String doctorid, String day, int timein, int timeout) {
		super();
		this.doctorid = doctorid;
		this.day = day;
		this.timein = timein;
		this.timeout = timeout;
	}
	public Schedule() {
		super();
	}
	

}
