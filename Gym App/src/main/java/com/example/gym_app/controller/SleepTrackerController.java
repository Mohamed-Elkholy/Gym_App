package com.example.gym_app.controller;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.service.SleepTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sleep_tracker")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class SleepTrackerController {

    private final SleepTrackerService service;

    @PostMapping
    public ResponseEntity<SleepTracker> addSleepTracker(@Valid @RequestBody
                                                        SleepTracker sleepTracker,
                                                        Authentication connectedUser) {
        return ResponseEntity.ok(service.addSleepTracker(sleepTracker, connectedUser));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSleepTracker(@PathVariable Long id, Authentication connectedUser) {
        service.deleteSleepTracker(id, connectedUser);
        return ResponseEntity.ok("Deleted Successfully :)");
    }
    @GetMapping
    public ResponseEntity<List<SleepTracker>> getSleepTrackerListForWeek(Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.getSleepTrackerListForWeek(connectedUser));
    }
}
