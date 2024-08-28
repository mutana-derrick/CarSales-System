package com.mutana.CarSales.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorController {

    @GetMapping("/manager/dashboard")
    public String editorDashboard() {
        return "manager/dashboard"; // This will render manager/dashboard.html
    }
}