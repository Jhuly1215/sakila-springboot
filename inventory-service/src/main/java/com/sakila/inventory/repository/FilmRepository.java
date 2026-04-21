package com.sakila.inventory.repository;

import com.sakila.inventory.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Short> {
}
