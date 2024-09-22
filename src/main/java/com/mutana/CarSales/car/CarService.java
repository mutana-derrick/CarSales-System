package com.mutana.CarSales.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Fetch a car by its ID
    public Optional<CarModel> getCarById(Long carId) {
        return carRepository.findById(carId);
    }

    // Fetch all cars from the database
    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    // Save or update a car
    public void saveCar(CarModel car) {
        carRepository.save(car);
    }

    // Get all available cars in stock
    public List<CarModel> getAvailableCars() {
        return carRepository.findAvailableCars();
    }

    // Search for cars by model (ignoring case)
    public List<CarModel> findByModelContaining(String query) {
        return carRepository.findByModelContainingIgnoreCase(query);
    }

    // Update a car using the save method (acts as both insert and update)
    public void updateCar(CarModel car) {
        carRepository.save(car);
    }

    // Method to fetch available cars that match the query
    public List<CarModel> findAvailableCarsByModel(String query) {
        return carRepository.findByModelContainingAndStockStatusIgnoreCase(query, "Available");
    }
}


