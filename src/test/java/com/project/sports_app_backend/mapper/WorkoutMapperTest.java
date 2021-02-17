package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.controller.ReservationNotFoundException;
import com.project.sports_app_backend.controller.SportNotFoundException;
import com.project.sports_app_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkoutMapperTest {
    @Autowired
    private WorkoutMapper workoutMapper;

    @Test
    public void testMapToWorkoutEntity() {
        //given
        List<SportDto> sportDtos = new ArrayList<>();
        WorkoutDto workoutDto = new WorkoutDto(1L, "name", "desc", 60,
                100, "address", sportDtos);
        //when
        WorkoutEntity workoutEntity = workoutMapper.mapToWorkoutEntity(workoutDto);
        String name = workoutEntity.getName();
        double price = workoutEntity.getPrice1h();
        //then
        assertEquals("name", name);
        assertEquals(100, price);
    }

    @Test
    public void testMapToWorkoutDto(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = new WorkoutEntity(5L,"name", "desc", 60,
                100, "address", sportEntities);
        //when
        WorkoutDto workoutDto = workoutMapper.mapToWorkoutDto(workoutEntity);
        String desc = workoutDto.getDescription();
        int time = workoutDto.getDurationMin();
        //then
        assertEquals("desc", desc);
        assertEquals(60, time);
    }

    @Test
    public void testMapWorkoutDtoList(){
        List<SportEntity> sportEntities = new ArrayList<>();
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        workoutEntities.add(new WorkoutEntity(5L,"name3", "desc3", 60,
                100, "address", sportEntities));
        //when
        List<WorkoutDto> workoutDto = workoutMapper.mapToWorkoutDtoList(workoutEntities);
        //then
        assertEquals(1, workoutDto.size());
    }

}
