package com.example.EmployeeManagementSystem.service;

import java.util.List;

import com.example.EmployeeManagementSystem.entity.Employee;

public interface EmployeeServiceInterface {
	
	public List<Employee> getAllEmployees();
	
	public boolean displayData(Employee employee);
	
	public void deleteEmployeeById(Integer id);
	
	Employee getEmployeeById(Integer Id);
	
	public void updateEmployee(Integer id,Employee updatedEmployee);
	
	List<Employee> filterEmployees(String name, String email, String phoneNo, String department,
            String position, Double salary, String salaryCondition,
            String address, String joiningDate);



	
	
	

}
