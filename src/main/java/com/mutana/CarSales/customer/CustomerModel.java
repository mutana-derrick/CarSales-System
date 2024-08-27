package com.mutana.CarSales.customer;

import com.mutana.CarSales.user.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String email;
    private String phone;
    private String address;

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

