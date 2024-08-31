package com.mutana.CarSales.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
    long count();
    List<CarModel> findTop3ByOrderByCreatedAtDesc();

}

