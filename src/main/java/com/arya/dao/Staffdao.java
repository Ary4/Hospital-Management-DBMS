package com.arya.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.arya.model.Appointment;
import com.arya.model.ProdExt;
import com.arya.model.Staff;

import java.util.List;

public interface Staffdao {
	@Autowired
	public List<Staff> getallstaff();
	public void deletestaff (String username);
	public List<Appointment> showapp();
	public void uploaddata(int appid,String details);
	public List<Appointment> showappprev();
	public List<ProdExt> getallitems();
}