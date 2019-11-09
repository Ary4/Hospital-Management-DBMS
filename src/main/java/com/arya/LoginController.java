package com.arya;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arya.dao.Admindao;
import com.arya.dao.Userdao;
import com.arya.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Controller
public class LoginController {

	@Autowired
	public Userdao userdao;
	@Autowired
	public Admindao admindao;
	
	@RequestMapping(value = "/home")
	public String welcome(Model model) {
		model.addAttribute("name", "Home Page");
		model.addAttribute("description", "Page yet to be completed!");
		return "home";
	}

	@RequestMapping("/admin")
	public String admin(Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", " " + loggedInUserName);
//		model.addAttribute("name", "Spring Security Custom Login Demo");
//		model.addAttribute("description", "Protected page !");
		
		String flash1 = (String) model.asMap().get("message3");
		System.out.println("viewdoctor controller, s="+flash1);
		model.addAttribute("message", flash1); 		// This flash attribute is used to show successfull mesage from adddoctor/staff
		return "admin/profile";
	}
	@RequestMapping("/pharma")
	public String pharma(Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", " " + loggedInUserName);
//		model.addAttribute("name", "Spring Security Custom Login Demo");
//		model.addAttribute("description", "Protected page !");
		
		String flash1 = (String) model.asMap().get("message3");
		System.out.println("in login controller pharma, s="+flash1);
		model.addAttribute("message", flash1); 		// This flash attribute is used to show successfull mesage from adddoctor/staff
		return "pharma/profile";
	}
	@RequestMapping("/user")
	public String user (Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", loggedInUserName);
		
//		model.addAttribute("name", "Spring Security USER Custom Login Demo");
//		model.addAttribute("description", "Protected page for user !");
		String flash1 = (String) model.asMap().get("message3");
		System.out.println("in login controller, s="+flash1);
		model.addAttribute("message", flash1);
		return "user/profile";
	}
	
	@RequestMapping("/doctor")
	public String doctor (Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		userdao.attendance(loggedInUserName);
		model.addAttribute("user", loggedInUserName);
		String flash1 = (String) model.asMap().get("message3");
		System.out.println("in login controller, s="+flash1);
		model.addAttribute("message", flash1);
		return "doctor/profile";
	}
	
	@RequestMapping("/staff")
	public String staff (Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		userdao.attendance(loggedInUserName);
		model.addAttribute("user", loggedInUserName);
//		model.addAttribute("name", "Spring Security USER Custom Login Demo");
//		model.addAttribute("description", "Protected page for user !");
		String flash1 = (String) model.asMap().get("message3");
		System.out.println("in logincontroller, s="+flash1);
		model.addAttribute("message", flash1);
		return "staff/profile";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response, Authentication authentication) {
//		
//		Principal principal=request.getUserPrincipal();
//		if(principal!=null)
//		{
//			String loggedInUserName = principal.getName();
//			model.addAttribute("user", loggedInUserName);
//			Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//		    if (authorities.contains("ROLE_ADMIN")) {
//		    	return "redirect:/admin";
//		    } else if (authorities.contains("ROLE_USER")) {
//		    	return "redirect:/user";
//		    } else {
//		        throw new IllegalStateException();
//		    }
//		// out.println("<form action=\"login\"><input type=\"submit\" value=\"Login\"><form>");
//		}
//		
//
//		return "login";
//
//	}

	//idher use kiya to redirect already logged in users to usser/admin page 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		
		Principal principal=request.getUserPrincipal();
		if(principal!=null)
		{
			String loggedInUserName = principal.getName();
//			model.addAttribute("user", loggedInUserName);
			Set <String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		    if (authorities.contains("ROLE_ADMIN")) {
		    	ModelAndView mav = new ModelAndView("redirect:/admin");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    } else if (authorities.contains("ROLE_USER")) {
		    	ModelAndView mav = new ModelAndView("redirect:/user");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    }
		    else if (authorities.contains("ROLE_DOCTOR")) {
		    	ModelAndView mav = new ModelAndView("redirect:/doctor");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    }
		    else if (authorities.contains("ROLE_STAFF")) {
		    	ModelAndView mav = new ModelAndView("redirect:/staff");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    }else {
		        throw new IllegalStateException();
		    }
		// out.println("<form action=\"login\"><input type=\"submit\" value=\"Login\"><form>");
		}
		
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
//		model.addAttribute("message",
//				"You have successfully logged off from application !");
		return "logout";

	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";

	}
	//idher already logged in users ko firse register karne se rokne ka kaam kiya h using prinipal
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(Model model, HttpServletRequest request, Authentication authentication) {
		
		Principal principal=request.getUserPrincipal();
		if(principal!=null)
		{
			String loggedInUserName = principal.getName();
//			model.addAttribute("user", loggedInUserName);
			Set <String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		    if (authorities.contains("ROLE_ADMIN")) {
		    	ModelAndView mav = new ModelAndView("redirect:/admin");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    } else if (authorities.contains("ROLE_USER")) {
		    	ModelAndView mav = new ModelAndView("redirect:/user");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    } 
		    else if (authorities.contains("ROLE_DOCTOR")) {
		    	ModelAndView mav = new ModelAndView("redirect:/doctor");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    }
		    else if (authorities.contains("ROLE_STAFF")) {
		    	ModelAndView mav = new ModelAndView("redirect:/staff");
		    	mav.addObject("user", loggedInUserName);
		    	return mav;
		    }else {
		        throw new IllegalStateException();
		    }
		// out.println("<form action=\"login\"><input type=\"submit\" value=\"Login\"><form>");
		}
		
		User user = new User();		
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", user);
		return mav;
	}
	
	boolean hasspace (String s) {
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)==' ') {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerProcess(@Valid @ModelAttribute("user") User user,BindingResult result,Model model, HttpServletRequest request) {
		
		String n1 = request.getParameter("name");
		String n2 = request.getParameter("phone");
		System.out.println(n1);
		System.out.println(n2);
//		
//		admindao.addlog(n1);
//		admindao.addlog(n2);
		
		if(result.hasErrors()) {
			return "register";
		}
		else {
			if(!user.getPassword().equals(user.getMpassword())) {
				model.addAttribute("error","passwords dont match");
				return "register";
			}
			if (hasspace(user.getUsername())) {
				model.addAttribute("error", "Username cant have space");
				return "register";
			}
			if(userdao.getUser(user.getUsername())!=null) {
				model.addAttribute("error", "username exists");
				return "register";
			}
			userdao.saveOrUpdate(user);
			return "redirect: login";
		}
	}
	
	@RequestMapping(value="/user/products")
	public String checker(Model model,Principal principal) {
		String user = principal.getName();
		model.addAttribute("user",user);
		model.addAttribute("product","product");
		return "product";
	}
	
}
