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

@Controller
public class EmployeeController {

	// load employee data

	private EmployeeService employeeService;
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	@GetMapping("/")
	public String showhome() {
		return "home";
	}
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "fancy-login";
	}
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployeesForAdmin(Model theModel) {
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
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
		public String createEmployeeForm(Model themodel){
			Employee Employee = new Employee();
			themodel.addAttribute("employee" , Employee);
			return "Employee-form";

	}
		@GetMapping("/Employees/newEmployeeForManager")
		public String createEmployeeFormFromManager(Model themodel){
			Employee Employee = new Employee();
			themodel.addAttribute("employee" , Employee);
			return "Employee-form-from-manager";

		}
		@PostMapping("/listForManager")
		public String saveStudentFromManager(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);
		return "redirect:/listForManager";
	}
		@PostMapping("/list")
		public String saveStudent(@ModelAttribute("employee") Employee employee){
			employeeService.save(employee);
			return "redirect:/list";
	}
		@GetMapping("/employees/edit/{id}")
			public String editEmployeeForm (@PathVariable int id , Model model){
			Employee employeeById = employeeService.findById(id);
			model.addAttribute("employee",employeeById);
			return "edit_employee";
	}
	@GetMapping("/employees/editForManager/{id}")
	public String editEmployeeFormForManager (@PathVariable int id , Model model){
		Employee employeeById = employeeService.findById(id);
		model.addAttribute("employee",employeeById);
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
		return "redirect:/listForManager";}
		@PostMapping("/employees/{id}")
			public String updateStudent(@PathVariable int id, @ModelAttribute("employee") Employee employee, Model model) {

		Employee existingStudent = employeeService.findById(id);
			existingStudent.setId(id);
			existingStudent.setFirstName(employee.getFirstName());
			existingStudent.setLastName(employee.getLastName());
			existingStudent.setEmail(employee.getEmail());

			employeeService.save(existingStudent);
			return "redirect:/list";}
		@GetMapping("/employees/{id}")
			public String deleteStudent(@PathVariable int id) {
				employeeService.deleteById(id);
				return "redirect:/list";
}
		@GetMapping("/access-denied")
		public String showaccessdenied() {
			return "access-denied";
		}
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails mohamed = User.builder()
				.username("mohamed")
				.password("{bcrypt}$2a$12$3J06kgNaw79WCedOqZhq5.JJU/ZqMzlYdi.HDRYDwjg9q.VrehrLe")
				.roles("EMPLOYEE")
				.build();
		UserDetails john = User.builder()
				.username("ebrahim")
				.password("{bcrypt}$2a$12$8c994cQFOsgAcbcS35DEnucD6KWdoaCJtScXLaY9VXQk8Z5UUEv3a")
				.roles("EMPLOYEE", "MANAGER")
				.build();
		UserDetails shosha = User.builder()
				.username("shosha")
				.password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER","ADMIN")
				.build();
		return new InMemoryUserDetailsManager(mohamed, john, shosha);

	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
						configurer
								.requestMatchers( "/listForEmployee").hasRole("EMPLOYEE")
								.requestMatchers( "/listForEmployee/**").hasRole("EMPLOYEE")
								.requestMatchers( "/listForManager").hasRole("MANAGER")
								.requestMatchers( "/listForManager/**").hasRole("MANAGER")
								.requestMatchers( "/list").hasRole("ADMIN")
								.anyRequest().authenticated()
				)
				.exceptionHandling(configurer ->
						configurer
								.accessDeniedPage("/access-denied"))
				.formLogin(form ->
						form
								.loginPage("/showMyLoginPage")
								.loginProcessingUrl("/authenticateTheUser")
								.permitAll())
				.logout( logout -> logout.permitAll());
		return http.build();
	}
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(configurer ->
//				configurer
//						.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
//						.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//						.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//						.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//						.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
//		// use HTTP Basic authentication
//		http.httpBasic(Customizer.withDefaults());
//		// disable Cross Site Request Forgery (CSRF)
//		http.csrf(csrf -> csrf.disable());
//		return http.build(
//	}
}









