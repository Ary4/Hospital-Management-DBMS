package com.arya.model;

public class Staff {
	
	// Stored in users table
	String username;
	String password;
	int enabled;
	String name;
	String email;
	String phone;
	
	// Stored in doctors table
	String sex;
	int salary;	
	
	public Staff () {
		
	}
	
	
	
	
	public Staff(String username, String password, String name, String email, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = 1;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}




	public Staff(String username, String password, int enabled, String name, String email, String phone, String sex,
			int salary) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.salary = salary;
	}




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




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}




	public int getSalary() {
		return salary;
	}




	public void setSalary(int salary) {
		this.salary = salary;
	}

    	
}