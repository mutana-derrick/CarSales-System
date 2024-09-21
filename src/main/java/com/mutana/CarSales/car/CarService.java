package com.mutana.CarSales.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository; // Inject the repository

    public Optional<CarModel> getCarById(Long carId) {
        return carRepository.findById(carId);
    }

    public List<CarModel> getAllCars() {
        return carRepository.findAll(); // Fetch all cars from the database
    }

    public void saveCar(CarModel car) {
        carRepository.save(car);
    }

    // Method to get all available cars in stock
    public List<CarModel> getAvailableCars() {
        return carRepository.findAvailableCars();
    }

    public List<CarModel> findByModelContaining(String query) {
        // Call the repository method to search for cars by name
        return carRepository.findByModelContainingIgnoreCase(query);
    }
}

