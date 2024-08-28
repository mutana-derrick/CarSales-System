package com.mutana.CarSales;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Debug {
        public static void main(String[] args) {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "editor"; // The plain text password
            String encodedPassword = encoder.encode(rawPassword);
            System.out.println("Encoded Password: " + encodedPassword);
        }
}
