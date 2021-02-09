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

    public ReservationEntity mapToReservationEntity(final ReservationDto reservationDto){
        return new ReservationEntity(
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

    public List<ReservationDto> mapToReservationDtoList(List<ReservationEntity> reservationEntity){
        return reservationEntity.stream()
                .map(reservation -> new ReservationDto(
                        reservation.getId(),
                        new WorkoutDto(),
                        userMapper.mapToUserDto(reservation.getUserEntity()),
                        reservation.getToPay(),
                        reservation.getDate()
                ))
                .collect(Collectors.toList());
    }
}
