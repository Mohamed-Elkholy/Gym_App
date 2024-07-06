package com.example.gym_app.repository;

import com.example.gym_app.model.SleepTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SleepTrackerRepository extends JpaRepository<SleepTracker, Long> {
    boolean existsByToday(LocalDate today);
}
