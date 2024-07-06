package com.example.gym_app.service;

import com.example.gym_app.model.Workout;
import com.example.gym_app.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Workout getWorkout(Long id) {
        return repository.getById(id);
    }
}
