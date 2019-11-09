package com.arya.model;

public class Attendance {
	String empid;
	int d;
	int m;
	int y;
	int time;
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Attendance(String empid, int d, int m, int y, int time) {
		super();
		this.empid = empid;
		this.d = d;
		this.m = m;
		this.y = y;
		this.time = time;
	}
	public Attendance() {
		super();
	}
	
}
