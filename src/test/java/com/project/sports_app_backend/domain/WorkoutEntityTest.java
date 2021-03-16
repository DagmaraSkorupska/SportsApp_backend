package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class WorkoutEntityTest {
    @Autowired
    private WorkoutRepository workoutRepository;

    @BeforeEach
    public void cleanUp(){
        workoutRepository.deleteAll();
    }

    @Test
    public void saveNewWorkoutTest(){
        //Given
        List<UserEntity> userEntities = new ArrayList<>();
        WorkoutEntity workout1 = new WorkoutEntity("Swim","desc" ,60, 100, "address1", "Monday", "18:00");
        workoutRepository.save(workout1);

        //When
        long countWorkout = workoutRepository.count();
        String nameWorkout1 = workout1.getName();

        //Then
        assertEquals(1, countWorkout);
        assertEquals("Swim", nameWorkout1);

        //CleanUp
        workoutRepository.deleteAll();
    }

    @Test
    public void deleteWorkoutTest(){
        //Given
        List<UserEntity> userEntities = new ArrayList<>();
        WorkoutEntity workout1 = new WorkoutEntity("Swim2","desc2" ,60, 100, "address1", "Monday", "18:00");
        WorkoutEntity save = workoutRepository.save(workout1);

        //When
        workoutRepository.delete(save);
        long countWorkout = workoutRepository.count();

        //Then
        assertEquals(0, countWorkout);

        //CleanUp
        workoutRepository.deleteAll();
    }
}
