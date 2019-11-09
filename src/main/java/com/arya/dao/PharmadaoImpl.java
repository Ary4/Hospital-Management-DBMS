package com.arya.dao;

import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.arya.model.Cart;
import com.arya.model.Inventory;
import com.arya.model.Medicine;
import com.arya.model.OrderContains;
import com.arya.model.Orders;
import com.arya.model.Product;
import com.arya.model.one_integer;
import com.arya.model.one_string;

public class PharmadaoImpl implements Pharmadao{
		@Autowired
		DataSource datasource;
		@Autowired
		private JdbcTemplate jdbcTemplate;
		public PharmadaoImpl() {
		
		}
	public PharmadaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	public List<Medicine> getallitems() {
		String sql="SELECT * FROM medicine";
		List<Medicine> ans = (List<Medicine>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class));
		return ans;
	}
	public int getThreshold (String medid) {
		String sql = "select threshold as count from medicine where medid='"+medid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		
		return count.get(0).getCount();
	}
	public boolean exists (String medid) {
		
		String sql = "SELECT count(*) as count FROM medicine WHERE medid='" + medid + "'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		
		if (count.get(0).getCount()==0) return false;
		return true;
	}
	public void saveOrUpdate(Medicine medicine) {
		 String sql = "INSERT INTO medicine(medid,threshold,price) VALUES (?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {medicine.getMedid(),medicine.getThreshold(),medicine.getPrice()});
	}
	public int get_quantity (String medid) {
		String sql = "select quantity as count from medicine where medid = '"+medid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		return count.get(0).getCount();
	}
	public void updatep(String medid,int q)
	{
		String sql="UPDATE medicine SET quantity=quantity+'"+q+"' WHERE medid='"+medid+"'";
		jdbcTemplate.update(sql);
	}
	public int get_price (String medid) {
		String sql = "select price as count from medicine where medid = '"+medid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		return count.get(0).getCount();
	}
	public void addtocart(String bookerid,String medid,int quantity,int price) {
		int totalcost=price*quantity;
		String sql = "select count(*) as count from cart where medid = '"+medid+"' and bookerid ='"+bookerid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));
		int ans= count.get(0).getCount();
		if(ans==0)
		{sql="INSERT INTO cart(bookerid,medid,quantity,totalcost) VALUES('"+bookerid+"','"+medid+"','"+quantity+"','"+totalcost+"')";
			jdbcTemplate.update(sql);
		}
		else
		{
			sql="UPDATE cart set totalcost='"+totalcost+"' WHERE bookerid='"+bookerid+"' and medid='"+medid+"'";
			jdbcTemplate.update(sql);
			sql="UPDATE cart set quantity='"+quantity+"' WHERE bookerid='"+bookerid+"' and medid='"+medid+"'";
			jdbcTemplate.update(sql);
			
		}
	}
	public int iscartempty (String bookerid) {
		String sql = "select count(*) as count from cart where bookerid = '"+bookerid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		return count.get(0).getCount();
	}
	public List<Cart> getcartitems(String bookerid) {
		String sql="SELECT * FROM cart WHERE bookerid='"+bookerid+"'";
		List<Cart> ans = (List<Cart>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cart>(Cart.class));
		
		return ans;
	}
	public int gettotalcost (String bookerid) {
		String sql = "select sum(totalcost) as count from cart where bookerid = '"+bookerid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		return count.get(0).getCount();
	}
	public void placeorder(String bookerid,String address)
	{
		String sql = "select sum(totalcost) as count from cart where bookerid = '"+bookerid+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		int cnt = count.get(0).getCount();		
		
		sql="INSERT INTO orders(bookerid,totalprice,address) VALUES('"+bookerid+"' ,'"+cnt+"','"+address+"')";
		jdbcTemplate.update(sql);		
		//because orderid is autoincrement ..max will show the latest orderid by that bookerid
		sql = "select max(orderid) as count from orders where bookerid = '"+bookerid+"'";
		List<one_integer> order = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		int orderid = order.get(0).getCount();
		
		
		
		sql="insert into ordercontains (select '"+orderid+"',medid,quantity from cart where bookerid='"+bookerid+"')";
		jdbcTemplate.update(sql);
		
		
		sql="DELETE FROM cart WHERE bookerid='"+bookerid+"'";
		jdbcTemplate.update(sql);
		
		
		
		sql="SELECT medid,quantity from ordercontains WHERE orderid='"+orderid+"'";
		List<Medicine> list=(List<Medicine>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class));
//		ListIterator<Medicine> listIterator = list.listIterator();
		 
//		while(listIterator.hasNext()) {
//			 System.out.println("s"+listIterator.next().getMedid());
//			 listIterator.next().	
//			 //System.out.println("s"+listIterator.next().getQuantity());
////			 String medid=(String)listIterator.next().getMedid();
////			 int quantity=(int)listIterator.next().getQuantity();
////			 System.out.println(medid);
////			 System.out.println(quantity);
//			//sql="UPDATE medicine SET quantity=quantity-'"+quantity+"' WHERE medid='"+medid+"'";
//			//jdbcTemplate.update(sql);
//		}
		for(int i=0;i<list.size();i++)
		{
			String medid=list.get(i).getMedid();
			int quantity=list.get(i).getQuantity();
			System.out.println(medid);
			System.out.println(quantity);
			
			sql="UPDATE medicine SET quantity=quantity-'"+quantity+"' WHERE medid='"+medid+"'";
			jdbcTemplate.update(sql);
		}
		
	}
	public List<Orders> getallorders() {
		String sql="SELECT * FROM orders";
		List<Orders> ans = (List<Orders>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Orders>(Orders.class));
		return ans;
	}
	public List<OrderContains> orderdetails(int orderid) {
		String sql="SELECT * FROM ordercontains WHERE orderid='"+orderid+"'";
		List<OrderContains> ans = (List<OrderContains>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderContains>(OrderContains.class));
		return ans;
	}
	

}
