package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkoutMapperTest {
    @Autowired
    private WorkoutMapper workoutMapper;

    @Test
    public void testMapToWorkoutEntity() {
        //given
        List<UserDto> userDtos = new ArrayList<>();
        WorkoutDto workoutDto = new WorkoutDto(1L, "name", "desc", 60,
                100, "address", userDtos);
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
        List<UserEntity> userEntities = new ArrayList<>();
        WorkoutEntity workoutEntity = new WorkoutEntity("name", "desc", 60,
                100, "address", "Monday", "18:00" );
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
        List<UserEntity> userEntities = new ArrayList<>();
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        workoutEntities.add(new WorkoutEntity("name3", "desc3", 60,
                100, "address", "Monday", "18:00" ));
        //when
        List<WorkoutDto> workoutDto = workoutMapper.mapToWorkoutDtoList(workoutEntities);
        //then
        assertEquals(1, workoutDto.size());
    }

}
