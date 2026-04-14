package com.sakila.reports.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_by_store")
@Data
public class SalesByStore {
    @Id
    private String store;
    
    private String manager;
    
    @Column(name = "total_sales")
    private BigDecimal totalSales;
}
