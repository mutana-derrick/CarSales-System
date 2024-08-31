package com.mutana.CarSales.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    long count();
    List<EmployeeModel> findTop3ByOrderByCreatedAtDesc();
}
