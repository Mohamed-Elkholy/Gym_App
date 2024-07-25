package com.example.gym_app.controller;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping
    public ResponseEntity<List<Exercise>> getExerciseList() {
        return ResponseEntity.ok(service.getExerciseList());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> addExercise(
            @RequestParam("name") String name,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("sets") List<String> sets,
            @RequestParam("description") List<String> description,
            @RequestParam("instructions") List<String> instructions,
            @RequestParam("workoutId") Long workoutId
    ) throws IOException {
        try {
            service.addExercise(photo, name, sets, description, instructions, workoutId);
            return ResponseEntity.ok("Exercise has been added successfully :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/list")
    public ResponseEntity<List<Exercise>> addExercises(@RequestBody List<Exercise> exercises) {
        return ResponseEntity.ok(service.addExercises(exercises));
    }
    @GetMapping("/get/{name}")
    public ResponseEntity<Exercise> getExerciseByName(@PathVariable(value = "name") String name) {
        Optional<Exercise> exercise = service.getExerciseByName(name);
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable Long id) {
        Optional<Exercise> exercise = service.getExercise(id);
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
