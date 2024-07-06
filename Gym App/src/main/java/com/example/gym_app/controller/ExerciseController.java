package com.example.gym_app.controller;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
//        service.deleteWorkout(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
//        return ResponseEntity.ok(service.getWorkout(id));
//    }

}
