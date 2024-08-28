package com.mutana.CarSales.car;

import com.mutana.CarSales.category.CategoryModel;
import com.mutana.CarSales.user.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;

    private String model;
    private LocalDate year;
    private double price;
    private String stockStatus; // 'Available', 'Sold'

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

