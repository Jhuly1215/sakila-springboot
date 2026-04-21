package com.sakila.auth.repository;

import com.sakila.auth.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Byte> {
    Optional<Staff> findByUsername(String username);
}