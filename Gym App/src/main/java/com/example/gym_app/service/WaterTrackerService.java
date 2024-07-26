package com.example.gym_app.service;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.User;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    /*
    public Optional<SleepTracker> getSleepTrackerByDay(LocalDate date, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        return repository.findSleepTrackerByDay(date, user.getId());
    }
     */

    public Optional<WaterTracker> getWaterTrackerByDay(LocalDate date, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        return repository.findWaterTrackerByDay(date, user.getId());
    }
}
