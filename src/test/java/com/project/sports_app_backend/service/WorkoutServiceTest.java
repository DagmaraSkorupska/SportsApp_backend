package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.SportRepository;
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
        List<SportEntity> sportEntities = new ArrayList<>();
        workoutEntities.add(new WorkoutEntity(1234L,"name", "decs", 60, 120, "address", sportEntities));
        workoutEntities.add(new WorkoutEntity(456L,"name2", "decs2", 60, 120, "address2", sportEntities));
        when(workoutRepository.findAll()).thenReturn(workoutEntities);
        //when
        List<WorkoutEntity> resultList = workoutService.getAllWorkout();
        //then
        assertEquals(2, resultList.size());
    }

    @Test
    public void testGetWorkoutById(){
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = (new WorkoutEntity(1234L,"name", "decs", 60, 120, "address", sportEntities));
        when(workoutRepository.findById(1234L)).thenReturn(Optional.of(workoutEntity));
        //when
        WorkoutEntity result = workoutService.getWorkoutById(1234L).orElse(new WorkoutEntity());
        //then
        assertEquals(1234L, result.getId());
    }

    @Test
    public void testSaveWorkout(){
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = (new WorkoutEntity(1234L,"name", "decs", 60, 120, "address", sportEntities));
        when(workoutRepository.saveWorkout(workoutEntity)).thenReturn(workoutEntity);
        //when
        WorkoutEntity result = workoutService.saveWorkout(workoutEntity);
        //then
        assertEquals("name", result.getName());
    }







}
