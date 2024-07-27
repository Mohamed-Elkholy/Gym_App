package com.example.gym_app.service;

import com.example.gym_app.model.Workout;
import com.example.gym_app.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    public List<Workout> getWorkoutList() {
        return repository.findAll();
    }

    public void addWorkout(MultipartFile photo, String name) throws IOException {
        if(repository.existsByName(name)) {
            throw new IllegalArgumentException("Workout name must be unique");
        }
        Workout workout = new Workout();
        workout.setName(name);
        workout.setPhoto(photo.getBytes());
        repository.save(workout);
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

    @Transactional
    public List<Workout> searchWorkoutsByName(String name) {
        return repository.findByNameContaining(name);
    }
}
