package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.ReservationDto;
import com.project.sports_app_backend.domain.ReservationEntity;
import com.project.sports_app_backend.domain.WorkoutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private UserMapper userMapper;

    public ReservationEntity mapToReservationEntity(final ReservationDto reservationDto){
        return new ReservationEntity(
                reservationDto.getId(),
                workoutMapper.mapToWorkoutEntity(reservationDto.getWorkoutId()),
                userMapper.mapToUserEntity(reservationDto.getUserId()),
                reservationDto.getToPay(),
                reservationDto.getDate()
        );
    }

    public ReservationDto mapToReservationDto(final ReservationEntity reservationEntity){
        return new ReservationDto(
                reservationEntity.getId(),
                new WorkoutDto(),
                userMapper.mapToUserDto(reservationEntity.getUserEntity()),
                reservationEntity.getToPay(),
                reservationEntity.getDate()
        );
    }
}
