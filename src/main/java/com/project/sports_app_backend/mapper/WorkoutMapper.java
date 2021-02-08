package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.ReservationEntity;
import com.project.sports_app_backend.domain.WorkoutDto;
import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkoutMapper {

    @Autowired
    private SportMapper sportMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    public WorkoutEntity mapToWorkoutEntity(final WorkoutDto workoutDto){
        return new WorkoutEntity(
                workoutDto.getName(),
                workoutDto.getDescription(),
                workoutDto.getDurationMin(),
                workoutDto.getPrice1h(),
                workoutDto.getDate(),
                workoutDto.getAddress()
        );
    }

    public WorkoutDto mapToWorkoutDto(final WorkoutEntity workoutEntity){
        return new WorkoutDto(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getDescription(),
                workoutEntity.getDurationMin(),
                workoutEntity.getPrice1h(),
                workoutEntity.getDate(),
                workoutEntity.getAddress(),
                sportMapper.mapToSportDtoList(workoutEntity.getSport())
        );
    }

}
