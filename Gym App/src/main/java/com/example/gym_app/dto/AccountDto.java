package com.example.gym_app.dto;

import com.example.gym_app.model.SleepTracker;
import com.example.gym_app.model.WaterTracker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private String name;
    private Integer age ;
    private Double weight ;
    private Double height;
    List<WaterTracker> waterTrackerList;
    List<SleepTracker> sleepTrackerList;
}
