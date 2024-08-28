package com.mutana.CarSales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String welcomeLogin() {
        return "login"; // This will render the login.html template
    }

}
