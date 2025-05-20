package com.example.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployeeManagementSystem.entity.User;
import com.example.EmployeeManagementSystem.entity.Employee;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	

	User findByEmail(String email);

	void save(Employee employeeData);

	boolean existsByEmail(String email);

//	User findByEmailAndPassword(String email, String password);

	

	

	

}
