package com.project.sports_app_backend.mapper;

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
        ReservationDto reservationDto = new ReservationDto(1L,  25, new Date(), new UserDto(), new WorkoutDto());
        //when
        Reservation reservationEntity = reservationMapper.mapToReservationEntity(reservationDto);
        double toPay = reservationEntity.getToPay();
        //then
        assertEquals(25, toPay);
    }

    @Test
    public void testMapToReservationDto(){
        //given
        Reservation reservationEntity = new Reservation(10, new Date(), new UserEntity(), new WorkoutEntity());
        //when
        ReservationDto reservationDto = reservationMapper.mapToReservationDto(reservationEntity);
        double toPay = reservationDto.getToPay();
        //then
        assertEquals(10, toPay);
    }

    @Test
    public void testMapToReservationDtoList(){
        //given
        List<Reservation> reservationEntities = new ArrayList<>();
        reservationEntities.add(new Reservation(10,new Date(), new UserEntity(), new WorkoutEntity()));
        //when
        List<ReservationDto> reservationDtos = reservationMapper.mapToReservationDtoList(reservationEntities);
        int size = reservationDtos.size();
        //then
        assertEquals(1, size);
    }


}
