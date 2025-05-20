package com.example.EmployeeManagementSystem.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email")) // Unique constraint on email
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Full Name is required")
   // @Size(min = 2, max = 50, message = "Full Name must be between 2 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
   @Email(message = "Invalid email format")
    @Column(unique = true)  // Ensure uniqueness at DB level
    private String email;

//    @NotBlank(message = "Phone Number is required")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be 10 digits")
//    private String phoneNo;

//    @NotBlank(message = "Gender is required")
//    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
//    private String gender;

    @NotBlank(message = "Password is required")
    private String password;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
