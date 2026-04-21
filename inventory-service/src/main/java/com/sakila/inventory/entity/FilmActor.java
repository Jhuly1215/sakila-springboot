package com.sakila.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
@IdClass(FilmActor.FilmActorId.class)
@Data
public class FilmActor {

    @Id
    @Column(name = "actor_id", nullable = false)
    private Short actorId;

    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Column(name = "last_update", insertable = false, updatable = false)
    private LocalDateTime lastUpdate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FilmActorId implements Serializable {
        private Short actorId;
        private Short filmId;
    }
}