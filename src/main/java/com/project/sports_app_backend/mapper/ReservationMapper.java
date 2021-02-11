package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.ReservationDto;
import com.project.sports_app_backend.domain.ReservationEntity;
import com.project.sports_app_backend.domain.WorkoutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private UserMapper userMapper;

    public ReservationEntity mapToReservationEntity( ReservationDto reservationDto )  {
        return new ReservationEntity(
                reservationDto.getToPay(),
                reservationDto.getDate(),
                userMapper.mapToUserEntity(reservationDto.getUserId()),
                workoutMapper.mapToWorkoutEntity(reservationDto.getWorkoutDto())
        );
    }

    public ReservationDto mapToReservationDto( ReservationEntity reservationEntity){
        return new ReservationDto(
                reservationEntity.getId(),
                reservationEntity.getToPay(),
                reservationEntity.getDate(),
                userMapper.mapToUserDto(reservationEntity.getUserEntity()),
                workoutMapper.mapToWorkoutDto(reservationEntity.getWorkoutEntity())
        );
    }

    public List<ReservationDto> mapToReservationDtoList(List<ReservationEntity> reservationEntity){
        return reservationEntity.stream()
                .map(reservation -> new ReservationDto(
                        reservation.getId(),
                        reservation.getToPay(),
                        reservation.getDate(),
                        userMapper.mapToUserDto(reservation.getUserEntity()),
                        workoutMapper.mapToWorkoutDto(reservation.getWorkoutEntity())
                ))
                .collect(Collectors.toList());
    }

    public List<ReservationEntity> mapToReservationEntityList(List<ReservationDto> reservationDtos){
        return reservationDtos.stream()
                .map(reservationDto -> new ReservationEntity(
                                reservationDto.getToPay(),
                                reservationDto.getDate(),
                                userMapper.mapToUserEntity(reservationDto.getUserId()),
                                workoutMapper.mapToWorkoutEntity(reservationDto.getWorkoutDto())
                    ))
                .collect(Collectors.toList());
    }
}
