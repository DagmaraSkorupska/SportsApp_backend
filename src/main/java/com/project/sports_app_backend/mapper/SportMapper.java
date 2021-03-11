package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.SportDto;
import com.project.sports_app_backend.domain.SportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class SportMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkoutMapper workoutMapper;

    public SportEntity mapToSportEntity( SportDto sportDto) {
        return new SportEntity(
                sportDto.getName(),
                sportDto.getDescription(),
                userMapper.mapToUserEntityList(sportDto.getUsers()),
                workoutMapper.mapToWorkoutEntity(sportDto.getWorkouts())
        );
    }

    public SportDto mapToSportDto( SportEntity sportEntity){
        return new SportDto(
                sportEntity.getId(),
                sportEntity.getName(),
                sportEntity.getDescription(),
                userMapper.mapToUserDtoList(sportEntity.getUsers()),
                workoutMapper.mapToWorkoutDto(sportEntity.getWorkouts())
        );
    }

    public List<SportDto> mapToSportDtoList( List<SportEntity> sportEntities){
        return sportEntities.stream()
                .map(sport -> new SportDto(
                        sport.getId(),
                        sport.getName(),
                        sport.getDescription(),
                        userMapper.mapToUserDtoList(sport.getUsers()),
                        workoutMapper.mapToWorkoutDto(sport.getWorkouts()))
                )
                .collect(Collectors.toList());
    }

    public List<SportEntity> mapToSportEntityList(List<SportDto> sportDtos) {
        return sportDtos.stream()
                .map(sport -> new SportEntity(
                        sport.getName(),
                        sport.getDescription(),
                        userMapper.mapToUserEntityList(sport.getUsers()),
                        workoutMapper.mapToWorkoutEntity(sport.getWorkouts()))
                )
                .collect(Collectors.toList());
    }
}
