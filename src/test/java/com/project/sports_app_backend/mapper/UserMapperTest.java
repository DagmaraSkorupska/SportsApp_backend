package com.project.sports_app_backend.mapper;

import com.project.sports_app_backend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapToUserEntity(){
        //given
        List<SportDto> sportDto = new ArrayList<>();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        UserDto userDto = new UserDto(1L, UserType.USER, "name", "lastName", "email", "password", "desc", "123456798", sportDto, reservationDtos);
        //when
        UserEntity userEntity = userMapper.mapToUserEntity(userDto);
        String name = userEntity.getFirstName();
        String email = userEntity.getEmail();
        //then
        assertEquals("name", name);
        assertEquals("email", email);
    }

    @Test
    public void testMapToUserDto(){
        //given
        List<SportEntity> sport = new ArrayList<>();
        List<Reservation> reservationEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity(1L, UserType.USER, "name", "lastName", "email", "password", "desc", "123456798", sport, reservationEntities);
        //when
        UserDto userDto = userMapper.mapToUserDto(userEntity);
        String name = userDto.getFirstName();
        //then
        assertEquals("name", name);
    }

    @Test
    public void testMapToUserDtoList(){
        //given
        List<SportEntity> sport = new ArrayList<>();
        List<Reservation> reservationEntities = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity(9L,UserType.USER, "name", "lastName", "email", "password", "desc", "123456798", sport, reservationEntities));
        //when
        List<UserDto> userDtos = userMapper.mapToUserDtoList(userEntities);
        int size = userDtos.size();
        String name = userDtos.get(0).getFirstName();
        //then
        assertEquals(1, size);
        assertEquals("name", name);
    }

    @Test
    public void testMapUserEntityList(){
        //given
        List<SportDto> sportDto = new ArrayList<>();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, UserType.USER, "name", "lastName", "email", "password", "desc", "123456798", sportDto, reservationDtos));
        //when
        List<UserEntity> userEntities = userMapper.mapToUserEntityList(userDtos);
        int size = userEntities.size();
        String pass = userEntities.get(0).getPassword();
        //then
        assertEquals(1, size);
        assertEquals("password", pass);


    }
}






















