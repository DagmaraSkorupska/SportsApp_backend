package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkoutServiceTest {

    @InjectMocks
    private WorkoutService workoutService;

    @Mock
    WorkoutRepository workoutRepository;

    @Test
    public void testGetAllWorkout(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        workoutEntities.add(new WorkoutEntity("name", "decs", 60, 120, "address", "Monday", "18:00" ));
        workoutEntities.add(new WorkoutEntity("name2", "decs2", 60, 120, "address2", "Monday", "18:00" ));
        when(workoutRepository.findAll()).thenReturn(workoutEntities);
        //when
        List<WorkoutEntity> resultList = workoutService.getAllWorkout();
        //then
        assertEquals(2, resultList.size());
        //cleanUp
        workoutRepository.deleteAll();
    }

    @Test
    public void testGetWorkoutById(){
        List<UserEntity> userEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = (new WorkoutEntity("name", "decs", 60, 120, "address", "Monday", "18:00" ));
        when(workoutRepository.findById(workoutEntity.getId())).thenReturn(Optional.of(workoutEntity));
        //when
        WorkoutEntity result = workoutService.getWorkoutById(workoutEntity.getId()).orElse(new WorkoutEntity());
        //then
        assertEquals(workoutEntity.getId(), result.getId());
        //cleanUp
        workoutRepository.deleteAll();
    }

    @Test
    public void testSaveWorkout(){
        List<UserEntity> userEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = (new WorkoutEntity("name", "decs", 60, 120, "address", "Monday", "18:00" ));
        when(workoutRepository.save(workoutEntity)).thenReturn(workoutEntity);
        //when
        WorkoutEntity result = workoutService.saveWorkout(workoutEntity);
        //then
        assertEquals("name", result.getName());
        //cleanUp
        workoutRepository.deleteAll();
    }







}
