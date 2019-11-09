package com.arya.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Attendance;
import com.arya.model.InvTrans;
import com.arya.model.Transaction;
import com.arya.model.User;

public class AdmindaoImpl implements Admindao {
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	public void addlog (String log) {
//		
////		String sql1 = "INSERT INTO logs values(\"" + log + "\");";
//		System.out.println(log);
//		jdbcTemplate.update(sql1);
//		
//		return;
//	}
	public List<Appointment> showapp()
	{
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment ";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public List<AppointmentDetails> showappdetails(int appid)
	{	
		String sql ="select * from appointmentdetails where appid='"+appid+"' ORDER BY appid";
		List<AppointmentDetails> ans= (List<AppointmentDetails>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<AppointmentDetails>(AppointmentDetails.class));
		return ans;
	}
	public List<User> getallusers()
	{	
		String sql ="select * from users ";
		List<User> ans= (List<User>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
		return ans;
	}
	public List<Attendance> attendance()
	{	
		String sql ="select * from attendance ";
		List<Attendance> ans= (List<Attendance>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Attendance>(Attendance.class));
		return ans;
	}
	public List<Transaction> invtrans()
	{	
		String sql ="select prodid,staffid,quantity,datntim,comments from invtrans ";
		List<Transaction> ans= (List<Transaction>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Transaction>(Transaction.class));
		return ans;
	}

	

}
