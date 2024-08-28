package com.mutana.CarSales.sales;

import com.mutana.CarSales.car.CarModel;
import com.mutana.CarSales.customer.CustomerModel;
import com.mutana.CarSales.user.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class SalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "carId")
    private CarModel car;

    @ManyToOne(optional = true)
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    @ManyToOne(optional = true)
    @JoinColumn(name = "salespersonId")
    private UserModel salesperson;

    private LocalDate saleDate;
    private double salePrice;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private UserModel createdBy;

    private LocalDateTime createdAt;

    @ManyToOne(optional = true)
    @JoinColumn(name = "modifiedBy")
    private UserModel modifiedBy;

    private LocalDateTime modifiedAt;
    private String status; // 'Active', 'Inactive'
}

