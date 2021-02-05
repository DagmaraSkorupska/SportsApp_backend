package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.ReservationRepository;
import com.project.sports_app_backend.repository.UserRepository;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collector;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationEntityTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void cleanUp() {
        reservationRepository.deleteAll();
        workoutRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSaveReservation() {
        //Given
        ReservationEntity reservationEntity1 = new ReservationEntity(120, new Date(2020,5,23));
        reservationRepository.save(reservationEntity1);

        //When
        long countOfReservation = reservationRepository.count();

        //Then
        Assert.assertEquals(1, countOfReservation);

        //CleanUp
        reservationRepository.deleteAll();
    }

    @Test
    public void testDeleteReservation(){
        //Given
        ReservationEntity reservationEntity1 = new ReservationEntity(120, new Date(2020,5,23));
        reservationRepository.save(reservationEntity1);

        //When
        reservationRepository.delete(reservationEntity1);
        long countOfReservation = reservationRepository.count();

        //Then
        Assert.assertEquals(0, countOfReservation);
    }

}

