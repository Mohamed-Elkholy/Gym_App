package com.example.gym_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BMIRequest {
    private Integer age ;
    private Double weight ;
    private Double height;
}
