package com.mutana.CarSales.report;

import com.mutana.CarSales.car.CarRepository;
import com.mutana.CarSales.customer.CustomerRepository;
import com.mutana.CarSales.sales.SalesModel;
import com.mutana.CarSales.sales.SalesRepository;
import com.mutana.CarSales.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
@Autowired
private SalesRepository salesRepository;

@Autowired
private CarRepository carRepository;

@Autowired
private CustomerRepository customerRepository;

@Autowired
private ReportRepository reportRepository;

    @Transactional
    public ReportModel generateReport(String reportTitle, String reportDescription, LocalDateTime startDate, LocalDateTime endDate, UserModel manager) {
        List<SalesModel> sales = salesRepository.findSalesByDateRange(startDate, endDate);

        Long availableCars = carRepository.countAvailableCars();
        Long soldCars = salesRepository.countSalesInDateRange(startDate, endDate);
        Long newCustomers = customerRepository.countNewCustomersByDateRange(startDate, endDate);
        Double totalSales = calculateTotalSales(sales);
        Double totalProfit = calculateTotalProfit(sales);
        String topSellingModel = findTopSellingModel(sales);

        return ReportModel.builder()
                .reportTitle(reportTitle)
                .reportDescription(reportDescription)
                .availableCars(availableCars != null ? availableCars : 0L)
                .soldCars(soldCars != null ? soldCars : 0L)
                .newCustomers(newCustomers != null ? newCustomers : 0L)
                .totalSales(totalSales != null ? totalSales : 0.0)
                .totalProfit(totalProfit != null ? totalProfit : 0.0)
                .topSellingModel(topSellingModel)
                .startDate(startDate)
                .endDate(endDate)
                .manager(manager)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private double calculateTotalSales(List<SalesModel> sales) {
        return sales.stream().mapToDouble(SalesModel::getSalePrice).sum();
    }

    private double calculateTotalProfit(List<SalesModel> sales) {
        return sales.stream()
                .mapToDouble(sale -> sale.getSalePrice() - sale.getCar().getPrice())
                .sum();
    }

    private String findTopSellingModel(List<SalesModel> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getCar().getModel(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public List<ReportModel> getAllReports() {
        return reportRepository.findAll();
    }

    public ReportModel getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));
    }
}
