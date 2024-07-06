package com.example.gym_app.service;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.SleepTrackerRepository;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepTrackerService {

    private final SleepTrackerRepository repository;

    public SleepTracker addSleepTracker(SleepTracker sleepTracker) {
        if (repository.existsByToday(sleepTracker.getToday())) {
            throw new IllegalArgumentException("You can't enter the same date twice :)");
        }
        return repository.save(sleepTracker);
    }

    public void deleteSleepTracker(Long id) {
        repository.deleteById(id);
    }
}
