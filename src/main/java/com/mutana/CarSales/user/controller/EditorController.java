package com.mutana.CarSales.user.controller;

import com.mutana.CarSales.report.ReportModel;
import com.mutana.CarSales.report.ReportService;
import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class EditorController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

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
    public String generateReport(@ModelAttribute("reportTitle") String reportTitle,
                                 @ModelAttribute("reportDescription") String reportDescription,
                                 @ModelAttribute("startDate") String startDateStr,
                                 @ModelAttribute("endDate") String endDateStr,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 RedirectAttributes redirectAttributes) {

        LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);

        UserModel manager = userService.getUserModelFromUserDetails(userDetails);
        ReportModel report = reportService.generateReport(reportTitle, reportDescription, startDate, endDate, manager);

        redirectAttributes.addFlashAttribute("message", "Report generated successfully!");
        redirectAttributes.addFlashAttribute("report", report);

        return "redirect:/manager/report";
    }
}