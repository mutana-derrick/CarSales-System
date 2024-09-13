package com.mutana.CarSales.report;

import com.mutana.CarSales.car.CarRepository;
import com.mutana.CarSales.customer.CustomerRepository;
import com.mutana.CarSales.sales.SalesRepository;
import com.mutana.CarSales.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReportService {
    private final SalesRepository salesRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public ReportModel generateReport(String reportTitle, String reportDescription, LocalDateTime startDate, LocalDateTime endDate, UserModel manager) {
        // Simplified report generation
//        long totalSales = salesRepository.countSalesInDateRange(startDate, endDate);
        long availableCars = carRepository.countAvailableCars();
        long newCustomers = customerRepository.countNewCustomersByDateRange(startDate, endDate);

        // Build and save the report
        ReportModel report = ReportModel.builder()
                .reportTitle(reportTitle)
                .reportDescription(reportDescription)
                .availableCars(availableCars)
                .newCustomers(newCustomers)
                .startDate(startDate)
                .endDate(endDate)
                .manager(manager)
                .createdAt(LocalDateTime.now())
                .build();

        return reportRepository.save(report);
    }



    public List<ReportModel> getAllReports() {
        return reportRepository.findAll();
    }

    public ReportModel getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));
    }
}
