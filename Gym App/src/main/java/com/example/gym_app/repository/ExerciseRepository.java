package com.example.gym_app.repository;

import com.example.gym_app.model.Exercise;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT e FROM Exercise e WHERE e.name LIKE %:name% and e.workout.id = :workoutId")
    List<Exercise> findByNameContaining(@Param("name") String name, @Param("workoutId") Long workoutId);
}
