package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.Reservation;
import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.UserType;
import com.project.sports_app_backend.repository.UserRepository;
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
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testGetAllUsers(){
        //given
        List<UserEntity> userEntityList = new ArrayList<>();
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        userEntityList.add(new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations));
        when(userRepository.findAll()).thenReturn(userEntityList);
        //when
        List<UserEntity> resultList = userService.getAllUsers();
        //then
        assertEquals(1, resultList.size());
        assertEquals("firstname", resultList.get(0).getFirstName());
    }

    @Test
    public void testGetUserById(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
        when(userRepository.findById(12L)).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserById(12L).orElse(new UserEntity());
        //then
        assertEquals(12L, result.getId());
    }

    @Test
    public void testGetUserByFirstName(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
        when(userRepository.findByFirstName("firstname")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByFirstName("firstname").orElse(new UserEntity());
        //then
        assertEquals("firstname", result.getFirstName());
    }

    @Test
    public void testGetUserByLastName(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
        when(userRepository.findByLastName("lastname")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByLastName("lastname").orElse(new UserEntity());
        //then
        assertEquals("lastname", result.getLastName());
    }

    @Test
    public void testGetUserByEmail(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
        when(userRepository.findByEmail("email@test.com")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByEmail("email@test.com").orElse(new UserEntity());
        //then
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    public void testSaveUser(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(12L, UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", sportEntities, reservations);
        when(userRepository.saveUser(userEntity)).thenReturn(userEntity);
        //when
        UserEntity result = userService.saveUser(userEntity);
        //then
        assertEquals("email@test.com", result.getEmail());
    }
}
