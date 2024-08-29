package com.mutana.CarSales.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository; // Inject the repository

    public List<CarModel> getAllCars() {
        return carRepository.findAll(); // Fetch all cars from the database
    }

    public void saveCar(CarModel car) {
        carRepository.save(car);
    }
}

