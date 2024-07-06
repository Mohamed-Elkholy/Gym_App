package com.example.gym_app.service;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    public List<Exercise> getExerciseList() {
        return repository.findAll();
    }

    public Exercise addExercise(Exercise exercise) {
        if (repository.existsByName(exercise.getName())) {
            throw new IllegalArgumentException("Exercise name must be unique");
        }
        return repository.save(exercise);
    }

    public void deleteExercise(Long id) {
        repository.deleteById(id);
    }

    public Optional<Exercise> getExercise(Long id) {
        return repository.findById(id);
    }
    public List<Exercise> addExercises(List<Exercise> exercises) {
        return repository.saveAll(exercises);
    }

    public Optional<Exercise> getExerciseByName(String name) {
        return repository.findByName(name);
    }
}
