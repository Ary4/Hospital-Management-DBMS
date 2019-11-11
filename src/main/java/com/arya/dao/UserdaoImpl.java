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

import com.arya.model.User;
import com.arya.model.one_integer;
import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Doctor;
import com.arya.model.Orders;
import com.arya.model.Patient;
import com.arya.model.Schedule;

public class UserdaoImpl implements Userdao{
	
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserdaoImpl() {
		
	}
	public UserdaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	public void saveOrUpdate(User user) {
		 String sql = "INSERT INTO users(username, password,name,email,phone) VALUES (?,?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone()});
		 
		 sql = "INSERT INTO user_roles VALUES (?,'ROLE_USER')";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername()});
		
		 sql = "INSERT INTO patients(pid) VALUES(?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername()});
		 
		 //System.out.println("EROOR");
	}
	public void saveOrUpdatedoc(User user,String d1) {
		 String sql = "INSERT INTO users(username, password,name,email,phone) VALUES (?,?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone()});
		 
//		 sql = "INSERT INTO users_roles(user,role) VALUES(?,?)";
//		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),"ROLE_DOCTOR"});
		 sql = "INSERT INTO user_roles VALUES (?,'ROLE_USER')";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername()});
		 sql = "UPDATE users_roles SET role = \"ROLE_DOCTOR\" WHERE user = \"" + user.getUsername() + "\"";
		 jdbcTemplate.update(sql);
		 sql="UPDATE doctors SET department='"+d1+"' WHERE doctorid='"+user.getUsername()+"'";
		 jdbcTemplate.update(sql);
		 //System.out.println("EROOR");
	}
	public void saveOrUpdatestaff(User user) {
		 String sql = "INSERT INTO users(username, password,name,email,phone) VALUES (?,?,?,?,?)";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone()});
		 sql = "INSERT INTO user_roles VALUES (?,'ROLE_USER')";
		 jdbcTemplate.update(sql,new Object[] {user.getUsername()});
