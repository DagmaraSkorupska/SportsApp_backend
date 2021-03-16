package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private double toPay;
    private String day;
    private String hour;
    private UserDto userId;
    private WorkoutDto workoutDto;
}
