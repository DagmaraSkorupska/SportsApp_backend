package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.controller.ReservationNotFoundException;
import com.project.sports_app_backend.controller.SportNotFoundException;
import com.project.sports_app_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    public void testMapToReservationEntity(){
        //given
        ReservationDto reservationDto = new ReservationDto(1L, new WorkoutDto(), new UserDto(), 25, new Date());
        //when
        ReservationEntity reservationEntity = reservationMapper.mapToReservationEntity(reservationDto);
        double toPay = reservationEntity.getToPay();
        //then
        assertEquals(25, toPay);
    }

    @Test
    public void testMapToReservationDto(){
        //given
        ReservationEntity reservationEntity = new ReservationEntity(10, new Date(), new WorkoutEntity(), new UserEntity());
        //when
        ReservationDto reservationDto = reservationMapper.mapToReservationDto(reservationEntity);
        double toPay = reservationDto.getToPay();
        //then
        assertEquals(10, toPay);
    }

    @Test
    public void testMapToReservationDtoList(){
        //given
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        reservationEntities.add(new ReservationEntity(10,new Date(), new WorkoutEntity(), new UserEntity()));
        //when
        List<ReservationDto> reservationDtos = reservationMapper.mapToReservationDtoList(reservationEntities);
        int size = reservationDtos.size();
        //then
        assertEquals(1, size);
    }


}
