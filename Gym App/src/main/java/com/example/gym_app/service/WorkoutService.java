package com.example.gym_app.service;

import com.example.gym_app.model.Workout;
import com.example.gym_app.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    public List<Workout> getWorkoutList() {
        return repository.findAll();
    }

    public Workout addWorkout(Workout workout) {
        if(repository.existsByName(workout.getName())) {
            throw new IllegalArgumentException("Workout name must be unique");
        }
        return repository.save(workout);
    }

    public void deleteWorkout(Long id) {
        repository.deleteById(id);
    }

    public Optional<Workout> getWorkout(Long id) {
        return repository.findById(id);
    }

    public List<Workout> addWorkouts(List<Workout> workouts) {
        return repository.saveAll(workouts);
    }

    public Optional<Workout> getWorkoutByName(String name) {
        return repository.findByName(name);
    }
}
