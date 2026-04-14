package com.sakila.business.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "store")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @Column(name = "manager_staff_id")
    private Integer managerStaffId;

    @Column(name = "address_id")
    private Integer addressId;
}
