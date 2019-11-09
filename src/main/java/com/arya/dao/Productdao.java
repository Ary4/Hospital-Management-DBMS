package com.arya.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arya.model.Product;

public interface Productdao {
	@Autowired
	public void saveOrUpdate(Product product);
	public boolean exists (String prodname);
	public boolean exists (int pid);
	
	public int get_quantity (int pid);
	public void updatep (int pid, int qnt, String staffid, String comment);
	public List<Product> getalldetails(int pid);
	public int getThreshold (int pid);
}
