package com.example.gym_app.repository;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface WaterTrackerRepository extends JpaRepository<WaterTracker, Long> {

    @Query("select x from WaterTracker x where x.user.id = :id")
    List<WaterTracker> findAllByUser(@Param("id") Long id);
    @Query("select count(x) from WaterTracker x where x.today = :today and x.user.id = :id")
    int countByToday(@Param("today") LocalDate today, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM WaterTracker x WHERE x.id = :trackerId AND x.user.id = :userId")
    void deleteByIdAndUserId(@Param("trackerId") Long trackerId, @Param("userId") Long userId);

    @Query("select x from WaterTracker x where x.today = :date and x.user.id = :id")
    Optional<WaterTracker> findWaterTrackerByDay(@Param("date") LocalDate date, @Param("id") Long id);
}
