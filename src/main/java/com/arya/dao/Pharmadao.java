package com.arya.dao;

import java.util.List;

import com.arya.model.Cart;
import com.arya.model.Medicine;
import com.arya.model.OrderContains;
import com.arya.model.Orders;

public  interface Pharmadao {
	public List<Medicine> getallitems();
	public int getThreshold (String medid);
	public boolean exists (String medid);
	public void saveOrUpdate(Medicine medicine);
	public int get_quantity (String medid);
	public void updatep(String medid,int q);
	public int get_price (String medid);
	public void addtocart(String bookerid,String medid,int quantity,int price);
	public List<Cart> getcartitems(String bookerid);
	public int gettotalcost (String bookerid);
	public void placeorder(String bookerid,String address);
	public List<Orders> getallorders();
	public List<OrderContains> orderdetails(int orderid);
	public int iscartempty (String bookerid);

}
