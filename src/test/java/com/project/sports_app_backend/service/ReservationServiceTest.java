package com.project.sports_app_backend.service;


import com.project.sports_app_backend.domain.*;
import com.project.sports_app_backend.repository.ReservationRepository;
import com.project.sports_app_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
        reservations.add(new Reservation( 133, LocalDateTime.now(), new UserEntity(), new WorkoutEntity()));
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
        Reservation reservation = new Reservation(133, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());
        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        //when
        Reservation result = reservationService.getReservationById(reservation.getId()).orElse(new Reservation());
        //then
        assertEquals(reservation.getId(), result.getId());
        //clean up
        reservationRepository.deleteAll();
    }


    @Test
    public void testGetReservationByDate(){
        //given
        Reservation reservation = new Reservation( 133, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());
        when(reservationRepository.findByDate(LocalDateTime.now())).thenReturn(Optional.of(reservation));
        //when
        Reservation result = reservationService.getReservationByDate(LocalDateTime.now()).orElse(new Reservation());
        //then
        assertEquals( 133, result.getToPay());
    }

    @Test
    public void testReservationByUserLogin(){
        //given
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation( 133, LocalDateTime.now(), new UserEntity(), new WorkoutEntity()));
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(reservationRepository.findByUserEntity(userEntity)).thenReturn(reservations);
        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));
        //when
        List<Reservation> resultList = reservationService.getReservationByUserLogin(userEntity.getEmail());
        //then
        assertEquals(1, resultList.size());
    }

    @Test
    public void testSaveReservation(){
        //given
        Reservation reservation = new Reservation( 133, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        //when
        Reservation result = reservationService.saveReservation(reservation);
        //then
        assertEquals(133, result.getToPay());
        //clean up
        reservationRepository.deleteAll();

    }

}
