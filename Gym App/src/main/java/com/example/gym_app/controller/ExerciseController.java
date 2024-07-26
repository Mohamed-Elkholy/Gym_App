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
@CrossOrigin
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchByName(@RequestParam("name") String name) {
        List<Exercise> exercises = service.searchExercisesByName(name);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{workout_name}")
    public ResponseEntity<List<Exercise>> getExercisesByWorkout(@PathVariable("workout_name") String workoutName) {
        return ResponseEntity.ok(service.getExercisesByWorkout(workoutName));
    }

}
