package com.mutana.CarSales.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewerController {

    @GetMapping("/salesperson/dashboard")
    public String salesPersonDashboard() {
        return "salesperson/dashboard";
    }

    @GetMapping("/salesperson/profile")
    public String profile() {
        return "salesperson/profile";
    }

    @GetMapping("/salesperson/cars")
    public String viewCars() {
        return "salesperson/cars";
    }

    @GetMapping("/salesperson/customers")
    public String viewCustomers() {
        return "salesperson/customers";
    }

    @GetMapping("/salesperson/sales")
    public String viewSales() {
        return "salesperson/sales";
    }
}
