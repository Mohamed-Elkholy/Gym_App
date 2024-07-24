package com.example.gym_app.service;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.SleepTrackerRepository;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SleepTracker> getSleepTrackerListForWeek() throws ChangeSetPersister.NotFoundException {
        List<SleepTracker> result = repository.findAll();
        if (result == null) throw new ChangeSetPersister.NotFoundException();
        return (result.size() > 7 ? result.subList(0,7): result);
    }
}
