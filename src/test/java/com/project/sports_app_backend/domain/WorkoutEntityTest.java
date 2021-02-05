package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.WorkoutRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutEntityTest {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    public void saveNewWorkoutTest(){
        //Given
        WorkoutEntity workout1 = new WorkoutEntity("Swim","desc" ,60, 100, "address1", new Date(2020,02,20));
        workoutRepository.save(workout1);

        //When
        long countWorkout = workoutRepository.count();
        String nameWorkout1 = workout1.getName();

        //Then
        Assert.assertEquals(1, countWorkout);
        Assert.assertEquals("Swim", nameWorkout1);

        //CleanUp
        workoutRepository.deleteAll();
    }

    @Test
    public void deleteWorkoutTest(){
        //Given
        WorkoutEntity workout1 = new WorkoutEntity("Swim","desc" ,60, 100, "address1", new Date(2020,02,20));
        workoutRepository.save(workout1);

        //When
        workoutRepository.delete(workout1);
        long countWorkout = workoutRepository.count();

        //Then
        Assert.assertEquals(0, countWorkout);

        //CleanUp
        workoutRepository.deleteAll();
    }
}
