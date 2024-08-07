package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Myapp")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/list")
    public String listEmployeesForAdmin(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees", theEmployees);
        return "list-employees";
    }

    @GetMapping("/listForEmployee")
    public String listEmployeesFormToEmployee(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);
        return "list-employees-For-Employee";
    }

    @GetMapping("/listForManager")
    public String listEmployeesFormToManager(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);
        return "list-employees-For-Manager";
    }

    @GetMapping("/Employees/new")
    public String createEmployeeForm(Model themodel) {
        Employee Employee = new Employee();
        themodel.addAttribute("employee", Employee);
        return "Employee-form";

    }

    @GetMapping("/Employees/newEmployeeForManager")
    public String createEmployeeFormFromManager(Model themodel) {
        Employee Employee = new Employee();
        themodel.addAttribute("employee", Employee);
        return "Employee-form-from-manager";

    }

    @PostMapping("/listForManager")
    public String saveStudentFromManager(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/listForManager";
    }

    @PostMapping("/list")
    public String saveStudent(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable int id, Model model) {
        Employee employeeById = employeeService.findById(id);
        model.addAttribute("employee", employeeById);
        return "edit_employee";
    }

    @GetMapping("/employees/editForManager/{id}")
    public String editEmployeeFormForManager(@PathVariable int id, Model model) {
        Employee employeeById = employeeService.findById(id);
        model.addAttribute("employee", employeeById);
        return "edit_employee_For-manager";
    }

    @PostMapping("/employeesForManager/{id}")
    public String updateStudentForManager(@PathVariable int id, @ModelAttribute("employee") Employee employee, Model model) {
        Employee existingStudent = employeeService.findById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(employee.getFirstName());
        existingStudent.setLastName(employee.getLastName());
        existingStudent.setEmail(employee.getEmail());
        employeeService.save(existingStudent);
        return "redirect:/listForManager";
    }

    @PostMapping("/employees/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute("employee") Employee employee, Model model) {

        Employee existingStudent = employeeService.findById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(employee.getFirstName());
        existingStudent.setLastName(employee.getLastName());
        existingStudent.setEmail(employee.getEmail());

        employeeService.save(existingStudent);
        return "redirect:/list";
    }

    @GetMapping("/employees/{id}")
    public String deleteStudent(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }




}









