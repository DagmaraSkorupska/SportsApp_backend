package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.ReservationDto;
import com.project.sports_app_backend.domain.Reservation;
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

    public Reservation mapToReservationEntity(ReservationDto reservationDto) {
        return new Reservation(
                reservationDto.getToPay(),
                reservationDto.getDay(),
                reservationDto.getHour(),
                userMapper.mapToUserEntity(reservationDto.getUserId()),
                workoutMapper.mapToWorkoutEntity(reservationDto.getWorkoutDto())
        );
    }

    public ReservationDto mapToReservationDto(Reservation reservationEntity) {
        return new ReservationDto(
                reservationEntity.getId(),
                reservationEntity.getToPay(),
                reservationEntity.getDay(),
                reservationEntity.getHour(),
                userMapper.mapToUserDto(reservationEntity.getUserEntity()),
                workoutMapper.mapToWorkoutDto(reservationEntity.getWorkoutEntity())
        );
    }

    public List<ReservationDto> mapToReservationDtoList(List<Reservation> reservationEntity) {
        return reservationEntity.stream()
                .map(reservation -> new ReservationDto(
                        reservation.getId(),
                        reservation.getToPay(),
                        reservation.getDay(),
                        reservation.getHour(),
                        userMapper.mapToUserDto(reservation.getUserEntity()),
                        workoutMapper.mapToWorkoutDto(reservation.getWorkoutEntity())
                ))
                .collect(Collectors.toList());
    }

    public List<Reservation> mapToReservationEntityList(List<ReservationDto> reservationDtos) {
        return reservationDtos.stream()
                .map(reservationDto -> new Reservation(
                        reservationDto.getToPay(),
                        reservationDto.getDay(),
                        reservationDto.getHour(),
                        userMapper.mapToUserEntity(reservationDto.getUserId()),
                        workoutMapper.mapToWorkoutEntity(reservationDto.getWorkoutDto())
                ))
                .collect(Collectors.toList());
    }
}
