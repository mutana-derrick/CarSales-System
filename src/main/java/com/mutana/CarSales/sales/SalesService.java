package com.mutana.CarSales.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<SalesModel> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<SalesModel> getSaleById(Long id) {
        return salesRepository.findById(id);
    }

    @Transactional
    public SalesModel saveSale(SalesModel sale) {
        return salesRepository.save(sale);
    }

    @Transactional
    public void deleteSale(Long id) {
        salesRepository.deleteById(id);
    }

    public List<SalesModel> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Assuming there is a method in SalesRepository to fetch sales by date range
        return salesRepository.findSalesByDateRange(startDate, endDate);
    }
}