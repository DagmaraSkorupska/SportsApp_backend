package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.ReservationRepository;
import com.project.sports_app_backend.repository.UserRepository;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ReservationEntityTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSaveReservation() {
        //Given
        ReservationEntity reservationEntity1 = new ReservationEntity(120, new Date(2020,5,23), null, null);
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
        ReservationEntity reservationEntity1 = new ReservationEntity(120, new Date(2020,5,23), null, null);
        reservationRepository.save(reservationEntity1);

        //When
        reservationRepository.delete(reservationEntity1);
        long countOfReservation = reservationRepository.count();

        //Then
        assertEquals(0, countOfReservation);
    }

}

