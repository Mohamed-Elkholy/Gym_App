package com.example.gym_app.service;

import com.example.gym_app.model.User;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterTrackerService {

    private final WaterTrackerRepository repository;

    public WaterTracker addWaterTracker(WaterTracker waterTracker, User user) {
        if (repository.existsByToday(waterTracker.getToday())) {
            throw new IllegalArgumentException("You can't enter the same date twice :)");
        }
        waterTracker.setUser(user);
        return repository.save(waterTracker);
    }

    public void deleteWaterTracker(Long id) {
        repository.deleteById(id);
    }

    public List<WaterTracker> getWaterTrackerListForWeek() throws ChangeSetPersister.NotFoundException {
        List<WaterTracker> result = repository.findAll();
        if (result == null) throw new ChangeSetPersister.NotFoundException();
        return (result.size() > 7 ? result.subList(0,7) : result);
    }
}
