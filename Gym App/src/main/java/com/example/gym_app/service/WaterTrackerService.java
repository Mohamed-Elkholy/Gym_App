package com.example.gym_app.service;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.User;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterTrackerService {

    private final WaterTrackerRepository repository;

    public WaterTracker addWaterTracker(WaterTracker waterTracker, Authentication connectedUser) {
        User savedUser = (User) connectedUser.getPrincipal();
        if (repository.countByToday(waterTracker.getToday(), savedUser.getId()) > 0) {
            throw new IllegalArgumentException("You can't enter the same date twice :)");
        }
        waterTracker.setUser(savedUser);
        return repository.save(waterTracker);
    }

    public void deleteWaterTracker(Long id, Authentication connectedUser) {
        User savedUser = (User) connectedUser.getPrincipal();
        repository.deleteByIdAndUserId(id, savedUser.getId());
    }

    public List<WaterTracker> getWaterTrackerListForWeek(Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        User user = (User) connectedUser.getPrincipal();
        List<WaterTracker> result = repository.findAllByUser(user.getId());
        if (result == null) throw new ChangeSetPersister.NotFoundException();
        return (result.size() > 7 ? result.subList(0,7): result);
    }
}
