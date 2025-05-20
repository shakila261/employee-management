package com.example.EmployeeManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	EmployeeRepository employeerepository;
	

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeerepository.findAll();
	}

	
	@Override
	public boolean displayData(Employee employee) {
		try {
			employeerepository.save(employee);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public void deleteEmployeeById(Integer id) {
		employeerepository.deleteById(id);
		
		
	}

	public Employee getEmployeeById(Integer id)
	{
		return employeerepository.findById(id).orElseThrow();
		
	}


	@Override
	public void updateEmployee(Integer id, Employee updatedEmployee) {
		Employee existEmployee=getEmployeeById(id);
		
		 existEmployee.setName(updatedEmployee.getName());
	        existEmployee.setEmail(updatedEmployee.getEmail());
	        existEmployee.setPhoneNo(updatedEmployee.getPhoneNo());
	        existEmployee.setDepartment(updatedEmployee.getDepartment());
	        existEmployee.setPosition(updatedEmployee.getPosition());
	        existEmployee.setSalary(updatedEmployee.getSalary());
	        existEmployee.setAddress(updatedEmployee.getAddress());
	        existEmployee.setJoiningDate(updatedEmployee.getJoiningDate());

	        employeerepository.save(existEmployee);
		
		
	}


	@Override
	public List<Employee> filterEmployees(String name, String email, String phoneNo, String department,
	                                      String position, Double salary, String salaryCondition,
	                                      String address, String joiningDate) {
	    return employeerepository.findAll().stream()
	            .filter(emp -> (name == null || name.isEmpty() || emp.getName().toLowerCase().contains(name.toLowerCase())))
	            .filter(emp -> (email == null || email.isEmpty() || emp.getEmail().toLowerCase().contains(email.toLowerCase())))
	            .filter(emp -> (phoneNo == null || phoneNo.isEmpty() || emp.getPhoneNo().contains(phoneNo)))
	            .filter(emp -> (department == null || department.isEmpty() || emp.getDepartment().toLowerCase().contains(department.toLowerCase())))
	            .filter(emp -> (position == null || position.isEmpty() || emp.getPosition().toLowerCase().contains(position.toLowerCase())))
	            .filter(emp -> {
	                if (salary == null) return true;
	                if ("greater".equalsIgnoreCase(salaryCondition)) {
	                    return emp.getSalary() > salary;
	                } else if ("less".equalsIgnoreCase(salaryCondition)) {
	                    return emp.getSalary() < salary;
	                } else {
	                    return Double.compare(emp.getSalary(), salary) == 0;
	                }
	            })

	            .filter(emp -> (address == null || address.isEmpty() || emp.getAddress().toLowerCase().contains(address.toLowerCase())))
	            .filter(emp -> (joiningDate == null || joiningDate.isEmpty() || emp.getJoiningDate().toString().equals(joiningDate)))
	            .toList();
	}



	



}
