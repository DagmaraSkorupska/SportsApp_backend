package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.WorkoutDto;
import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class WorkoutMapper {

    @Autowired
    private SportMapper sportMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    public WorkoutEntity mapToWorkoutEntity( WorkoutDto workoutDto)  {
        return new WorkoutEntity(
                workoutDto.getName(),
                workoutDto.getDescription(),
                workoutDto.getDurationMin(),
                workoutDto.getPrice1h(),
                workoutDto.getAddress(),
                sportMapper.mapToSportEntityList(workoutDto.getSport())
        );
    }

    public WorkoutDto mapToWorkoutDto( WorkoutEntity workoutEntity){
        return new WorkoutDto(
                workoutEntity.getId(),
                workoutEntity.getName(),
                workoutEntity.getDescription(),
                workoutEntity.getDurationMin(),
                workoutEntity.getPrice1h(),
                workoutEntity.getAddress(),
                sportMapper.mapToSportDtoList(workoutEntity.getSport())
        );
    }

    public List<WorkoutDto> mapToWorkoutDtoList(List<WorkoutEntity> workoutEntities){
        return workoutEntities.stream()
                .map(workout -> new WorkoutDto(
                        workout.getId(),
                        workout.getName(),
                        workout.getDescription(),
                        workout.getDurationMin(),
                        workout.getPrice1h(),
                        workout.getAddress(),
                        sportMapper.mapToSportDtoList(workout.getSport())
                ))
                .collect(Collectors.toList());
    }

}
