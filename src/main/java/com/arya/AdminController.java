package com.arya;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arya.dao.Admindao;
import com.arya.dao.Doctordao;
import com.arya.dao.Inventorydao;
import com.arya.dao.Productdao;
import com.arya.dao.Staffdao;
import com.arya.dao.Userdao;
import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Attendance;
import com.arya.model.Doctor;
import com.arya.model.Inventory;
import com.arya.model.Product;
import com.arya.model.Staff;
import com.arya.model.Transaction;
import com.arya.model.User;

@Controller
public class AdminController {
	boolean hasspace (String s) {
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)==' ') {
				return true;
			}
		}
		return false;
	}
	
	
	@Autowired
	Doctordao doctordao;
	@Autowired
	Staffdao staffdao;
	@Autowired
	Admindao admindao;
	@Autowired
	Inventorydao inventorydao;
	@Autowired
	Productdao productdao;
	
	
	
	@RequestMapping("admin/allusers")
	public String allusers(Model model)
	{
		List<User> list=admindao.getallusers();
		model.addAttribute("list",list);
		return "admin/alluser";
	}
	
	@RequestMapping(value="admin/adddoctor",method=RequestMethod.GET)
		public String adddoctor(Model model)
		{
			User user= new User();
			//model.addAttribute("list",list);
			model.addAttribute("user",user);
			return "admin/adddoctor";
		}
	@Autowired
	public Userdao userdao;
	@RequestMapping(value="admin/adddoctor",method=RequestMethod.POST)
	public ModelAndView registerProcess(@Valid @ModelAttribute("user") User user,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String n1 = request.getParameter("name");
		String n2 = request.getParameter("phone");
		String d1 = request.getParameter("department");
		System.out.println(n1);
		System.out.println(n2);
//		
//		admindao.addlog(n1);
//		admindao.addlog(n2);
		
		if(result.hasErrors()) {
			return new ModelAndView("admin/adddoctor");
		}
		else {
			ModelAndView mv1 = new ModelAndView("admin/adddoctor");
			if(!user.getPassword().equals(user.getMpassword())) {
				mv1.addObject("error", "passwords dont match");
//				model.addAttribute("error","passwords dont match");
				return mv1;
			}
			if (hasspace(user.getUsername())) {
				model.addAttribute("error", "Username cant have space");
				return mv1;
			}
			if(userdao.getUser(user.getUsername())!=null) {
//				model.addAttribute("error", "username exists");
				mv1.addObject("error", "username exists");
				return mv1;
			}
			userdao.saveOrUpdatedoc(user,d1);
//			model.addAttribute("message", "[" + request.getParameter("name") + " successfully added as Doctor!]");
			redirectAttrs.addFlashAttribute("message3", "Doctor Succesfully Added");
			
			return new ModelAndView("redirect:/admin");
		}
	}
	
	@RequestMapping(value="admin/successful",method=RequestMethod.GET)
	public String successful(Model model) 
	{
		String some = (String) model.asMap().get("Pass");
	    System.out.println("some value: " + some);
//		return "admin/successful";
		
	    if (some=="yes") {
	    	return "admin/successful";
	    }
	    
//		System.out.println("555" +  + "1");
//		if (request.getParameter("name")!=null) {
//			return "admin/successful";
//		}
//		
		return "error";
	}
	
