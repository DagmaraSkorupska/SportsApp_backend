package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SportDto {
    private Long id;
    private String name;
    private String description;
    private List<UserEntity> users;
    private WorkoutEntity workouts;
}
