package com.mutana.CarSales.user.service;

import com.mutana.CarSales.user.model.UserModel;
import com.mutana.CarSales.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    // Add more user-related methods as needed
}
