package com.example.gym_app.service;

import com.example.gym_app.model.Exercise;
import com.example.gym_app.model.Workout;
import com.example.gym_app.repository.ExerciseRepository;
import com.example.gym_app.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public List<Exercise> getExerciseList() {
        return exerciseRepository.findAll();
    }

    public Exercise addExercise(MultipartFile photo, String name, List<String> sets, List<String> description, List<String> instructions, Long workoutId) throws IOException {
        if (exerciseRepository.existsByName(name)) {
            throw new IllegalArgumentException("Exercise name must be unique");
        }
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (!workoutOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid workout ID");
        }

        Workout workout = workoutOptional.get();

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setPhoto(photo.getBytes());
        exercise.setSets(sets);
        exercise.setDescription(description);
        exercise.setInstructions(instructions);
        exercise.setWorkout(workout);

        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    public Optional<Exercise> getExercise(Long id) {
        return exerciseRepository.findById(id);
    }

    public List<Exercise> addExercises(List<Exercise> exercises) {
        return exerciseRepository.saveAll(exercises);
    }

    public Optional<Exercise> getExerciseByName(String name) {
        return exerciseRepository.findByName(name);
    }
}
