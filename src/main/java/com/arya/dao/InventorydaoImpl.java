package com.arya.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import com.arya.model.Inventory;

public class InventorydaoImpl implements Inventorydao {
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public InventorydaoImpl() {
		
	}
	public InventorydaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	public List<Inventory> getallitems() {
		String sql="SELECT * FROM inventory";
		List<Inventory> ans = (List<Inventory>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Inventory>(Inventory.class));
		return ans;
	}
	
}
