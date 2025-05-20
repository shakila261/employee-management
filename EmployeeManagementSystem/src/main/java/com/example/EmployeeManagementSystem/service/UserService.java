package com.example.EmployeeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeManagementSystem.entity.User;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userrepository;
	
	@Override
	public boolean checkRegister(User user) {
		try {
			userrepository.save(user);
			return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public User loginPage(String email, String password) {
		
		User validUser=userrepository.findByEmail(email);
		
		if(validUser!=null && validUser.getPassword().equals(password))
		{
			return validUser;
		}
		
		return null;
	}

	@Override
	public String registerUser(User user) {
		   if (userrepository.existsByEmail(user.getEmail())) {
	            return "User already registered with this email!";
	        }
	        userrepository.save(user);
	        return "Registration successful!";
	    }

//	@Override
//	public User authenticate(String email, String password) {
//		 return userrepository.findByEmailAndPassword(email, password);
//	}
	}
	

	
	

	


