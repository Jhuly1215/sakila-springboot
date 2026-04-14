package com.sakila.auth.service;

import com.sakila.auth.entity.Staff;
import com.sakila.auth.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String register(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        repository.save(staff);
        return "User registered successfully";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        // Validation logic if needed for internal calls
    }
}
