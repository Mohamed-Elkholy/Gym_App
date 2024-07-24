package com.example.gym_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] photo;
    @ElementCollection
    @JoinTable(name = "excecise_sets", joinColumns = @JoinColumn(name = "exercise_id"))
    private List<String> sets = new ArrayList<>();
    @ElementCollection
    @JoinTable(name = "excecise_description", joinColumns = @JoinColumn(name = "exercise_id"))
    private List<String> description = new ArrayList<>();
    @ElementCollection
    @JoinTable(name = "excecise_instructions", joinColumns = @JoinColumn(name = "exercise_id"))
    private List<String> instructions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonBackReference
    private Workout workout;

}
