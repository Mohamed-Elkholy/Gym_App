package com.example.gym_app.repository;

import com.example.gym_app.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    boolean existsByName(String name);

    Optional<Workout> findByName(String name);
}
