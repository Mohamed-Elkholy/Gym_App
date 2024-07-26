package com.example.gym_app.controller;

import com.example.gym_app.dto.WorkoutDto;
import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workout")
@RequiredArgsConstructor
@Validated
public class WorkoutController {

    private final WorkoutService service;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")  // Replace with your Vue app's URL
    public ResponseEntity<List<Workout>> getWorkoutList() {
        return ResponseEntity.ok(service.getWorkoutList());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> addWorkout(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("name") String name
            ) throws IOException {
        try {
            service.addWorkout(photo, name);
            return ResponseEntity.ok("The Workout has been added successfully :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
