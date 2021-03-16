package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {
    private Long id;
    private String name;
    private String description;
    private int durationMin;
    private double price1h;
    private String address;
    private UserDto userDtos;
    private String day;
    private String hour;

}
