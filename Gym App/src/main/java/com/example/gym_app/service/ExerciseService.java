package com.example.gym_app.service;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    public List<Exercise> getExerciseList() {
        return repository.findAll();
    }

    public Exercise addExercise(Exercise exercise) {
        return repository.save(exercise);
    }
}
