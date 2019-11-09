package com.arya.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arya.model.Inventory;

public interface Inventorydao {
	@Autowired
	public List<Inventory> getallitems();
	
//	public void showinvenr (String username);
}
