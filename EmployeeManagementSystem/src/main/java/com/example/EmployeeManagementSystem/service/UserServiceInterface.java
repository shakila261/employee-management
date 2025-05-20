package com.example.EmployeeManagementSystem.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.EmployeeManagementSystem.entity.User;
import com.example.EmployeeManagementSystem.entity.Employee;

public interface UserServiceInterface {

	public boolean checkRegister(User user);
	
	public User loginPage(String email,String password );
	

//	
	  String registerUser(User user);

//	public User authenticate(String email, String password);

}
