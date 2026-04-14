package com.sakila.auth.config;

import com.sakila.auth.entity.Staff;
import com.sakila.auth.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> staff = repository.findByUsername(username);
        if (staff.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(staff.get().getUsername(), staff.get().getPassword(), new ArrayList<>());
    }
}
