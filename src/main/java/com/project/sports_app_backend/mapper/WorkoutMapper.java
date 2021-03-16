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
    private ReservationMapper reservationMapper;

    @Autowired
    private UserMapper userMapper;

    public WorkoutEntity mapToWorkoutEntity( WorkoutDto workoutDto)  {
        return new WorkoutEntity(
                workoutDto.getName(),
                workoutDto.getDescription(),
                workoutDto.getDurationMin(),
                workoutDto.getPrice1h(),
                workoutDto.getAddress(),
                workoutDto.getDay(),
                workoutDto.getHour(),
                userMapper.mapToUserEntity(workoutDto.getUserDtos())
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
                userMapper.mapToUserDto(workoutEntity.getUser()),
                workoutEntity.getDay(),
                workoutEntity.getHour()
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
                        userMapper.mapToUserDto(workout.getUser()),
                        workout.getDay(),
                        workout.getHour()
                ))
                .collect(Collectors.toList());
    }

    public List<WorkoutEntity> mapToWorkoutEntityList(List<WorkoutDto> workoutDtos) {
        return workoutDtos.stream()
                .map(workout -> new WorkoutEntity(
                        workout.getName(),
                        workout.getDescription(),
                        workout.getDurationMin(),
                        workout.getPrice1h(),
                        workout.getAddress(),
                        workout.getDay(),
                        workout.getHour(),
                        userMapper.mapToUserEntity(workout.getUserDtos())
                ))
                .collect(Collectors.toList());
    }
}
