package com.sakila.business.controller;

import com.sakila.business.entity.Store;
import com.sakila.business.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    private StoreRepository storeRepository;

    @QueryMapping
    public List<Store> allStores() {
        return storeRepository.findAll();
    }

    @QueryMapping
    public Store storeById(@Argument Integer id) {
        return storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
    }
}
