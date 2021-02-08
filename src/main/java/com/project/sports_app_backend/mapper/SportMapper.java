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

    public SportEntity mapToSportEntity(final SportDto sportDto){
        return new SportEntity(
                sportDto.getId(),
                sportDto.getName(),
                sportDto.getDescription(),
                userMapper.mapToUserEntitesList(sportDto.getUsers()),
                workoutMapper,mapToSportDto(sportDto.getWorkouts())
        );
    }

    public SportDto mapToSportDto(final SportEntity sportEntity){
        return new SportDto(
                sportEntity.getId(),
                sportEntity.getName(),
                sportEntity.getDescription(),
                userMapper.mapToUserDto(sportEntity.getUsers()),
                workoutMapper.mapToWorkoutDto(sportEntity.getWorkouts())
        );
    }

    public List<SportDto> mapToSportDtoList(final List<SportEntity> sportEntities){
        return sportEntities.stream()
                .map(sport -> new SportDto(
                        sport.getId(),
                        sport.getName(),
                        sport.getDescription(),
                        userMapper.mapToUserDtoL(sport.getUsers()),
                        workoutMapper.mapToWorkoutDto(sport.getWorkouts())
                        )
                )
                .collect(Collectors.toList());
    }
}
