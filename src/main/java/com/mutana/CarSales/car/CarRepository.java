package com.mutana.CarSales.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
    long count();
    List<CarModel> findTop3ByOrderByCreatedAtDesc();

    @Query("SELECT c FROM CarModel c WHERE c.stockStatus = 'Available'")
    List<CarModel> findAvailableCars();

    @Query("SELECT COUNT(c) FROM CarModel c WHERE c.stockStatus = 'Available'")
    long countAvailableCars();

    List<CarModel> findByModelContainingIgnoreCase(String model);
}

