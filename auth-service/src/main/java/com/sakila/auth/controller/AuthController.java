package com.sakila.auth.controller;

import com.sakila.auth.dto.AuthRequest;
import com.sakila.auth.dto.RegisterStaffRequest;
import com.sakila.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody RegisterStaffRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        }

        throw new RuntimeException("Invalid access");
    }
}