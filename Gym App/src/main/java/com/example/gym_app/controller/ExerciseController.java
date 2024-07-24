package com.example.gym_app.controller;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        return ResponseEntity.ok(service.addExercise(exercise));
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
