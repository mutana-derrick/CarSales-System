package com.mutana.CarSales.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    @Query("SELECT c FROM CustomerModel c WHERE c.createdAt BETWEEN :startDate AND :endDate")
    List<CustomerModel> findNewCustomersByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(c) FROM CustomerModel c WHERE c.createdAt BETWEEN :startDate AND :endDate")
    long countNewCustomersByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<CustomerModel> findByNameContainingIgnoreCase(String name);
}
