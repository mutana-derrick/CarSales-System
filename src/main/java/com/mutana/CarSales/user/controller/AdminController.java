package com.mutana.CarSales.user.controller;

import com.mutana.CarSales.car.CarModel;
import com.mutana.CarSales.car.CarService;
import com.mutana.CarSales.category.CategoryModel;
import com.mutana.CarSales.category.CategoryService;
import com.mutana.CarSales.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

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
}

