package com.example.gym_app.controller;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
@Validated
public class WorkoutController {

    private final WorkoutService service;

    @GetMapping
    public ResponseEntity<List<Workout>> getWorkoutList() {
        return ResponseEntity.ok(service.getWorkoutList());
    }

    @PostMapping
    public ResponseEntity<Workout> addWorkout(@Valid @RequestBody Workout workout) {
        try {
            return ResponseEntity.ok(service.addWorkout(workout));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        service.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
        Optional<Workout> workout = service.getWorkout(id);
        return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/get/{name}")
    public ResponseEntity<Workout> getWorkoutByName(@PathVariable(value = "name") String name) {
        Optional<Workout> workout = service.getWorkoutByName(name);
        return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/list")
    public ResponseEntity<List<Workout>> addWorkouts(@RequestBody List<Workout> workouts) {
        return ResponseEntity.ok(service.addWorkouts(workouts));
    }

}
