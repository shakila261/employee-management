package com.example.EmployeeManagementSystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EmployeeManagementSystem.entity.User;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.UserServiceInterface;

@Controller
public class UserController
{
	@Autowired
	UserServiceInterface userservice;
	
	
//	@GetMapping("Frontend")
//	public String employeeFrontend(Model model)
//	{
//		model.addAttribute("employee",new User());
//		return "employeeFrontend";
//	}

	
	@GetMapping("register")
	public String userRegisteration(Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String checkRegister(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
	{
		
		    if (result.hasErrors()) {
		        return "register";  
		    }

		    boolean status = userservice.checkRegister(user);
		    if (status) {
		        model.addAttribute("successmsg", "Registration done successfully");
		    } else {
		        model.addAttribute("errorsmsg", "You have already registered");
		    }
		    return "register";
		}

		
		
	
	
	@GetMapping("loginPage")
	public String login(Model model)
	{
		model.addAttribute("user", new User());
		return "loginPage";
	}
	
	 @PostMapping("loginPage")
	    public String submitLoginPage(@ModelAttribute("user") User user, HttpSession session, Model model) {
	        User valid = userservice.loginPage(user.getEmail(), user.getPassword());

	        if (valid != null) {
//	            session.setAttribute("loggedInUser", valid); // ✅ Store user session
	            return "redirect:/employeeData"; // ✅ Redirect to EmployeeController
	        } else {
	            model.addAttribute("errormsg", "Invalid email or password");
	            return "loginPage";
	        }
	    }
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		
		if(session!=null)
		{
			session.invalidate();
		}
		return "logout";
	}

	
	

}
