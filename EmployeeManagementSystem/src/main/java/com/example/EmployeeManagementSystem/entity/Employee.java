package com.example.EmployeeManagementSystem.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotBlank(message="Full Name is required")
	@Size(min=2,max=50,message="Full Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	private String email;
	
	@NotBlank(message = "department is required")
	private String department;
	
	@NotBlank(message = "position is required")
	private String position;
	
	@NotBlank(message = "address is required")
	private String address;
	
	@NotNull(message = "salary is required")
	@DecimalMin(value = "0", inclusive = false, message = "Salary must be greater than 0")
	private double salary;
	

	@PastOrPresent(message = "Joining Date cannot be in the future")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;
	
	@NotBlank(message = "Phone Number is required")
//	@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be 10 digits")
	
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be 10 digits")
	private String phoneNo;

	


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}


	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}


	/**
	 * @param deparment the department to set
	 */
	public void setDepartment(String deparment) {
		this.department = deparment;
	}


	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}


	/**
	 * @return the joiningDate
	 */
	public LocalDate getJoiningDate() {
		return joiningDate;
	}


	/**
	 * @param joiningDate the joiningDate to set
	 */
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	

}
