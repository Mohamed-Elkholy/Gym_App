package com.example.gym_app.repository;

import com.example.gym_app.model.SleepTracker;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SleepTrackerRepository extends JpaRepository<SleepTracker, Long> {
    @Query("select x from SleepTracker x where x.user.id = :id")
    List<SleepTracker> findAllByUser(@Param("id") Long id);
    @Query("select count(x) from SleepTracker x where x.today = :today and x.user.id = :id")
    int countByToday(@Param("today") LocalDate today, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM SleepTracker x WHERE x.id = :trackerId AND x.user.id = :userId")
    void deleteByIdAndUserId(@Param("trackerId") Long trackerId, @Param("userId") Long userId);
}
