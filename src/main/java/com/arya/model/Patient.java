package com.arya.model;

public class Patient {
	//stored in user table
	String username;
	String password;
	int enabled;
	String name;
	String email;
	String phone;
	//stored in patients tables
	String bloodGroup;
	String previousdiseases;
	String address;
	String sex;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getPreviousdiseases() {
		return previousdiseases;
	}
	public void setPreviousdiseases(String previousdiseases) {
		this.previousdiseases = previousdiseases;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Patient(String username, String password, int enabled, String name, String email, String phone,
			String bloodGroup, String previousdiseases, String address, String sex) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.bloodGroup = bloodGroup;
		this.previousdiseases = previousdiseases;
		this.address = address;
		this.sex = sex;
	}
	public Patient() {
		super();
	}
	
}
