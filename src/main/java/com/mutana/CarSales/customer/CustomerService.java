package com.mutana.CarSales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public CustomerModel saveCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<CustomerModel> getNewCustomersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Assuming there is a method in CustomerRepository to fetch new customers by date range
        return customerRepository.findNewCustomersByDateRange(startDate, endDate);
    }
}
