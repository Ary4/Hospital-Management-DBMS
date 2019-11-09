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
import com.arya.dao.Staffdao;
import com.arya.dao.Userdao;
import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Inventory;
import com.arya.model.ProdExt;
import com.arya.model.Product;
import com.arya.model.Schedule;
import com.arya.model.Staff;
import com.arya.model.Transaction;
import com.arya.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Controller
public class StaffController {

	@Autowired
	public Inventorydao inventorydao;
	@Autowired
	public Admindao admindao;
	@Autowired
	public Productdao productdao;
	@Autowired
	public Staffdao staffdao;
	@Autowired
	public Userdao userdao;
	
	@RequestMapping(value="staff/viewinventory")
	public ModelAndView showinventory (Model model, ModelAndView mv)
	{
		mv.setViewName("staff/viewinventory");
		List<ProdExt> inventorylist = staffdao.getallitems();
		
		
		for (int i = 0; i < inventorylist.size(); i++) {
			int limit = productdao.getThreshold(inventorylist.get(i).getProdid());
			inventorylist.get(i).setLimit(limit);
			if (inventorylist.get(i).getQuantity() <= limit) {
				inventorylist.get(i).setLow("YES");
			} else {
				inventorylist.get(i).setLow("NO");
			}
		}
		
		mv.addObject("ilist",inventorylist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("viewstaff controller, s="+s);
		
		return mv;
	}
	
	@RequestMapping(value="staff/additem",method=RequestMethod.GET)
	public String addproduct(Model model)
	{
		Product product =new Product();
		//model.addAttribute("list",list);
		model.addAttribute("product",product);
		return "staff/additem";
	}
	
	@RequestMapping(value="staff/additem",method=RequestMethod.POST)
	public ModelAndView registerProcess2(@Valid @ModelAttribute("product") Product product,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
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
			ModelAndView mv = new ModelAndView("staff/additem");
			mv.addObject("error", "Product Already Exists!");
			mv.addObject("product", new Product());
			return mv;
		}
		else {
//			
			productdao.saveOrUpdate(product);
	//		model.addAttribute("message", "[" + request.getParameter("name") + " successfully added as Doctor!]");
			redirectAttrs.addFlashAttribute("message3", "Product Successfully Added!");
			
	//		return new ModelAndView("redirect:successful");
			return new ModelAndView("redirect:/staff");
	
		}
	}
	
	
	@RequestMapping(value="staff/fillitem/{prodid}",method=RequestMethod.GET)
	public String transactplus (@PathVariable("prodid") int pid, Model model)
	{
		System.out.println(pid);
		//Product product =new Product();
		int count = productdao.get_quantity(pid);

		Transaction transaction = new Transaction();
		transaction.setProdid(pid);
		transaction.setQuantity(count);		
		
		model.addAttribute("transaction",transaction);
		return "staff/fillitem";
	}
	@RequestMapping(value="staff/fillitem",method=RequestMethod.POST)
	public ModelAndView additem(Principal principal, @Valid @ModelAttribute("transaction") Transaction transaction,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String staffid = principal.getName();
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String comments = request.getParameter("comments");
		int pid = Integer.parseInt(request.getParameter("prodid"));
		
		System.out.println("Take Item Post");
		System.out.println("PID:"+pid);
		System.out.println(quantity);
		System.out.println(comments);
		System.out.println("Staffid:" + staffid);
		
		productdao.updatep(pid, quantity, staffid, comments);
		
		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of product id " + pid + " added!");
		return new ModelAndView("redirect:/staff/viewinventory");
	}
	
	@RequestMapping(value="staff/takeitem/{prodid}",method=RequestMethod.GET)
	public String transactminus (@PathVariable("prodid") int pid, Model model)
	{
		System.out.println(pid);
		//Product product =new Product();
		int count = productdao.get_quantity(pid);

		Transaction transaction = new Transaction();
		transaction.setProdid(pid);
		transaction.setQuantity(count);		
		
		model.addAttribute("transaction",transaction);
		return "staff/takeitem";
	}
	
	
	@RequestMapping(value="staff/takeitem",method=RequestMethod.POST)
	public ModelAndView removeitem(Principal principal, @Valid @ModelAttribute("transaction") Transaction transaction,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String staffid = principal.getName();
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String comments = request.getParameter("comments");
		int pid = Integer.parseInt(request.getParameter("prodid"));
		
		System.out.println("Take Item Post");
		System.out.println("PID:"+pid);
		System.out.println(quantity);
		System.out.println(comments);
		System.out.println("Staffid:" + staffid);
		
		productdao.updatep(pid, -1*quantity, staffid, comments);
		
		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of product id " + pid + " removed!");
		return new ModelAndView("redirect:/staff/viewinventory");
	}
	@RequestMapping(value="staff/itemdetails/{prodid}")
	public ModelAndView itemdetail (@PathVariable("prodid") int pid,Model model, ModelAndView mv)
	{	System.out.println(pid);
		mv.setViewName("staff/itemdetails");
//		List<Inventory> inventorylist = inventorydao.getallitems();
		List<Product> itemdetail = productdao.getalldetails(pid);
		mv.addObject("ilist",itemdetail);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("viewstaff controller, s="+s);
		
		return mv;
	}
	
