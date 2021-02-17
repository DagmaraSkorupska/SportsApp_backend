package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Reservation reservationEntity1 = new Reservation(489L,10, Date.from(Instant.now()), null, null);
        reservationRepository.save(reservationEntity1);
        //When
        long countOfReservation = reservationRepository.count();
        //Then
        assertEquals(1, countOfReservation);
        //CleanUp
        reservationRepository.deleteAll();
    }

    @Test
    public void testDeleteReservation(){
        //Given
        Reservation reservationEntity1 = new Reservation(798L,120, Date.from(Instant.now()), null, null);
        reservationRepository.save(reservationEntity1);
        //When
        reservationRepository.delete(reservationEntity1);
        long countOfReservation = reservationRepository.count();
        //Then
        assertEquals(0, countOfReservation);
        //CleanUp
        reservationRepository.deleteAll();
    }

}

