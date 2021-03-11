package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservationEntityTest {


    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void cleanUp(){
        reservationRepository.deleteAll();
    }

    @Test
    public void testSaveReservation() {
        //Given
        Reservation reservationEntity1 = new Reservation(10, LocalDateTime.now(), null, null);
        reservationRepository.save(reservationEntity1);
        //When
        long countOfReservation = reservationRepository.count();
        //Then
        assertEquals(1, countOfReservation);
        //CleanUp
        reservationRepository.deleteAll();
    }

    @Test
    public void testDeleteReservation() {
        //Given
        Reservation reservationEntity1 = new Reservation( 120, LocalDateTime.now(), null, null);
        Reservation save = reservationRepository.save(reservationEntity1);
        //When
        reservationRepository.delete(save);
        long countOfReservation = reservationRepository.count();
        //Then
        assertEquals(0, countOfReservation);
        //CleanUp
        reservationRepository.deleteAll();
    }

}

