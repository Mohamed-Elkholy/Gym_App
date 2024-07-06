package com.example.gym_app.repository;

import com.example.gym_app.model.WaterTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WaterTrackerRepository extends JpaRepository<WaterTracker, Long> {
    boolean existsByToday(LocalDate today);
}
