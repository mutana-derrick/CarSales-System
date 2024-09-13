package com.mutana.CarSales.report;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long> {
    // Add any custom query methods if needed
}

