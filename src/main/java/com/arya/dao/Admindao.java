package com.arya.dao;

import java.util.List;

import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Attendance;
import com.arya.model.Transaction;
import com.arya.model.User;

public interface Admindao {
	
//	public void addlog (String log);
	public List<Appointment> showapp();
	public List<User> getallusers();
	public List<AppointmentDetails> showappdetails(int appid);
	public List<Attendance> attendance();
	public List<Transaction> invtrans();
}
