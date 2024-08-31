package com.mutana.CarSales.user.controller;

import com.mutana.CarSales.car.CarModel;
import com.mutana.CarSales.car.CarService;
import com.mutana.CarSales.category.CategoryModel;
import com.mutana.CarSales.category.CategoryService;
import com.mutana.CarSales.employee.EmployeeModel;
import com.mutana.CarSales.employee.EmployeeRepository;
import com.mutana.CarSales.employee.EmployeeService;
import com.mutana.CarSales.user.model.Role;
import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.repository.UserRepository;
import com.mutana.CarSales.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository; // To fetch the EmployeeModel
    @Autowired
    private PasswordEncoder passwordEncoder; // For encoding the password

    @GetMapping("/ceo/dashboard")
    public String adminDashboard() {
        return "ceo/dashboard";
    }

    @GetMapping("/ceo/cars")
    public String getCars(Model model) {
        List<CarModel> cars = carService.getAllCars(); // Fetch car data
        model.addAttribute("cars", cars);

        List<CategoryModel> categories = categoryService.getAllCategories(); // Fetch car data
        model.addAttribute("categories", categories);
        return "ceo/cars";
    }

    @PostMapping("/ceo/addCar")
    public String addCar(@ModelAttribute CarModel car, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        userService.getUserByUsername(username).ifPresent(user -> {
            car.setCreatedBy(user);
            car.setCreatedAt(LocalDateTime.now());
            carService.saveCar(car);
        });
        redirectAttributes.addFlashAttribute("message", "Car added successfully!");
        return "redirect:/ceo/cars";
    }

    @GetMapping("/ceo/employee")
    public String getEmployee(Model model){
        List<EmployeeModel> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("roles" , Arrays.toString(Role.values()));
        return "ceo/employee";
    }

    @PostMapping("/ceo/addEmployee")
    public String addEmployee(@ModelAttribute EmployeeModel employee, RedirectAttributes redirectAttributes) {
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        return "redirect:/ceo/employee";
    }

    @GetMapping("/ceo/assignrole")
    public String assignRole(@RequestParam("employeeId") Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("roles", Role.values());
        return "/ceo/assignrole";
    }

    @PostMapping("/ceo/assignrole")
    public String assignRole(@RequestParam("employeeId") Long employeeId, @RequestParam("role") Role role) {
        // Fetch the employee by ID
        EmployeeModel employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        UserModel user = new UserModel();
        user.setEmployeeId(employee);
        user.setUsername(employee.getEmail());
        user.setPassword(passwordEncoder.encode("password")); // Encode the default password
        user.setRole(role);
        user.setStatus("Active"); // Set the status as Active
        user.setCreatedAt(LocalDateTime.now()); // Set the current date as createdAt

        userRepository.save(user);

        return "redirect:/ceo/users";
    }

    @GetMapping("/ceo/users")
    public String getUsers(Model model) {
        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "ceo/users";
    }

    @GetMapping("/ceo/profile")
    public String adminProfile() {
        return "ceo/profile";
    }
}

