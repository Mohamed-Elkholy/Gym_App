package com.example.gym_app.service;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.repository.ExerciseRepository;
import com.example.gym_app.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;
    private final WorkoutRepository workoutRepository;

    public List<Exercise> getExerciseList() {
        return repository.findAll();
    }

    public void addExercise(
            MultipartFile photo,
            String name,
            List<String> sets,
            List<String> description,
            List<String> instructions,
            Long workoutId
    ) throws IOException {
        if (repository.existsByName(name)) {
            throw new IllegalArgumentException("Exercise name must be unique :(");
        }
        Exercise exercise = Exercise.builder()
                .name(name)
                .photo(photo.getBytes())
                .sets(sets)
                .description(description)
                .instructions(instructions)
                .build();
        if (workoutRepository.existsById(workoutId)) {
            exercise.setWorkout(workoutRepository.getReferenceById(workoutId));
        } else {
            throw new IllegalArgumentException("There is no workouts with this id :(");
        }
        repository.save(exercise);
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
