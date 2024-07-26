package com.example.gym_app.repository;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    boolean existsByName(String name);

    Optional<Workout> findByName(String name);

    @Query("SELECT w FROM Workout w WHERE w.name LIKE %:name%")
    List<Workout> findByNameContaining(@Param("name") String name);

    @Query("select x.exercises from Workout x where x.name = :workoutName")
    List<Exercise> findExercisesByName(@Param("workoutName") String workoutName);
}
