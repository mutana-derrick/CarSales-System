package com.mutana.CarSales.user.controller;


import com.mutana.CarSales.car.CarModel;
import com.mutana.CarSales.car.CarRepository;
import com.mutana.CarSales.car.CarService;
import com.mutana.CarSales.customer.CustomerModel;
import com.mutana.CarSales.customer.CustomerRepository;
import com.mutana.CarSales.customer.CustomerService;
import com.mutana.CarSales.report.ReportModel;
import com.mutana.CarSales.report.ReportRepository;
import com.mutana.CarSales.report.ReportService;
import com.mutana.CarSales.sales.SalesModel;
import com.mutana.CarSales.sales.SalesRepository;
import com.mutana.CarSales.sales.SalesService;
import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

@Controller
public class EditorController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SalesService salesService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReportRepository reportRepository;



    @GetMapping("/manager/dashboard")
    public String editorDashboard() {
        return "manager/dashboard";
    }

    @GetMapping("/manager/profile")
    public String profile() {
        return "manager/profile";
    }

    @GetMapping("/manager/cars")
    public String viewCars(Model model) {
        List<CarModel> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "manager/cars";
    }


    @GetMapping("/manager/customers")
    public String viewCustomers(Model model) {
        List<CustomerModel> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "manager/customers";
    }

    @PostMapping("/manager/addCustomer")
    public String addCustomer(@ModelAttribute CustomerModel customer, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        userService.getUserByUsername(username).ifPresent(user -> {
            customer.setCreatedBy(user);
            customer.setCreatedAt(LocalDateTime.now());
            customer.setStatus("Active");
            customerService.saveCustomer(customer);
        });

        redirectAttributes.addFlashAttribute("message", "Customer added successfully!");
        return "redirect:/manager/customers";
    }


    @GetMapping("/manager/sales")
    public String viewSales(Model model) {
        List<SalesModel> sales = salesRepository.findAll();
        model.addAttribute("sales", sales);
        return "manager/sales";
    }

    @GetMapping("/manager/recordSales")
    public String recordSales(){
        return"manager/recordsales";
    }

    // Search cars by name (AJAX) and only include cars with 'In Stock' status
    @GetMapping("/manager/searchCars")
    public ResponseEntity<String> searchCars(@RequestParam("query") String query) {
        // Modify the service call to fetch only cars that are available (stock status 'Available')
        List<CarModel> cars = carService.findAvailableCarsByModel(query);

        StringBuilder suggestions = new StringBuilder();
        for (CarModel car : cars) {
            suggestions.append("<div class='car-item' data-id='").append(car.getCarId()).append("'>")
                    .append(car.getModel()).append("</div>");
        }
        return new ResponseEntity<>(suggestions.toString(), HttpStatus.OK);
    }


    @GetMapping("/manager/getCarDetails")
    public ResponseEntity<Map<String, Object>> getCarDetails(@RequestParam("carId") Long carId) {
        Optional<CarModel> car = carService.getCarById(carId);
        if (car.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("manufacturingYear", car.get().getYear());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Search customers by name (AJAX)
    @GetMapping("/manager/searchCustomers")
    public ResponseEntity<String> searchCustomers(@RequestParam("query") String query) {
        List<CustomerModel> customers = customerService.findByNameContaining(query);  // Implement search method in service
        StringBuilder suggestions = new StringBuilder();
        for (CustomerModel customer : customers) {
            suggestions.append("<div class='customer-item' data-id='").append(customer.getCustomerId()).append("'>")
                    .append(customer.getName()).append("</div>");
        }
        return new ResponseEntity<>(suggestions.toString(), HttpStatus.OK);
    }


    @PostMapping("/manager/addSale")
    public String addSale(@RequestParam("carId") Long carId,
                          @RequestParam("customerId") Long customerId,
                          @ModelAttribute SalesModel sale,
                          RedirectAttributes redirectAttributes) {
        System.out.println("Received carId: " + carId);
        System.out.println("Received customerId: " + customerId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Fetch the car and customer by their IDs
        Optional<CarModel> car = carService.getCarById(carId);
        Optional<CustomerModel> customer = customerService.getCustomerById(customerId);

        // Ensure both car and customer exist
        if (car.isPresent() && customer.isPresent()) {
            CarModel selectedCar = car.get();

            // Set the car and customer in the SalesModel
            sale.setCar(selectedCar);
            sale.setCustomer(customer.get());

            // Fetch and set the current user as the creator
            userService.getUserByUsername(username).ifPresent(user -> {
                sale.setCreatedBy(user);
                sale.setCreatedAt(LocalDateTime.now());
                sale.setStatus("Completed");

                // Save the sale
                salesService.saveSale(sale);
            });

            // Update car status to 'Sold'
            selectedCar.setStockStatus("Sold");
            carService.updateCar(selectedCar);  // Save the updated car

            redirectAttributes.addFlashAttribute("message", "Sale recorded successfully!");
        } else {
            // If car or customer is not found, return an error message
            redirectAttributes.addFlashAttribute("error", "Invalid car or customer selection.");
        }

        return "redirect:/manager/sales";
    }


    @GetMapping("/manager/report")
    public String showReport(Model model) {
        model.addAttribute("reports", reportService.getAllReports());
        return "manager/report";
    }

    @GetMapping("/manager/viewreport/{id}")
    public String viewReport(@PathVariable Long id, Model model) {
        ReportModel report = reportService.getReportById(id);
        model.addAttribute("report", report);
        return "manager/viewreport";
    }


    @PostMapping("/manager/generatereport")
    public String generateReport(
            @ModelAttribute("reportTitle") String reportTitle,
            @ModelAttribute("reportDescription") String reportDescription,
            @ModelAttribute("startDate") String startDateStr,
            @ModelAttribute("endDate") String endDateStr,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);

        UserModel manager = userService.getUserModelFromUserDetails(userDetails);
        ReportModel report = reportService.generateReport(reportTitle, reportDescription, startDate, endDate, manager);

        reportRepository.save(report);


        redirectAttributes.addFlashAttribute("message", "Comprehensive report generated successfully!");
        redirectAttributes.addFlashAttribute("report", report);

        return "redirect:/manager/report";
    }

}