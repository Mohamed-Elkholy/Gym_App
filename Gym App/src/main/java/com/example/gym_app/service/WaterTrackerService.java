package com.example.gym_app.service;

import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaterTrackerService {

    private final WaterTrackerRepository repository;

    public WaterTracker addWaterTracker(WaterTracker waterTracker) {
        if (repository.existsByToday(waterTracker.getToday())) {
            throw new IllegalArgumentException("You can't enter the same date twice :)");
        }
        return repository.save(waterTracker);
    }

    public void deleteWaterTracker(Long id) {
        repository.deleteById(id);
    }
}
