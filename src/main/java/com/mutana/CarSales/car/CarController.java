package com.mutana.CarSales.car;

import com.mutana.CarSales.category.CategoryModel;
import com.mutana.CarSales.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/addCarForm")
    public String showAddCarForm(Model model) {
        // Fetch categories and add to the model
        List<CategoryModel> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        // Return the name of the Thymeleaf template
        return "addCarForm";
    }
}

