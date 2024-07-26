package com.example.gym_app.controller;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.User;
import com.example.gym_app.model.WaterTracker;
import com.example.gym_app.service.UserService;
import com.example.gym_app.service.WaterTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/water_tracker")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class WaterTrackerController {

    private final WaterTrackerService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<WaterTracker> addWaterTracker(@Valid @RequestBody
                                                        WaterTracker waterTracker,
                                                        Authentication connectedUser
    ) throws Exception {
        return ResponseEntity.ok(service.addWaterTracker(waterTracker, connectedUser));
    }
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSleepTracker(@PathVariable Long id, Authentication connectedUser) {
        service.deleteSleepTracker(id, connectedUser);
        return ResponseEntity.ok("Deleted Successfully :)");
    }
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWaterTracker(@PathVariable Long id, Authentication connectedUser) {
        service.deleteWaterTracker(id, connectedUser);
        return ResponseEntity.ok("Deleted Successfully :)");
    }
    @GetMapping
    public ResponseEntity<List<WaterTracker>> getWaterTrackerListForWeek(Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.getWaterTrackerListForWeek(connectedUser));
    }

    @GetMapping("/Date")
    public ResponseEntity<WaterTracker> getWaterTrackerByDay(@RequestParam("date") LocalDate date, Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        Optional<WaterTracker> waterTracker = service.getWaterTrackerByDay(date, connectedUser);
        if (waterTracker == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(waterTracker.get());
    }

}
