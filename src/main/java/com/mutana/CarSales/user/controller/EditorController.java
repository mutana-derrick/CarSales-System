package com.mutana.CarSales.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorController {

    @GetMapping("/manager/dashboard")
    public String editorDashboard() {
        return "manager/dashboard";
    }

    @GetMapping("/manager/profile")
    public String profile() {
        return "manager/profile";
    }

    @GetMapping("/manager/cars")
    public String viewCars() {
        return "manager/cars";
    }

    @GetMapping("/manager/customers")
    public String viewCustomers() {
        return "manager/customers";
    }

    @GetMapping("/manager/sales")
    public String viewSales() {
        return "manager/sales";
    }

    @GetMapping("/manager/report")
    public String generateReport() {
        return "manager/report";
    }
}