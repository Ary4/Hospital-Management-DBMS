package com.arya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Doctor;
import com.arya.model.Product;
import com.arya.model.User;
import com.arya.model.one_integer;
import com.arya.model.Schedule;

public class DoctordaoImpl implements Doctordao {
	
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public DoctordaoImpl() {
		
	}
	public DoctordaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public List<Doctor> getalldoc() {
		String sql="SELECT username, enabled, name, email, phone, sex, salary, degree, status FROM users, doctors WHERE username = doctorid;";
		List<Doctor> ans = (List<Doctor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Doctor>(Doctor.class));
		return ans;
	}
	
	public void deletedoctor (String username) {
		String sql="DELETE FROM users WHERE username = \"" + username + "\"";
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}
	
	public Doctor getdata (String username) {
		String sql = " select username, name, email, phone, sex, salary, degree, status,department from users, doctors where username = doctorid and username = '" + username + "'";
		System.out.println(sql);
		List<Doctor> ans = (List<Doctor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Doctor>(Doctor.class));
		return ans.get(0);
		
	}
	public List<Schedule> getschedule (String user) {
		String sql = " select doctorid as username, day, timein, timeout from schedule where doctorid = '"+ user +"' ";
		System.out.println(sql);
		List<Schedule> ans = (List<Schedule>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Schedule>(Schedule.class));
		return ans ;
		
	}
	public void saveOrUpdate(Schedule schedule) {
		 String sql = "INSERT INTO schedule(doctorid,day,timein,timeout) VALUES (?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {schedule.getDoctorid(),schedule.getDay(),schedule.getTimein(),schedule.getTimeout(),});
	}
	public void deleteschedule (String day,int timein) {
		String sql="DELETE FROM schedule WHERE day = '"+day+"' and timein ='"+timein+"'";
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}
	public List<Appointment> showappnext(String doctorid)
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
		
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment WHERE y*366*31*24*60+m*31*60*24+d*60*24+(time/100*60)+(time%60)>='"+value+"' AND doctorid='"+doctorid+"' ORDER BY y*366*31*24*60+m*31*60*24+d*60*24";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public List<Appointment> showappprev(String doctorid)
	{	//Date date = new Date();
		long h=LocalDateTime.now().getHour();
		long mn=LocalDateTime.now().getMinute();
		long d=LocalDateTime.now().getDayOfMonth();
		long m=LocalDateTime.now().getMonthValue();
		long y=LocalDateTime.now().getYear();
		long value=y*366*31*24*60+m*31*60*24+d*60*24+h*60+mn;
		System.out.println(value);
		
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment WHERE y*366*31*24*60+m*31*60*24+d*60*24+(time/100*60)+(time%60)<='"+value+"' AND doctorid='"+doctorid+"' ORDER BY y*366*31*24*60+m*31*60*24+d*60*24 DESC";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public List<AppointmentDetails> showappdetails(int appid,String doctorid)
	{	//why this is important?because of more secure..so that other user cant open other's details.so this query will show error
		String sql ="SELECT t1.appid,t1.details FROM appointmentdetails as t1,appointment as t2 WHERE t1.appid='"+appid+"' and t1.appid=t2.appid and t2.doctorid ='"+doctorid+"' ORDER BY t1.appid";
		List<AppointmentDetails> ans= (List<AppointmentDetails>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<AppointmentDetails>(AppointmentDetails.class));
		return ans;
	}
	public void uploaddata(int appid,String details)
	{
		String sql="INSERT INTO appointmentdetails VALUES('"+appid+"','"+details+"')";
		jdbcTemplate.update(sql);
		
	}
	
	public int getsalary(String doctorid)
	{	
		System.out.println("DoctorID:" + doctorid);
		String sql="SELECT salary from doctors where doctorid='"+doctorid+"'";
		List<Doctor> ans= (List<Doctor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Doctor>(Doctor.class));
		System.out.println("length"+ans.size());
		return ans.get(0).getSalary();
	}
	
	public void updateprofile(Doctor doctor,String doctorid,int salary)
	{
		String sql="DELETE FROM doctors WHERE doctorid='"+doctorid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="INSERT INTO doctors(doctorid,sex,degree,salary) VALUES('"+doctorid+"','"+doctor.getSex()+"','"+doctor.getDegree()+"', '" + salary + "')";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET phone='"+doctor.getPhone()+"' where username='"+doctorid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET email='"+doctor.getEmail()+"' where username='"+doctorid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET name='"+doctor.getName()+"' where username='"+doctorid+"'";
		jdbcTemplate.batchUpdate(sql);
		
		
	}
		
	
}