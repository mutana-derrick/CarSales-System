package com.mutana.CarSales.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Long> {

    @Query("SELECT s FROM SalesModel s WHERE s.createdAt BETWEEN :startDate AND :endDate")
    List<SalesModel> findSalesByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(s) FROM SalesModel s WHERE s.createdAt BETWEEN :startDate AND :endDate")
    Long countSalesInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
