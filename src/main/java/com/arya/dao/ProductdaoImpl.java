package com.arya.dao;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.arya.model.Inventory;
import com.arya.model.Product;
import com.arya.model.User;
import com.arya.model.one_integer;

public class ProductdaoImpl implements Productdao{
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ProductdaoImpl() {
		
	}
	public ProductdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	public void saveOrUpdate(Product product) {
		 String sql = "INSERT INTO proddata(prodname,threshold,description) VALUES (?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {product.getProdname(),product.getThreshold(),product.getDescription(),});
	}
	
	public boolean exists (String prodname) {
		
		String sql = "SELECT count(*) as count FROM proddata WHERE prodname='" + prodname + "'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		
		if (count.get(0).getCount()==0) return false;
		return true;
	}
	
	public boolean exists (int pid) {
		
		String sql = "SELECT count(*) as count FROM proddata WHERE prodid='" + pid + "'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		
		if (count.get(0).getCount()==0) return false;
		return true;
	}
	
	public int get_quantity (int pid) {
		String sql = "select quantity as count from inventory where prodid = " + pid;
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		return count.get(0).getCount();
	}
	//this is used to update inventory and transaction
	public void updatep (int pid, int qnt, String staffid, String comment) {
		
		Date date = new Date();   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
		
		String sql = "UPDATE inventory SET quantity = quantity + '" + qnt + "' where prodid = '"+ pid +"' ";
		String sql2 = "INSERT INTO invtrans(prodid, staffid, quantity, comments, datntim) values('" + pid + "', '" + staffid + "', '" + qnt + "', '" + comment + "', '" + strDate + "')";
		jdbcTemplate.update(sql);
		jdbcTemplate.update(sql2);
	}
	public List<Product> getalldetails(int pid) {
		String sql="SELECT * FROM proddata where prodid ='"+ pid +"'";
		List<Product> ans = (List<Product>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
		return ans;
	}
	
	
	public int getThreshold (int pid) {
		String sql = "select threshold as count from proddata where prodid="+pid;
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		
		return count.get(0).getCount();
	}
}
