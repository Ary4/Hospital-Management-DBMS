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
import com.arya.dao.Pharmadao;
import com.arya.dao.Productdao;
import com.arya.dao.Userdao;
import com.arya.dao.Doctordao;

import com.arya.model.Inventory;
import com.arya.model.Medicine;
import com.arya.model.OrderContains;
import com.arya.model.Orders;
import com.arya.model.Patient;
import com.arya.model.Product;
import com.arya.model.Schedule;
import com.arya.model.Staff;
import com.arya.model.Transaction;
import com.arya.model.User;
import com.arya.model.Doctor;
import com.arya.model.Appointment;
import com.arya.model.AppointmentDetails;
import com.arya.model.Cart;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Controller
public class UserController {

	@Autowired
	public Userdao userdao;
	@Autowired
	public Pharmadao pharmadao;
	@RequestMapping(value="user/bookapp",method=RequestMethod.GET)
	public String bookapp(Model model)
	{
		Appointment appointment =new Appointment();
		//model.addAttribute("list",list);
		model.addAttribute("appointment",appointment);
		return "user/bookapp";
	}
	@RequestMapping(value="user/bookapp",method=RequestMethod.POST)
	public ModelAndView additem(Principal principal, @Valid @ModelAttribute("appointment") Appointment appointment,BindingResult result,Model model, HttpServletRequest request,ModelAndView mv, RedirectAttributes redirectAttrs) {
		
		String pid = principal.getName();
		int time = Integer.parseInt(request.getParameter("time"));
		String department = request.getParameter("department");
		int d = Integer.parseInt(request.getParameter("d"));
		int m = Integer.parseInt(request.getParameter("m"));
		int y = Integer.parseInt(request.getParameter("y"));
		List<Schedule> ss= userdao.searchsche(d, m, y, time, department);
		mv.setViewName("user/showresult");
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
	@RequestMapping(value="user/confirmapp/{doctorid}+{d}+{m}+{y}+{department}+{time}+{pid}")
	public ModelAndView itemdetail (@PathVariable("doctorid") String doctorid,@PathVariable("d") int d,@PathVariable("m") int m,@PathVariable("y") int y,@PathVariable("department") String department,@PathVariable("time") int time,@PathVariable("pid") String pid,Model model,RedirectAttributes redirectAttrs, ModelAndView mv)
	{	System.out.println(doctorid);
		//mv.setViewName("user");
//		List<Inventory> inventorylist = inventorydao.getallitems();
//		List<Product> itemdetail = productdao.getalldetails(pid);
//		mv.addObject("ilist",itemdetail);
		userdao.bookapp(d, m, y, time, doctorid, department, pid);		
		redirectAttrs.addFlashAttribute("message3", "Appointment booked on " +d+ "-" +m+ "-"+y+" time "+time+" with doctor " + doctorid + " !");
		return new ModelAndView("redirect:/user");
	}
	@RequestMapping(value="user/showapp")
	public ModelAndView showapp(Principal principal ,ModelAndView mv )
	{	String pid =principal.getName();
		List<Appointment> applist = userdao.showapp(pid);
		mv.setViewName("user/showapp");
		mv.addObject("ilist", applist);
	
		return mv;
	}
	@RequestMapping(value="user/deleteapp/{appid}")
	public ModelAndView deleteapp(Principal principal,@PathVariable("appid") int appid ,ModelAndView mv,RedirectAttributes redirectAttrs)
	{	
		String pid =principal.getName();
		userdao.deleteapp(appid, pid);
		redirectAttrs.addFlashAttribute("message3", "appid " + appid + " is deleted ");
		return new ModelAndView("redirect:/user");
		

	}
	@RequestMapping(value="user/showappdetails/{appid}")
	public ModelAndView showdetails(Principal principal,@PathVariable("appid") int appid ,ModelAndView mv,RedirectAttributes redirectAttrs)
	{	
		String pid =principal.getName();
		mv.setViewName("user/showappdetails");
		List<AppointmentDetails> a=userdao.showappdetails(appid, pid);
		mv.addObject("ilist", a);
		//redirectAttrs.addFlashAttribute("message3", "appid " + appid + " is deleted ");
		return mv;
		

	}
	@RequestMapping(value="user/market")
	public ModelAndView market (Model model, ModelAndView mv)
	{
		mv.setViewName("user/market");
		List<Medicine> medicinelist = pharmadao.getallitems();		
		mv.addObject("ilist",medicinelist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("now in user controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="user/itemquantity/{medid}",method=RequestMethod.GET)
	public String itemquant(@PathVariable("medid") String medid,Model model)
	{	int cnt=pharmadao.get_quantity(medid);
		System.out.println(cnt);
		Medicine medicine =new Medicine();
		model.addAttribute("medicine",medicine);
		model.addAttribute("cnt",cnt);
		return "user/itemquantity";
	}
	@RequestMapping(value="user/itemquantity",method=RequestMethod.POST)
	public ModelAndView itemquantity2(Principal principal, @Valid @ModelAttribute("medicine") Medicine medicine,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String medid =request.getParameter("medid");
		String bookerid=principal.getName();
		int price=pharmadao.get_price(medid);
		pharmadao.addtocart(bookerid,medid,quantity,price);
		//pharmadao.updatep(medid,quantity); dont do this unless the order is placed
		
		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of medicine id " + medid + " added to cart!");
		return new ModelAndView("redirect:/user/market");
	}
	@RequestMapping(value="user/showcart")
	public ModelAndView cart (Model model, ModelAndView mv,Principal principal,RedirectAttributes redirectAttrs)
	{	String bookerid =principal.getName();
		int val=pharmadao.iscartempty(bookerid);
		if(val==0)
		{
			redirectAttrs.addFlashAttribute("message3", "Cart is already empty,cant show");
			return new ModelAndView("redirect:/user/market");
		}
		mv.setViewName("user/showcart");
		List<Cart> cartitems = pharmadao.getcartitems(bookerid);		
		mv.addObject("ilist",cartitems);
		
		int totalcost=pharmadao.gettotalcost(bookerid);
		mv.addObject("totalprice", totalcost);
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="user/checkout",method=RequestMethod.GET)
	public String chck(Model model)
	{	
		Orders orders =new Orders();
		model.addAttribute("orders",orders);
		return "user/checkout";
	}
	@RequestMapping(value="user/checkout",method=RequestMethod.POST)
	public ModelAndView check4(Principal principal, @Valid @ModelAttribute("orders") Orders orders,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String address =request.getParameter("address");
		String bookerid=principal.getName();
		pharmadao.placeorder(bookerid,address);
		
		redirectAttrs.addFlashAttribute("message3", " Order Placed");
		return new ModelAndView("redirect:/user/market");
	}
	@RequestMapping(value="user/showorders")
	public ModelAndView orders (Model model, ModelAndView mv,Principal principal)
	{	String bookerid=principal.getName();
		mv.setViewName("user/showorders");
		List<Orders> orderlist = userdao.getallorders(bookerid);		
		mv.addObject("ilist",orderlist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("user controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="user/orderdetails/{orderid}")
	public ModelAndView orders (@PathVariable("orderid") int orderid,Model model, ModelAndView mv)
	{
		mv.setViewName("user/orderdetails");
		List<OrderContains> orderlist = pharmadao.orderdetails(orderid);		
		mv.addObject("ilist",orderlist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("user controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="user/details")
	public ModelAndView viewdetails (Principal principal) {
		String username = principal.getName();
		
		Patient data = userdao.getdata(username);
		
		ModelAndView mv = new ModelAndView("user/details");
		mv.addObject("data", data);
		
		return mv;
	}
	@RequestMapping(value="user/updatedetails",method=RequestMethod.GET)
	public String updateprof(Model model)
	{
		Patient patient =new Patient();
		//model.addAttribute("list",list);
		model.addAttribute("patient",patient);
		return "user/updatedetails";
	}
	@RequestMapping(value="user/updatedetails",method=RequestMethod.POST)
	public ModelAndView updateprof2( @Valid @ModelAttribute("patient")Patient patient,Principal principal,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		//int quantity = Integer.parseInt(request.getParameter("quantity"));
		String u1=principal.getName();
		System.out.println(u1);
		userdao.updateprofile(patient, u1);
		redirectAttrs.addFlashAttribute("message3", "Profile Succesfully updated ");
		return new ModelAndView("redirect:/user");
	}
	@RequestMapping(value="user/showprevapp")
	public ModelAndView appdetailsz (Model model, ModelAndView mv,Principal principal)
	{	String pid=principal.getName();
		mv.setViewName("user/showprevapp");
		List<AppointmentDetails> list = userdao.showprevapp(pid);		
		mv.addObject("ilist",list);		
		return mv;
	}
}