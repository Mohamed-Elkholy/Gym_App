package com.example.gym_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SleepTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Date is mandatory")
    private LocalDate today;
    @Min(value = 0, message = "Sleep duration should not be less than 0")
    private Integer sleepDuration ;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
