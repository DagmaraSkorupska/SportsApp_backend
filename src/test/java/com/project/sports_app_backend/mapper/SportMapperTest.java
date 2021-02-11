package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.controller.ReservationNotFoundException;
import com.project.sports_app_backend.controller.SportNotFoundException;
import com.project.sports_app_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SportMapperTest {

    @Autowired
    private SportMapper sportMapper;

    @Test
    public void testMapToSportEntity() {
        //given
        List<UserDto> userDtos = new ArrayList<>();
        SportDto sportDto = new SportDto(1L, "name", "description", new WorkoutDto());
        //when
        SportEntity sportEntity = sportMapper.mapToSportEntity(sportDto);
        String name = sportEntity.getName();
        //then
        assertEquals("name", name);

    }

    @Test
    public void testMapToSportDto(){
        //given
        List<UserEntity> userDtos = new ArrayList<>();
        SportEntity sportEntity = new SportEntity( "name", "description", userDtos, new WorkoutEntity());
        //when
        SportDto sportDto = sportMapper.mapToSportDto(sportEntity);
        String name = sportDto.getName();
        //then
        assertEquals("name",name);
    }

    @Test
    public void testMapToSportDtoList(){
        //given
        List<UserEntity> userDtos = new ArrayList<>();
        List<SportEntity> sportEntities = new ArrayList<>();
        sportEntities.add(new SportEntity( "name", "description", userDtos, new WorkoutEntity()));
        //when
        List<SportDto> sportDtos = sportMapper.mapToSportDtoList(sportEntities);
        int size = sportDtos.size();
        //then
        assertEquals(1, size);
    }

    @Test
    public void testMapToSportEntitiesList() {
        //given
        List<UserDto> userDtos = new ArrayList<>();
        List<SportDto> sportDtos = new ArrayList<>();
        sportDtos.add(new SportDto(1L, "name", "description",  new WorkoutDto()));
        //when
        List<SportEntity> sportEntities = sportMapper.mapToSportEntityList(sportDtos);
        int size = sportEntities.size();
        //then
        assertEquals(1, size);
    }

}
