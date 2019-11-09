package com.arya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.arya.model.Appointment;
import com.arya.model.Inventory;
import com.arya.model.ProdExt;
import com.arya.model.Staff;
import com.arya.model.User;

public class StaffdaoImpl implements Staffdao {
	
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public StaffdaoImpl() {
		
	}
	public StaffdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public List<Staff> getallstaff() {
		String sql = "SELECT username, enabled, name, email, phone, sex, salary FROM users, staffs WHERE username = staffid";
		List<Staff> ans = (List<Staff>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Staff>(Staff.class));
		return ans;
	}
	
	public void deletestaff (String username) {
		String sql="DELETE FROM users WHERE username = \"" + username + "\"";
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}
	public List<Appointment> showapp()
	{	//Date date = new Date();
//		Calendar calendar = GregorianCalendar.getInstance();
//		calendar.setTime(date);  
//		calendar.get(Calendar.HOUR_OF_DAY);
//		Calendar now = Calendar.getInstance();
//		int h=(now.get(Calendar.HOUR_OF_DAY));
//		int m=(now.get(Calendar.MINUTE));
		long h=LocalDateTime.now().getHour();
		long mn=LocalDateTime.now().getMinute();
		long d=LocalDateTime.now().getDayOfMonth();
		long m=LocalDateTime.now().getMonthValue();
		long y=LocalDateTime.now().getYear();
		long value=y*366*31*24*60+m*31*60*24+d*60*24+h*60+mn;
		System.out.println(value);
		
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment WHERE y*366*31*24*60+m*31*60*24+d*60*24+(time/100*60)+(time%60)>='"+value+"' ORDER BY y*366*31*24*60+m*31*60*24+d*60*24";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public List<Appointment> showappprev()
	{	//Date date = new Date();
//		Calendar calendar = GregorianCalendar.getInstance();
//		calendar.setTime(date);  
//		calendar.get(Calendar.HOUR_OF_DAY);
//		Calendar now = Calendar.getInstance();
//		int h=(now.get(Calendar.HOUR_OF_DAY));
//		int m=(now.get(Calendar.MINUTE));
		long h=LocalDateTime.now().getHour();
		long mn=LocalDateTime.now().getMinute();
		long d=LocalDateTime.now().getDayOfMonth();
		long m=LocalDateTime.now().getMonthValue();
		long y=LocalDateTime.now().getYear();
		long value=y*366*31*24*60+m*31*60*24+d*60*24+h*60+mn;
		System.out.println(value);
		
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment WHERE y*366*31*24*60+m*31*60*24+d*60*24+(time/100*60)+(time%60)<'"+value+"' ORDER BY y*366*31*24*60+m*31*60*24+d*60*24 DESC";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public void uploaddata(int appid,String details)
	{
		String sql="INSERT INTO appointmentdetails VALUES('"+appid+"','"+details+"')";
		jdbcTemplate.update(sql);
		
	}
	public List<ProdExt> getallitems() {
		String sql="SELECT t2.prodid,t2.prodname,t1.quantity FROM inventory as t1,proddata as t2 where t1.prodid =t2.prodid";
		List<ProdExt> ans = (List<ProdExt>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProdExt>(ProdExt.class));
		return ans;
	}

	
}