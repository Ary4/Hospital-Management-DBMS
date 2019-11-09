package com.arya.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Doctor;
import com.arya.model.Schedule;

import java.util.List;

public interface Doctordao {
	@Autowired
	public List<Doctor> getalldoc();
	public void deletedoctor (String username);
	public Doctor getdata (String username);
	public List<Schedule> getschedule (String username);
	public void saveOrUpdate(Schedule schedule);
	public void deleteschedule (String day,int timein);
	public List<Appointment> showappnext(String doctorid);
	public List<Appointment> showappprev(String doctorid);
	public List<AppointmentDetails> showappdetails(int appid,String doctorid);
	public void uploaddata(int appid,String details);
	
	public int getsalary(String doctorid);
	public void updateprofile(Doctor doctor,String doctorid,int salary);
}
