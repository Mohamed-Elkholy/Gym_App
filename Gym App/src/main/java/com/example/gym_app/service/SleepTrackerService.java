package com.example.gym_app.service;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.User;
import com.example.gym_app.repository.SleepTrackerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SleepTrackerService {

    private final SleepTrackerRepository repository;

    public SleepTracker addSleepTracker(SleepTracker sleepTracker, Authentication connectedUser) {
        User savedUser = (User) connectedUser.getPrincipal();
        if (repository.countByToday(sleepTracker.getToday(), savedUser.getId()) > 0) {
            throw new IllegalArgumentException("You can't enter the same date twice :)");
        }
        sleepTracker.setUser(savedUser);
        return repository.save(sleepTracker);
    }

    public void deleteSleepTracker(Long id, Authentication connectedUser) {
        User savedUser = (User) connectedUser.getPrincipal();
        repository.deleteByIdAndUserId(id, savedUser.getId());
    }

    public List<SleepTracker> getSleepTrackerListForWeek(LocalDate date, Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        User user = (User) connectedUser.getPrincipal();
        List<SleepTracker> result = repository.findAllByUser(user.getId());
        if (result == null) throw new ChangeSetPersister.NotFoundException();
        result = (result.size() > 7 ? result.subList(0,7): result);
        result.sort((x,y) -> x.getToday().compareTo(y.getToday()));
        return result;
    }

    public Optional<SleepTracker> getSleepTrackerByDay(LocalDate date, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        return repository.findSleepTrackerByDay(date, user.getId());
    }
}
