package com.example.gym_app.controller;

import com.example.gym_app.model.Workout;
import com.example.gym_app.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(service.getWorkout(id));
    }

}
