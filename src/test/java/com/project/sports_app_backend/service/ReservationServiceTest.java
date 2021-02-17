package com.project.sports_app_backend.service;


import com.project.sports_app_backend.domain.*;
import com.project.sports_app_backend.repository.ReservationRepository;
import com.project.sports_app_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    UserRepository userRepository;

    @Test
    public void testGetAllReservation(){
        //given
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(14L, 133, new Date(), new UserEntity(), new WorkoutEntity()));
        when(reservationRepository.findAll()).thenReturn(reservations);
        //when
        List<Reservation> resultList = reservationService.getAllReservation();
        //then
        assertEquals(1, resultList.size());
        //clean up
        reservationRepository.deleteAll();
    }

    @Test
    public void testGetReservationById(){
        //given
        Reservation reservation = new Reservation(14L, 133, new Date(), new UserEntity(), new WorkoutEntity());
        when(reservationRepository.findById(14L)).thenReturn(Optional.of(reservation));
        //when
        Reservation result = reservationService.getReservationById(14L).orElse(new Reservation());
        //then
        assertEquals(14L, result.getId());
        //clean up
        reservationRepository.deleteAll();
    }

//    @Test
//    public void testGetReservationByDate(){
//        //given
//        Reservation reservation = new Reservation(14L, 133, Date.from(Instant.now()), new UserEntity(), new WorkoutEntity());
//        when(reservationRepository.findByDate(Date.from(Instant.now()))).thenReturn(Optional.of(reservation));
//        //when
//        Reservation result = reservationService.getReservationByDate(new Date()).orElse(new Reservation());
//        //then
//        assertEquals( 14L, result.getId());
//    }

//    @Test
//    public void testReservationByUserLogin(){
//        //given
//        List<Reservation> reservations = new ArrayList<>();
//        reservations.add(new Reservation(14L, 133, new Date(), new UserEntity(), new WorkoutEntity()));
//        List<SportEntity> sportEntities = new ArrayList<>();
//        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
//        when(reservationRepository.findByUser(userEntity)).thenReturn(reservations);
//        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));
//        //when
//        List<Reservation> resultList = reservationService.getReservationByUserLogin(userEntity.getEmail());
//        //then
//        assertEquals(1, resultList.size());
//    }

    @Test
    public void testSaveReservation(){
        //given
        Reservation reservation = new Reservation(14L, 133, new Date(), new UserEntity(), new WorkoutEntity());
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        //when
        Reservation result = reservationService.saveReservation(reservation);
        //then
        assertEquals(133, result.getToPay());
        //clean up
        reservationRepository.deleteAll();

    }

}
