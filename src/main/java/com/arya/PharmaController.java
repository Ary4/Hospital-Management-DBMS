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

import com.arya.dao.Pharmadao;
import com.arya.model.Cart;
import com.arya.model.Inventory;
import com.arya.model.Medicine;
import com.arya.model.OrderContains;
import com.arya.model.Orders;
import com.arya.model.Product;
import com.arya.model.Transaction;

@Controller
public class PharmaController {
	
	@Autowired
	public Pharmadao pharmadao;
	@RequestMapping(value="pharma/viewinventory")
	public ModelAndView showinventory (Model model, ModelAndView mv)
	{
		mv.setViewName("pharma/viewinventory");
		List<Medicine> medicinelist = pharmadao.getallitems();
		
		
		for (int i = 0; i < medicinelist.size(); i++) {
			int limit = pharmadao.getThreshold(medicinelist.get(i).getMedid());
			medicinelist.get(i).setThreshold(limit);
			if (medicinelist.get(i).getQuantity() <= limit) {
				medicinelist.get(i).setLow("YES");
			} else {
				medicinelist.get(i).setLow("NO");
			}
		}
		
		mv.addObject("ilist",medicinelist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		return mv;
	}
	@RequestMapping(value="pharma/additem",method=RequestMethod.GET)
	public String addproduct(Model model)
	{
		Medicine medicine =new Medicine();
		//model.addAttribute("list",list);
		model.addAttribute("medicine",medicine);
		return "pharma/additem";
	}
	@RequestMapping(value="pharma/additem",method=RequestMethod.POST)
	public ModelAndView registerProcess2(@Valid @ModelAttribute("medicine") Medicine medicine,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String n1 = request.getParameter("medid");
		String n2 = request.getParameter("threshold");
		
		System.out.println(n1);
		System.out.println(n2);
		
		boolean exists = pharmadao.exists(n1);
		System.out.println(exists);
		if(result.hasErrors() || exists) {
			ModelAndView mv = new ModelAndView("pharma/additem");
			mv.addObject("error", "Medicine Already Exists!");
			mv.addObject("medicine", new Medicine());
			return mv;
		}
		else {
		
			pharmadao.saveOrUpdate(medicine);
			redirectAttrs.addFlashAttribute("message3", "Medicine Successfully Added!");
			return new ModelAndView("redirect:/pharma");
	
		}
	}
	@RequestMapping(value="pharma/fillitem/{medid}",method=RequestMethod.GET)
	public String fillitem(@PathVariable("medid") String medid,Model model)
	{	int cnt=pharmadao.get_quantity(medid);
		System.out.println(cnt);
		Medicine medicine =new Medicine();
		//model.addAttribute("list",list);
		model.addAttribute("medicine",medicine);
		model.addAttribute("cnt",cnt);
		return "pharma/fillitem";
	}
	@RequestMapping(value="pharma/fillitem",method=RequestMethod.POST)
	public ModelAndView additem(Principal principal, @Valid @ModelAttribute("medicine") Medicine medicine,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String medid =request.getParameter("medid");
		
		
		pharmadao.updatep(medid,quantity);
		
		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of medicine id " + medid + " added!");
		return new ModelAndView("redirect:/pharma/viewinventory");
	}
	@RequestMapping(value="pharma/market")
	public ModelAndView market (Model model, ModelAndView mv)
	{
		mv.setViewName("pharma/market");
		List<Medicine> medicinelist = pharmadao.getallitems();		
		mv.addObject("ilist",medicinelist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="pharma/itemquantity/{medid}",method=RequestMethod.GET)
	public String itemquant(@PathVariable("medid") String medid,Model model)
	{	int cnt=pharmadao.get_quantity(medid);
		System.out.println(cnt);
		Medicine medicine =new Medicine();
		model.addAttribute("medicine",medicine);
		model.addAttribute("cnt",cnt);
		return "pharma/itemquantity";
	}
	@RequestMapping(value="pharma/itemquantity",method=RequestMethod.POST)
	public ModelAndView itemquantity2(Principal principal, @Valid @ModelAttribute("medicine") Medicine medicine,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String medid =request.getParameter("medid");
		String bookerid=principal.getName();
		int price=pharmadao.get_price(medid);
		pharmadao.addtocart(bookerid,medid,quantity,price);
		//pharmadao.updatep(medid,quantity); dont do this unless the order is placed
		
		redirectAttrs.addFlashAttribute("message3", "" + quantity + " amount of medicine id " + medid + " added to cart!");
		return new ModelAndView("redirect:/pharma/market");
	}
	@RequestMapping(value="pharma/showcart")
	public ModelAndView cart (Model model, ModelAndView mv,Principal principal,RedirectAttributes redirectAttrs)
	{	String bookerid=principal.getName();
		int val=pharmadao.iscartempty(bookerid);
		if(val==0)
		{
			redirectAttrs.addFlashAttribute("message3", "Cart is already empty,cant show");
			return new ModelAndView("redirect:/pharma/market");
		}
		mv.setViewName("pharma/showcart");
		List<Cart> cartitems = pharmadao.getcartitems(bookerid);		
		mv.addObject("ilist",cartitems);
		int totalcost=pharmadao.gettotalcost(bookerid);
		mv.addObject("totalprice", totalcost);
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="pharma/checkout",method=RequestMethod.GET)
	public String chck(Model model)
	{	
		Orders orders =new Orders();
		model.addAttribute("orders",orders);
		return "pharma/checkout";
	}
	@RequestMapping(value="pharma/checkout",method=RequestMethod.POST)
	public ModelAndView check4(Principal principal, @Valid @ModelAttribute("orders") Orders orders,BindingResult result,Model model, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		String address =request.getParameter("address");
		String bookerid=principal.getName();
		pharmadao.placeorder(bookerid,address);
		
		redirectAttrs.addFlashAttribute("message3", " Order Placed");
		return new ModelAndView("redirect:/pharma/market");
	}
	@RequestMapping(value="pharma/showorders")
	public ModelAndView orders (Model model, ModelAndView mv)
	{
		mv.setViewName("pharma/showorders");
		List<Orders> orderlist = pharmadao.getallorders();		
		mv.addObject("ilist",orderlist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		
		return mv;
	}
	@RequestMapping(value="pharma/orderdetails/{orderid}")
	public ModelAndView orders (@PathVariable("orderid") int orderid,Model model, ModelAndView mv)
	{
		mv.setViewName("pharma/orderdetails");
		List<OrderContains> orderlist = pharmadao.orderdetails(orderid);		
		mv.addObject("ilist",orderlist);
		
		String s = (String) model.asMap().get("message3");
		mv.addObject("message", s);
		System.out.println("pharma controller, s="+s);
		
		
		return mv;
	}
}
