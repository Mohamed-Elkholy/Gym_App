package com.example.gym_app.controller;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.service.SleepTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sleep_tracker")
@RequiredArgsConstructor
@Validated
public class SleepTrackerController {

    private final SleepTrackerService service;

    @PostMapping
    public ResponseEntity<SleepTracker> addSleepTracker(@Valid @RequestBody
                                                        SleepTracker sleepTracker) {
        return ResponseEntity.ok(service.addSleepTracker(sleepTracker));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSleepTracker(@PathVariable Long id) {
        service.deleteSleepTracker(id);
        return ResponseEntity.noContent().build();
    }
}
