package com.mutana.CarSales.user.service;

import com.mutana.CarSales.employee.EmployeeModel;
import com.mutana.CarSales.user.model.Role;
import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUser(UserModel user) {
        userRepository.save(user);
    }


    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public UserModel getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void createUserFromEmployee(EmployeeModel employee, Role role) {
        UserModel user = new UserModel();
        user.setEmployeeId(employee);
        user.setUsername(employee.getEmail());  // Use email as username
        user.setPassword(passwordEncoder.encode("password"));  // Set a default password
        user.setRole(role);
        user.setStatus("Active");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    @Transactional
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel getUserModelFromUserDetails(UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userDetails.getUsername()));
    }
}