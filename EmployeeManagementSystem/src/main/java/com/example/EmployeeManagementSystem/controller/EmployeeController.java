

package com.example.EmployeeManagementSystem.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeServiceInterface;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


@Controller
public class EmployeeController {
	@Autowired
	EmployeeServiceInterface employeeService;
	
	 @PostMapping("/loginPopup")
	    public String loginAdmin(@RequestParam String email,
	                             @RequestParam String password,
	                             HttpSession session,
	                             RedirectAttributes redirectAttributes) {

	        // Replace this with database check later if needed
	        if (email.equals("admin@example.com") && password.equals("admin123")) {
	            session.setAttribute("loggedInUser", email);
	            return "redirect:/employeeData";
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Invalid credentials!");
	            return "redirect:loginPopup";
	        }
	    }

	    // 📄 Show login page (GET)
	    @GetMapping("/lo"
	    		+ "ginPopup")
	    public String showLoginPage() {
	        return "loginPopup"; // Thymeleaf login form page
	    }

	    // 🔐 Protected page - Show Employee Data
	    @GetMapping("/employeeData")
	    public String showEmployeeData(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	        String loggedInUser = (String) session.getAttribute("loggedInUser");

	        if (loggedInUser == null || !loggedInUser.equals("admin@example.com")) {
	            redirectAttributes.addFlashAttribute("error", "You must log in first!");
	            return "redirect:/loginPopup";
	        }

	        List<Employee> employeeList = employeeService.getAllEmployees();
	        model.addAttribute("employees", employeeList);
	        return "Employeetable"; // Thymeleaf page to show employee list
	    }


	
//	//DisplayEmployeeData
//	@GetMapping("employeeData")
//	public String displayEmployeeData(Model model)
//	{
//		List<Employee> employees=employeeService.getAllEmployees();
//		model.addAttribute("employees", employees);
//		return "Employeetable";
//	}
	

	
	//show employee form
	@GetMapping("addEmployee")
	public String addEmployees(Model model) {
		model.addAttribute("addEmployeeData", new Employee());
		return "AddData";
	}
	
	
	//after submit form, for dispaly data in table
	@PostMapping("addEmployee")
	public String submitDisplayData(@ModelAttribute("addEmployeeData")Employee employee,Model model)
	{
		boolean status=employeeService.displayData(employee);
		
		if(status)
		{
			model.addAttribute("successmsg","registeration done successfully");
			}
			else
			{
				model.addAttribute("errorsmsg","registeration not done successfully");
			}
		    return "redirect:/employeeData";
	}
	
	
	//delete data
	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Integer id)
	{
		employeeService.deleteEmployeeById(id);
		return "redirect:/employeeData";
	}
	
	
	//show update form
	@GetMapping("updateEmployee/{id}")
	public String showUpdateForm(@PathVariable Integer id,Model model)
	{
		Employee employee=employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "updateEmployee";
	}
	
	//update employee
	@PostMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable Integer id,@ModelAttribute("employee") Employee updatedEmployee)
	{
		employeeService.updateEmployee(id, updatedEmployee);
		return "redirect:/employeeData";
	}
	
	// Download employee report (CSV)
    @GetMapping("/employee/download")
    public ResponseEntity<byte[]> downloadEmployeeReport() {
        List<Employee> employees = employeeService.getAllEmployees();

        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos))) {
           
            writer.println("ID, Name, Email, Department, Position, Salary, Address");

           
            for (Employee employee : employees) {
                writer.println(String.format("%d, %s, %s, %s, %s, %.2f, %s",
                        employee.getId(), employee.getName(), employee.getEmail(),
                        employee.getDepartment(), employee.getPosition(),
                        employee.getSalary(), employee.getAddress()));
            }
            writer.flush();
        }

    
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee_report.csv");

     
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
    

    @GetMapping("/employee/download/pdf"
    		+ "")
    public ResponseEntity<byte[]> downloadPDF() throws Exception {
    
        List<Employee> employees = employeeService.getAllEmployees();

       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        
        PdfWriter writer = new PdfWriter(baos);

       
        PdfDocument pdfDocument = new PdfDocument(writer);

      
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Employee Report"));


        document.add(new Paragraph("\n"));


        for (Employee employee : employees) {
            document.add(new Paragraph("ID: " + employee.getId()));
            document.add(new Paragraph("Name: " + employee.getName()));
            document.add(new Paragraph("Email: " + employee.getEmail()));
            document.add(new Paragraph("Department: " + employee.getDepartment()));
            document.add(new Paragraph("Position: " + employee.getPosition()));
            document.add(new Paragraph("Salary: " + employee.getSalary()));
            document.add(new Paragraph("Address: " + employee.getAddress()));
            document.add(new Paragraph("\n"));
        }

       
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee_report.pdf");

        
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
    
    //when  click on employee data for that security first ask login
//    @GetMapping("/check-login")
//    public String checkLogin(HttpSession session, RedirectAttributes redirectAttributes) {
//        if (session.getAttribute("loggedInUser") != null) {
//            return "redirect:/employeeData"; // If logged in, go to Employee Data
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Please log in first!");
//            return "redirect:/loginPage"; // If not logged in, go to login
//        }
//    }
    
    @GetMapping("/filterEmployees")
    public String filterEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNo,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) Double salary,
            @RequestParam(required = false) String salaryCondition,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String joiningDate,
            Model model,
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/loginPage";
        }

        List<Employee> filteredEmployees = employeeService.filterEmployees(
                name, email, phoneNo, department, position, salary, salaryCondition, address, joiningDate
        );

        model.addAttribute("employees", filteredEmployees);
        return "Employeetable";
    }
    
    @GetMapping("/employee/idcard/{id}")
    public String showIdCard(@PathVariable Integer id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Only allow access if logged in
        if (session.getAttribute("loggedInUser") == null) {
            redirectAttributes.addFlashAttribute("error", "Please login to view ID card.");
            return "redirect:/loginPopup";
        }

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            redirectAttributes.addFlashAttribute("error", "Employee not found.");
            return "redirect:/employeeData";
        }

        model.addAttribute("employee", employee);
        return "idCard";
    }

    
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
  

   
    @GetMapping("/department/index")
    public String home() {
        return "department/index"; // maps to templates/department/index.html
    }

    
  
}
