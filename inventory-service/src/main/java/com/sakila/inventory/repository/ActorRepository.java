package com.sakila.inventory.repository;

import com.sakila.inventory.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Short> {
}