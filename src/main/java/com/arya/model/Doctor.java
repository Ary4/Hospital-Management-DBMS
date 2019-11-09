package com.arya.model;

public class Doctor {
	
	// Stored in users table
	String username;
	String password;
	int enabled;
	String name;
	String email;
	String phone;
	String department;
	
	// Stored in doctors table
	String sex;
	// dob
	// doj
	int salary;
	String degree;
	int status;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Doctor(String username, String password, int enabled, String name, String email, String phone,
			String department, String sex, int salary, String degree, int status) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.sex = sex;
		this.salary = salary;
		this.degree = degree;
		this.status = status;
	}
	public Doctor() {
		super();
	}	
	
	
	
}