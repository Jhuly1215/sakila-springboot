package com.sakila.customer.repository;

import com.sakila.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Short> {
}