	//
	@RequestMapping(value="staff/showapp")
	public ModelAndView showapp(ModelAndView mv )
	{	//String pid =principal.getName();
		List<Appointment> applist = staffdao.showapp();
		mv.setViewName("staff/showapp");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="staff/showappprev")
	public ModelAndView showappprev(ModelAndView mv )
	{	//String pid =principal.getName();
		List<Appointment> applist = staffdao.showappprev();
		mv.setViewName("staff/showappprev");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="staff/uploaddata/{appid}",method=RequestMethod.GET)
	public String uploaddata(Model model,@PathVariable("appid") int appid )
	{
		AppointmentDetails appointmentdetails =new AppointmentDetails();
		//model.addAttribute("list",list);
		model.addAttribute("appointmentdetails",appointmentdetails);
		model.addAttribute("storethis", appid);
		return "staff/uploaddata";
	}
	@RequestMapping(value="staff/uploaddata/{appid}",method=RequestMethod.POST)
	public ModelAndView uploadfile( @Valid @ModelAttribute("appointmentdetails") AppointmentDetails appointmentdetail,@PathVariable("appid") int appid,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		//int appid = Integer.parseInt(request.getParameter("storethis"));
		String details = request.getParameter("details");		
		staffdao.uploaddata(appid, details);	
		redirectAttrs.addFlashAttribute("message3", "Appointment id " + appid + "'s details have been uploaded");
		return new ModelAndView("redirect:/staff");
	}
	//copied from usercontroller,didnt change much but added userdao and getpid request
	@RequestMapping(value="staff/bookapp",method=RequestMethod.GET)
	public String bookapp(Model model)
	{
		Appointment appointment =new Appointment();
		//model.addAttribute("list",list);
		model.addAttribute("appointment",appointment);
		return "staff/bookapp";
	}
	@RequestMapping(value="staff/bookapp",method=RequestMethod.POST)
	public ModelAndView additem(@Valid @ModelAttribute("appointment") Appointment appointment,BindingResult result,Model model, HttpServletRequest request,ModelAndView mv, RedirectAttributes redirectAttrs) {
		
		String pid = request.getParameter("pid");
		int time = Integer.parseInt(request.getParameter("time"));
		String department = request.getParameter("department");
		int d = Integer.parseInt(request.getParameter("d"));
		int m = Integer.parseInt(request.getParameter("m"));
		int y = Integer.parseInt(request.getParameter("y"));
		List<Schedule> ss= userdao.searchsche(d, m, y, time, department);
		mv.setViewName("staff/showresult");
		mv.addObject("ilist",ss);
		//after this is not needed for retrieval
		mv.addObject("pid",pid);
		mv.addObject("time",time);
		mv.addObject("department", department);
		mv.addObject("d",d);
		mv.addObject("m",m);
		mv.addObject("y", y);
		
		
//		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of product id " + pid + " added!");
		return mv;
	}
	@RequestMapping(value="staff/confirmapp/{doctorid}+{d}+{m}+{y}+{department}+{time}+{pid}")
	public ModelAndView itemdetail (@PathVariable("doctorid") String doctorid,@PathVariable("d") int d,@PathVariable("m") int m,@PathVariable("y") int y,@PathVariable("department") String department,@PathVariable("time") int time,@PathVariable("pid") String pid,Model model,RedirectAttributes redirectAttrs, ModelAndView mv)
	{	System.out.println(doctorid);
		userdao.bookapp(d, m, y, time, doctorid, department, pid);		
		redirectAttrs.addFlashAttribute("message3", "Appointment booked on " +d+ "-" +m+ "-"+y+" time "+time+" with doctor " + doctorid + "for patient "+pid+" !");
		return new ModelAndView("redirect:/staff");
	}
	
}
