package com.sakila.auth.service;

import com.sakila.auth.dto.RegisterStaffRequest;
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

    public String register(RegisterStaffRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setAddressId(request.getAddressId());
        staff.setEmail(request.getEmail());
        staff.setStoreId(request.getStoreId());
        staff.setActive(request.getActive() == null || request.getActive());
        staff.setUsername(request.getUsername());
        staff.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            repository.save(staff);
            return "User registered successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB Error: " + e.getMessage() + (e.getCause() != null ? " | Cause: " + e.getCause().getMessage() : ""));
        }
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        // Validation logic if needed for internal calls
    }
}