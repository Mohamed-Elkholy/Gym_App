package com.example.gym_app.repository;

import com.example.gym_app.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    boolean existsByName(String name);
}
