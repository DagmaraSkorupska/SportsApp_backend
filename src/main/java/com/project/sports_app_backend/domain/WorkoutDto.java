package com.project.sports_app_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    private final List<SportDto> sport = new ArrayList<>();
    private ReservationDto reservationId ;
}
