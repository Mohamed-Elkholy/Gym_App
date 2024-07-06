package com.example.gym_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be more than 150")
    private Integer age ;
    private Double weight ;
    private Double height;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WaterTracker> waterTrackers = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SleepTracker> sleepTrackers = new ArrayList<>();

}
