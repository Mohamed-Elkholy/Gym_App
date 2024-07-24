package com.example.gym_app.dto;

import com.example.gym_app.model.Exercise;
import lombok.Data;

import java.util.List;
@Data
public class WorkoutDto {
    String name;
    List<Exercise> exercises;
}
