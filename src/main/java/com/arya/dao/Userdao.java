package com.arya.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Orders;
import com.arya.model.Patient;
import com.arya.model.Schedule;
import com.arya.model.User;

public interface Userdao{
	@Autowired
	public void saveOrUpdate(User user);
	public void saveOrUpdatedoc(User user,String d1);
	public void saveOrUpdatestaff(User user);
	public void delete(String username);
	public User getUser(String username);
	public List<Schedule> searchsche(int d,int m,int y,int time, String department);
	public void bookapp(int d,int m,int y,int time,String doctorid,String department,String pid);
	public List<Appointment> showapp(String pid);
	public void deleteapp(int appid,String pid);
	public List<AppointmentDetails> showappdetails(int appid,String pid);
	public List<Orders> getallorders(String bookerid);
	public void attendance(String username);
	public Patient getdata (String username) ;
	public void updateprofile(Patient patient,String pid);
	public List<AppointmentDetails> showprevapp(String pid);
	
}