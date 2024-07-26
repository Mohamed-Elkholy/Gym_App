package com.example.gym_app.repository;

import com.example.gym_app.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT e FROM Exercise e WHERE e.name LIKE %:name%")
    List<Exercise> findByNameContaining(@Param("name") String name);
}