//		 sql = "INSERT INTO users_roles(user,role) VALUES(?,?)";
//		 jdbcTemplate.update(sql,new Object[] {user.getUsername(),"ROLE_DOCTOR"});
		 sql = "UPDATE users_roles SET role = \"ROLE_STAFF\" WHERE user = \"" + user.getUsername() + "\"";
		 jdbcTemplate.update(sql);
		 
		 //System.out.println("EROOR");
	}
	public void delete(String username) {
		String sql = "DELETE FROM users WHERE username=?";
		jdbcTemplate.update(sql,username);
	}
	public User getUser(String username) {
		String sql = "SELECT * FROM users WHERE username='"+username+"'";
		return jdbcTemplate.query(sql,new ResultSetExtractor<User>() {
		
			public User extractData(ResultSet rs) throws SQLException,DataAccessException{
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			}
			
		});
	}
	static int dayofweek(int d, int m, int y) 
	{ 
	    int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 }; 
	    y -= (m < 3) ? 1 : 0; 
	    return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7; 
	} 
	
	// Driver Program to test above f 
	
	public List<Schedule> searchsche(int d,int m,int y,int time, String department)
	{	int dayint = dayofweek(d, m, y);
		String [] daymap = { "sun", "mon", "tue", "wed", "thr", "fri", "sat" };
		String day = daymap[dayint];
		
		String sql="SELECT t2.doctorid,day,timein,timeout from schedule as t2,doctors as t1 where t1.doctorid=t2.doctorid and t1.department = '"+department+"' and  t2.timeout >= '"+time+"' and t2.timein <= '"+time+"' and t2.day='"+day+"' ";
		List<Schedule> ans = (List<Schedule>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Schedule>(Schedule.class));
		return ans;
	}
	public void bookapp(int d,int m,int y,int time,String doctorid,String department,String pid)
	{
		String sql ="INSERT INTO appointment(d,m,y,time,doctorid,pid,department) VALUES('"+d+"','"+m+"','"+y+"','"+time+"','"+doctorid+"','"+pid+"','"+department+"')";
		jdbcTemplate.update(sql);
	}
	public List<Appointment> showapp(String pid)
	{	
		long h=LocalDateTime.now().getHour();
		long mn=LocalDateTime.now().getMinute();
		long d=LocalDateTime.now().getDayOfMonth();
		long m=LocalDateTime.now().getMonthValue();
		long y=LocalDateTime.now().getYear();
		long value=y*366*31*24*60+m*31*60*24+d*60*24+h*60+mn;
		System.out.println(value);
		String sql="SELECT appid,d,m,y,time,doctorid,pid,department FROM appointment WHERE pid='"+pid+"' ORDER BY y*366*31*24*60+m*31*60*24+d*60*24+(time/100*60)+(time%60)";
		List<Appointment> ans= (List<Appointment>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ans;
	}
	public void deleteapp(int appid,String pid)
	{
		String sql ="DELETE FROM appointment WHERE appid='"+appid+"' and pid ='"+pid+"'";
		jdbcTemplate.update(sql);
	}
	public List<AppointmentDetails> showappdetails(int appid,String pid)
	{	//why this is important?because of more secure..so that other user cant open other's details.so this query will show error
		String sql ="SELECT t1.appid,t1.details FROM appointmentdetails as t1,appointment as t2 WHERE t1.appid='"+appid+"' and t1.appid=t2.appid and t2.pid ='"+pid+"' ORDER BY t1.appid";
		List<AppointmentDetails> ans= (List<AppointmentDetails>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<AppointmentDetails>(AppointmentDetails.class));
		return ans;
	}
	public List<Orders> getallorders(String bookerid) {
		String sql="SELECT * FROM orders where bookerid='"+bookerid+"'";
		List<Orders> ans = (List<Orders>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Orders>(Orders.class));
		return ans;
	}
	public void attendance(String username)
	{
		long h=LocalDateTime.now().getHour();
		long mn=LocalDateTime.now().getMinute();
		long d=LocalDateTime.now().getDayOfMonth();
		long m=LocalDateTime.now().getMonthValue();
		long y=LocalDateTime.now().getYear();
		long value=y*366+m*31+d;
		long time=100*h+mn;
		
				
		String sql = "select count(*) as count from attendance where y*366+m*31+d = '"+value+"' and empid='"+username+"'";
		List<one_integer> count = (List<one_integer>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<one_integer>(one_integer.class));		
		int c= count.get(0).getCount();
		System.out.println(c);
		if(c==0)
		{
			sql="INSERT INTO attendance(empid,d,m,y,time) VALUES('"+username+"','"+d+"','"+m+"','"+y+"','"+time+"')";
			jdbcTemplate.update(sql);
		}
	}
	public Patient getdata (String username) {
		String sql = " select username, name, email, phone,bloodgroup,previousdiseases,address,sex from users, patients where username = pid and username = '" + username + "'";
		System.out.println(sql);
		List<Patient> ans = (List<Patient>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<Patient>(Patient.class));
		return ans.get(0);
		
	}
	public void updateprofile(Patient patient,String pid)
	{
		String sql="DELETE FROM patients WHERE pid='"+pid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="INSERT INTO patients(pid,sex,previousdiseases,address) VALUES('"+pid+"','"+patient.getSex()+"','"+patient.getPreviousdiseases()+"', '" + patient.getAddress() + "')";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET phone='"+patient.getPhone()+"' where username='"+pid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET email='"+patient.getEmail()+"' where username='"+pid+"'";
		jdbcTemplate.batchUpdate(sql);
		sql="UPDATE users SET name='"+patient.getName()+"' where username='"+pid+"'";
		jdbcTemplate.batchUpdate(sql);
		
		
	}
	public List<AppointmentDetails> showprevapp(String pid)
	{
		String sql=" select t1.appid,t1.details from appointmentdetails as t1 join appointment as t2 on t1.appid=t2.appid where pid='"+pid+"'";
		List<AppointmentDetails> ans = (List<AppointmentDetails>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppointmentDetails>(AppointmentDetails.class));
		return ans;
		
	}
	
}
