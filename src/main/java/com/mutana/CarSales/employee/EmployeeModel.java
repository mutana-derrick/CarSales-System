package com.mutana.CarSales.employee;

import com.mutana.CarSales.user.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String name;
    private String position;
    private String email;
    private String phone;

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

