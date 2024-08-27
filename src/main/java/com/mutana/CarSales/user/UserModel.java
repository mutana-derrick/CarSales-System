package com.mutana.CarSales.user;

import com.mutana.CarSales.employee.EmployeeModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeModel employee;

    private String username;
    private String password;
    private String role; // 'view', 'editor', 'admin'

    private String status; // 'Active', 'Inactive'
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

