package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.Reservation;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.UserType;
import com.project.sports_app_backend.domain.WorkoutEntity;
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
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        userEntityList.add(new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations));
        when(userRepository.findAll()).thenReturn(userEntityList);
        //when
        List<UserEntity> resultList = userService.getAllUsers();
        //then
        assertEquals(1, resultList.size());
        assertEquals("firstname", resultList.get(0).getFirstName());
        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserById(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserById(userEntity.getId()).orElse(new UserEntity());
        //then
        assertEquals(userEntity.getId(), result.getId());
        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserByFirstName(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(userRepository.findByFirstName("firstname")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByFirstName("firstname").orElse(new UserEntity());
        //then
        assertEquals("firstname", result.getFirstName());
        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserByLastName(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(userRepository.findByLastName("lastname")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByLastName("lastname").orElse(new UserEntity());
        //then
        assertEquals("lastname", result.getLastName());
        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserByEmail(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity(UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(userRepository.findByEmail("email@test.com")).thenReturn(Optional.of(userEntity));
        //when
        UserEntity result = userService.getUserByEmail("email@test.com").orElse(new UserEntity());
        //then
        assertEquals("email@test.com", result.getEmail());
        // cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser(){
        //given
        List<WorkoutEntity> workoutEntities = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        UserEntity userEntity = new UserEntity( UserType.USER, "firstname", "lastname", "email@test.com", "password", "desc", "132465798", workoutEntities, reservations);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        //when
        UserEntity result = userService.saveUser(userEntity);
        //then
        assertEquals("email@test.com", result.getEmail());
        //cleanUp
        userRepository.deleteAll();
    }
}
