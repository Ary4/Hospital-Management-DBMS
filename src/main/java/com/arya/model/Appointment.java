package com.arya.model;

public class Appointment {
	int appid;
	int d;
	int m;
	int y;
	int time;
	String doctorid;
	String pid;
	String Department;
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
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
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public Appointment(int appid, int d, int m, int y, int time, String doctorid, String pid, String department) {
		super();
		this.appid = appid;
		this.d = d;
		this.m = m;
		this.y = y;
		this.time = time;
		this.doctorid = doctorid;
		this.pid = pid;
		Department = department;
	}
	public Appointment() {
		super();
	}
	
}
