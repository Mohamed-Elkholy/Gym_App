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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/water_tracker")
@RequiredArgsConstructor
@Validated
public class WaterTrackerController {

    private final WaterTrackerService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<WaterTracker> addWaterTracker(@Valid @RequestBody
                                                        WaterTracker waterTracker,
                                                        @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user= userService.findUserProfileByJwt(jwt);
        return ResponseEntity.ok(service.addWaterTracker(waterTracker, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterTracker(@PathVariable Long id) {
        service.deleteWaterTracker(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<WaterTracker>> getWaterTrackerListForWeek() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.getWaterTrackerListForWeek());
    }

}
