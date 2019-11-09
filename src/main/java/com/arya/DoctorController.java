package com.arya;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arya.dao.Admindao;
import com.arya.dao.Inventorydao;
import com.arya.dao.Productdao;
import com.arya.dao.Userdao;
import com.arya.dao.Doctordao;

import com.arya.model.Inventory;
import com.arya.model.Product;
import com.arya.model.Schedule;
import com.arya.model.Staff;
import com.arya.model.User;
import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Doctor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Controller
public class DoctorController {

	@Autowired
	public Inventorydao inventorydao;
	@Autowired
	public Admindao admindao;
	@Autowired
	public Productdao productdao;
	@Autowired
	public Doctordao doctordao;
	
	@RequestMapping(value="doctor/details")
	public ModelAndView viewdetails (Principal principal) {
		String username = principal.getName();
		
		Doctor data = doctordao.getdata(username);
		
		ModelAndView mv = new ModelAndView("doctor/details");
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping(value="doctor/schedule")
	public ModelAndView schedule (Model model,HttpServletRequest request) {
		System.out.println("Here");
		Principal principal=request.getUserPrincipal();

		String u1 = principal.getName();
		System.out.println(u1);
		
		List<Schedule> data = doctordao.getschedule(u1);
		
		ModelAndView mv = new ModelAndView("doctor/schedule");
		mv.addObject("data", data);
		mv.addObject("user", u1);
		String s = (String) model.asMap().get("message3");
//		
//		System.out.println("viewstaff controller, s="+s);
		mv.addObject("message", s);
		
		
		return mv;
	}
	

	@RequestMapping(value="doctor/addschedule",method=RequestMethod.GET)
	public String addschedule(Model model)
	{
		Schedule schedule =new Schedule();
		//model.addAttribute("list",list);
		model.addAttribute("schedule",schedule);
		return "doctor/addschedule";
	}
	
	@RequestMapping(value="doctor/addschedule",method=RequestMethod.POST)
	public ModelAndView registerProcess2(@Valid @ModelAttribute("schedule") Schedule schedule,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		Principal principal = request.getUserPrincipal();
		String u1 = principal.getName();
		String n1 = request.getParameter("day");
//		String n2 = request.getParameter("timein");
		
//		System.out.println(n1);
//		System.out.println(n2);
	//	
	//	admindao.addlog(n1);
	//	admindao.addlog(n2);
		
		//boolean exists = productdao.exists(n1);
		
		
		
			ModelAndView mv1 = new ModelAndView("doctor/addschedule");
			
			schedule.setDoctorid(u1);
			schedule.setDay(n1);
			doctordao.saveOrUpdate(schedule);
			//model.addAttribute("message", "[" + request.getParameter("name") + " successfully added as Doctor!]");
			redirectAttrs.addFlashAttribute("message3", "Schedule Successfully Added!");
			
	//		return new ModelAndView("redirect:successful");
			return new ModelAndView("redirect:/doctor/schedule");
	
		
	}
	@RequestMapping(value="doctor/deleteschedule/{day}+{timein}")
	public String deletesche (Model model, @PathVariable(value="day") String day,@PathVariable(value="timein") int timein, HttpServletRequest request, RedirectAttributes redirectAttrs) 
	{
		System.out.println(day);
		System.out.println(timein);
		doctordao.deleteschedule(day,timein);
		redirectAttrs.addFlashAttribute("message3", "Schedule day "+day+" Timein "+timein+" is deleted");
		return "redirect:/doctor/schedule";
//		
//		String uid=request.getUserPrincipal().getName();
//		Cart cart=new Cart();
//		cartdao.addtocart(iid, uid, cart);
//		return "redirect:/allcat";
	}
	@RequestMapping(value="doctor/showappnext")
	public ModelAndView showappnext(Principal principal,ModelAndView mv )
	{	String doctorid =principal.getName();
		List<Appointment> applist = doctordao.showappnext(doctorid);
		mv.setViewName("doctor/showappnext");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="doctor/showappprev")
	public ModelAndView showappprev(Principal principal,ModelAndView mv )
	{	String doctorid =principal.getName();
		List<Appointment> applist = doctordao.showappprev(doctorid);
		mv.setViewName("doctor/showappprev");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="doctor/showappdetails/{appid}")
	public ModelAndView showdetails(Principal principal,@PathVariable("appid") int appid ,ModelAndView mv,RedirectAttributes redirectAttrs)
	{	
		String doctorid =principal.getName();
		mv.setViewName("doctor/showappdetails");
		List<AppointmentDetails> a=doctordao.showappdetails(appid, doctorid);
		mv.addObject("ilist", a);
		return mv;
	}
	@RequestMapping(value="doctor/uploaddata/{appid}",method=RequestMethod.GET)
	public String uploaddata(Model model,@PathVariable("appid") int appid )
	{
		AppointmentDetails appointmentdetails =new AppointmentDetails();
		//model.addAttribute("list",list);
		model.addAttribute("appointmentdetails",appointmentdetails);
		model.addAttribute("storethis", appid);
		return "staff/uploaddata";
	}

	@RequestMapping(value="doctor/uploaddata/{appid}",method=RequestMethod.POST)
	public ModelAndView uploadfile( @Valid @ModelAttribute("appointmentdetails") AppointmentDetails appointmentdetail,@PathVariable("appid") int appid,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		//int appid = Integer.parseInt(request.getParameter("storethis"));
		String details = request.getParameter("details");		
		doctordao.uploaddata(appid, details);	
		redirectAttrs.addFlashAttribute("message3", "Appointment id " + appid + "'s details have been uploaded");
		return new ModelAndView("redirect:/doctor");
	}

	@RequestMapping(value="doctor/updatedetails",method=RequestMethod.GET)
	public String updateprof(Model model)
	{
		Doctor doctor =new Doctor();
		//model.addAttribute("list",list);
		model.addAttribute("doctor",doctor);
		return "doctor/updatedetails";
	}
	@RequestMapping(value="doctor/updatedetails",method=RequestMethod.POST)
	public ModelAndView updateprof2( @Valid @ModelAttribute("doctor")Doctor doctor,Principal principal,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String u1=principal.getName();
		System.out.println(u1);
		int salary = doctordao.getsalary(u1);
		doctordao.updateprofile(doctor, u1, salary);
		redirectAttrs.addFlashAttribute("message3", "Profile Succesfully updated ");
		return new ModelAndView("redirect:/doctor");
	}
	
	
	
}
