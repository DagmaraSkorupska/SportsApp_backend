package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private WorkoutDto workoutId;
    private UserDto userId;
    private double toPay;
    private Date date;

}