//	 @RequestMapping(value = "bar", method = RequestMethod.GET)
//	  public ModelAndView handleGet(Model model) {
//		System.out.println("handleGet called!");
//	    String some = (String) model.asMap().get("some");
//	    System.out.println("some value: " + some);
//	    ModelAndView mav = new ModelAndView("foobar");
//	    
//	    User user = new User();
//		mav.addObject("user", user);
//		
//	    return mav;
//	    // do the job
//	  }
//
//	  @RequestMapping(value = "bar", method = RequestMethod.POST)
//	    public ModelAndView handlePost(RedirectAttributes redirectAttrs) {
//		  System.out.println("handlePost called!");
//		  redirectAttrs.addFlashAttribute("some", "thing");
//
////	    return new ModelAndView().setViewName("redirect:/foo/bar");
//		  return new ModelAndView("redirect:bar");
//	  } flash attribute checking code..debugging
	@RequestMapping(value="admin/addstaff",method=RequestMethod.GET)
	public String addstaff(Model model)
	{
		User user= new User();
		//model.addAttribute("list",list);
		model.addAttribute("user",user);
		return "admin/addstaff";
	}

	@RequestMapping(value="admin/addstaff",method=RequestMethod.POST)
	public ModelAndView registerProcess2(@Valid @ModelAttribute("user") User user,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String n1 = request.getParameter("name");
		String n2 = request.getParameter("phone");
		System.out.println(n1);
		System.out.println(n2);
	
		if(result.hasErrors()) {
			return new ModelAndView("admin/addstaff");
		}
		else {
			ModelAndView mv1 = new ModelAndView("admin/addstaff");
			if(!user.getPassword().equals(user.getMpassword())) {
				mv1.addObject("error", "passwords dont match");
	//			model.addAttribute("error","passwords dont match");
				return mv1;
			}
			if (hasspace(user.getUsername())) {
				model.addAttribute("error", "Username cant have space");
				return mv1;
			}
			if(userdao.getUser(user.getUsername())!=null) {
	//			model.addAttribute("error", "username exists");
				mv1.addObject("error", "username exists");
				return mv1;
			}
			userdao.saveOrUpdatestaff(user);
	//		model.addAttribute("message", "[" + request.getParameter("name") + " successfully added as Doctor!]");
			redirectAttrs.addFlashAttribute("message3", "Staff Successfully Added!");
			
	//		return new ModelAndView("redirect:successful");
			return new ModelAndView("redirect:/admin");
	
		}
	}
	
	@RequestMapping("admin/viewdoctors")
	public String showdoctors (Model model)
	{
		//ModelAndView mav = new ModelAndView("admin/viewdoctors");
		List<Doctor> doclist = doctordao.getalldoc();
		String s = (String) model.asMap().get("message3");
		
		System.out.println("viewdoctor controller, s="+s);
		model.addAttribute("message", s);
		
		model.addAttribute("doctorlist", doclist);

		return "admin/viewdoctors";

	}
	
	@RequestMapping(value="admin/deletedoctor/{uid}")
	public String deletedoc (Model model, @PathVariable(value="uid") String uid, HttpServletRequest request, RedirectAttributes redirectAttrs) 
	{
		System.out.println(uid);
		doctordao.deletedoctor(uid);
		redirectAttrs.addFlashAttribute("message3", "Doctor "+uid+" is deleted.hopefully not fired");
		return "redirect:/admin/viewdoctors";
	}
	
	
	@RequestMapping("admin/viewstaffs")
	public ModelAndView showstaff (Model model, ModelAndView mv)
	{
		mv.setViewName("admin/viewstaffs");
		List<Staff> stafflist = staffdao.getallstaff();
		String s = (String) model.asMap().get("message3");
		
		System.out.println("viewstaff controller, s="+s);
		mv.addObject("message", s);
		
		mv.addObject("stafflist", stafflist);
		return mv;
	}
	@RequestMapping(value="admin/deletestaff/{uid}")
	public String deletestaff (Model model, @PathVariable(value="uid") String uid, HttpServletRequest request, RedirectAttributes redirectAttrs) 
	{
		System.out.println(uid);
		staffdao.deletestaff(uid);
		redirectAttrs.addFlashAttribute("message3", "Staff "+uid+" is deleted.hopefully not fired");
		return "redirect:/admin/viewstaffs";
	}
	@RequestMapping(value="admin/showapp")
	public ModelAndView showapp(ModelAndView mv )
	{	//String pid =principal.getName();
		List<Appointment> applist = admindao.showapp();
		mv.setViewName("admin/showapp");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="admin/showappdetails/{appid}")
	public ModelAndView showdetails(@PathVariable("appid") int appid ,ModelAndView mv,RedirectAttributes redirectAttrs)
	{	
		//String pid =principal.getName();
		mv.setViewName("admin/showappdetails");
		List<AppointmentDetails> a=admindao.showappdetails(appid);
		mv.addObject("ilist", a);
		//redirectAttrs.addFlashAttribute("message3", "appid " + appid + " is deleted ");
		return mv;
		

	}
	@RequestMapping(value="admin/viewinventory")
	public ModelAndView showdetails(ModelAndView mv)
	{	mv.setViewName("admin/viewinventory");
		List<Inventory> inventorylist=inventorydao.getallitems();
		for (int i = 0; i < inventorylist.size(); i++) {
			int limit = productdao.getThreshold(inventorylist.get(i).getProdid());
			inventorylist.get(i).setLimit(limit);
			if (inventorylist.get(i).getQuantity() <= limit) {
				inventorylist.get(i).setLow("YES");
			} else {
				inventorylist.get(i).setLow("NO");
			}
		}
		mv.addObject("ilist", inventorylist);
		//redirectAttrs.addFlashAttribute("message3", "appid " + appid + " is deleted ");
		return mv;
		

	}
	@RequestMapping(value="admin/additem",method=RequestMethod.GET)
	public String addproduct(Model model)
	{
		Product product =new Product();
		//model.addAttribute("list",list);
		model.addAttribute("product",product);
		return "admin/additem";
	}
	@RequestMapping(value="admin/additem",method=RequestMethod.POST)
	public ModelAndView addproduct2(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String n1 = request.getParameter("prodname");
		String n2 = request.getParameter("threshold");
		
		System.out.println(n1);
		System.out.println(n2);
	//	
	//	admindao.addlog(n1);
	//	admindao.addlog(n2);
		
		boolean exists = productdao.exists(n1);
		
		if(result.hasErrors() || exists) {
//			Product tmpproduct = new Product();
			ModelAndView mv = new ModelAndView("admin/additem");
			mv.addObject("error", "Product Already Exists!");
			mv.addObject("product", new Product());
			return mv;
		}
			productdao.saveOrUpdate(product);
	//		model.addAttribute("message", "[" + request.getParameter("name") + " successfully added as Doctor!]");
			redirectAttrs.addFlashAttribute("message3", "Product Successfully Added!");
			
	//		return new ModelAndView("redirect:successful");
			return new ModelAndView("redirect:/admin");
	
		}
	@RequestMapping(value="admin/itemdetails/{prodid}")
	public ModelAndView itemdetail (@PathVariable("prodid") int pid,Model model, ModelAndView mv)
	{	System.out.println(pid);
		mv.setViewName("admin/itemdetails");
		List<Product> itemdetail = productdao.getalldetails(pid);
		mv.addObject("ilist",itemdetail);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("admin controller, s="+s);
		
		return mv;
	}
	@RequestMapping("admin/showattendance")
	public String attendance (Model model)
	{
		//ModelAndView mav = new ModelAndView("admin/viewdoctors");
		List<Attendance> list = admindao.attendance();
		String s = (String) model.asMap().get("message3");
		
		System.out.println("admincontroller controller, s="+s);
		model.addAttribute("message", s);
		
		model.addAttribute("ilist", list);

		return "admin/showattendance";

	}
	@RequestMapping("admin/invtrans")
	public ModelAndView invtrans (Model model, ModelAndView mv)
	{
		mv.setViewName("admin/invtrans");
		List<Transaction> list = admindao.invtrans();
		
		mv.addObject("list", list);
		return mv;
	}
	
	
	

}
