package com.mutana.CarSales.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

//    @GetMapping("/dashboard")
//    public String getDashboard() {
//        // Logic to determine which dashboard to show based on user role
//        return "dashboard";
//    }
//
//    @GetMapping("/salesperson/dashboard")
//    @PreAuthorize("hasRole('VIEWER')")
//    public String getSalespersonDashboard() {
//        return "dashboard/salesperson";
//    }
//
//    @GetMapping("/manager/dashboard")
//    @PreAuthorize("hasRole('EDITOR')")
//    public String getManagerDashboard() {
//        return "dashboard/manager";
//    }
//
//    @GetMapping("/ceo/dashboard")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String getCEODashboard() {
//        return "dashboard/ceo";
//    }
}
