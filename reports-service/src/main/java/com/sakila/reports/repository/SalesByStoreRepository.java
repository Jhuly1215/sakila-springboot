package com.sakila.reports.repository;

import com.sakila.reports.entity.SalesByStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesByStoreRepository extends JpaRepository<SalesByStore, String> {
}
