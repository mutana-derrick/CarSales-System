package com.mutana.CarSales.user.controller;

import com.mutana.CarSales.car.CarModel;
import com.mutana.CarSales.car.CarService;
import com.mutana.CarSales.customer.CustomerModel;
import com.mutana.CarSales.customer.CustomerService;
import com.mutana.CarSales.sales.SalesModel;
import com.mutana.CarSales.sales.SalesService;
import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewerController {
    @Autowired
    private CarService carService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;

    @GetMapping("/salesperson/dashboard")
    public String salesPersonDashboard(Model model) {
        // Get the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel currentUser = userService.getUserModelFromUserDetails((UserDetails) auth.getPrincipal());

        // Cars
        List<CarModel> allCars = carService.getAllCars();
        model.addAttribute("totalCars", allCars.size());
        model.addAttribute("recentCars", allCars.subList(0, Math.min(5, allCars.size())));

        // Customers
        List<CustomerModel> allCustomers = customerService.getAllCustomers();
        model.addAttribute("totalCustomers", allCustomers.size());
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<CustomerModel> recentCustomers = customerService.getNewCustomersByDateRange(oneMonthAgo, LocalDateTime.now());
        model.addAttribute("recentCustomers", recentCustomers.subList(0, Math.min(5, recentCustomers.size())));

        // Sales
        List<SalesModel> salespersonSales = salesService.getSalesBySalesperson(currentUser);
        if (!salespersonSales.isEmpty()) {
            model.addAttribute("totalSales", salespersonSales.size());
            model.addAttribute("recentSales", salespersonSales.subList(0, Math.min(5, salespersonSales.size())));
        } else {
            model.addAttribute("noSalesMessage", "You haven't made any sales yet.");
        }

        return "salesperson/dashboard";
    }

    @GetMapping("/salesperson/cars")
    public String viewCars(Model model) {
        // Fetch all cars
        List<CarModel> cars = carService.getAllCars();
        // Add the cars to the model
        model.addAttribute("cars", cars);
        return "salesperson/cars";
    }

    @GetMapping("/salesperson/customers")
    public String viewCustomers(Model model) {
        // Fetch all customers
        List<CustomerModel> customers = customerService.getAllCustomers();
        // Add the customers to the model
        model.addAttribute("customers", customers);
        return "salesperson/customers";
    }

    @GetMapping("/salesperson/sales")
    public String viewSales(Model model) {
        // Get the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel currentUser = userService.getUserModelFromUserDetails((UserDetails) auth.getPrincipal());

        // Fetch sales by salesperson
        List<SalesModel> sales = salesService.getSalesBySalesperson(currentUser);
        // Add the sales to the model
        model.addAttribute("sales", sales);
        return "salesperson/sales";
    }

    @GetMapping("/salesperson/profile")
    public String profile() {
        return "salesperson/profile";
    }
}
