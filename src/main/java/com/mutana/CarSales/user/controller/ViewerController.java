package com.mutana.CarSales.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewerController {

    @GetMapping("/salesperson/dashboard")
    public String viewerDashboard() {
        return "salesperson/dashboard"; // This will render salesperson/dashboard.html
    }
}
