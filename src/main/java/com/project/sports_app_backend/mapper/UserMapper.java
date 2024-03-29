package com.project.sports_app_backend.mapper;


import com.project.sports_app_backend.domain.UserDto;
import com.project.sports_app_backend.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    public UserEntity mapToUserEntity ( UserDto userDto){
            return new UserEntity(
                    userDto.getType(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail(),
                    userDto.getPassword(),
                    userDto.getDescription(),
                    userDto.getPhone()
            );

    }

    public UserDto mapToUserDto( UserEntity userEntity){
        return new UserDto(
                userEntity.getId(),
                userEntity.getType(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getDescription(),
                userEntity.getPhone(),
                workoutMapper.mapToWorkoutDtoList(userEntity.getWorkout()),
                reservationMapper.mapToReservationDtoList(userEntity.getReservation())
        );
    }

    public List<UserDto> mapToUserDtoList(List<UserEntity> userEntity){
        return userEntity.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getType(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getDescription(),
                        user.getPhone(),
                        workoutMapper.mapToWorkoutDtoList(user.getWorkout()),
                        reservationMapper.mapToReservationDtoList(user.getReservation())
                        ))
                .collect(Collectors.toList());
    }

    public List<UserEntity> mapToUserEntityList(List<UserDto> userDtos){
        return userDtos.stream()
                .map(userDto -> new UserEntity(
                        userDto.getType(),
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail(),
                        userDto.getPassword(),
                        userDto.getDescription(),
                        userDto.getPhone()))
                .collect(Collectors.toList());
    }
}
