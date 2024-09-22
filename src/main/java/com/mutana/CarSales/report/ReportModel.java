package com.mutana.CarSales.report;

import com.mutana.CarSales.user.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportTitle;
    private String reportDescription;

    @Column(name = "available_cars")
    private Long availableCars; // Changed from long to Long

    @Column(name = "sold_cars")
    private Long soldCars; // Changed from long to Long

    @Column(name = "new_customers")
    private Long newCustomers; // Changed from long to Long

    @Column(name = "total_sales")
    private Double totalSales; // Changed from double to Double

    @Column(name = "total_profit")
    private Double totalProfit; // Changed from double to Double

    @Column(name = "top_selling_model")
    private String topSellingModel;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserModel manager;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
