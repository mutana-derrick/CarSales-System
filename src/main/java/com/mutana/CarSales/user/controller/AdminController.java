package com.mutana.CarSales.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/ceo/dashboard")
    public String adminDashboard() {
        return "ceo/dashboard"; // This will render ceo/dashboard.html
    }
}

