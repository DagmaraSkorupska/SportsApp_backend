package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.SportRepository;
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
public class SportServiceTest {

    @InjectMocks
    private SportService sportService;

    @Mock
    SportRepository sportRepository;

    @Test
    public void testGetAllSport(){
        //given
        List<SportEntity> sportEntities = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        sportEntities.add(new SportEntity(1234L,"name", "decs", userEntities, new WorkoutEntity()));
        sportEntities.add(new SportEntity(4567L,"name2", "decs2", userEntities, new WorkoutEntity()));
        when(sportRepository.findAll()).thenReturn(sportEntities);
        //when
        List<SportEntity> resultList = sportService.getAllSports();
        //then
        assertEquals(2, resultList.size());
        //clean up
        sportRepository.deleteAll();
    }

    @Test
    public void testGetSport(){
        //given
        List<UserEntity> userEntities = new ArrayList<>();
        SportEntity sportEntity = (new SportEntity(1234L,"name", "decs", userEntities, new WorkoutEntity()));
        when(sportRepository.findById(1234L)).thenReturn(Optional.of(sportEntity));
        //when
        SportEntity result = sportService.getSport(1234L).orElse(new SportEntity());
        //then
        assertEquals(1234L, result.getId());
        sportRepository.deleteAll();
    }

    @Test
    public void testSaveSport(){
        //given
        List<UserEntity> userEntities = new ArrayList<>();
        SportEntity sportEntity = (new SportEntity(1234L,"name", "decs", userEntities, new WorkoutEntity()));
        when(sportRepository.save(sportEntity)).thenReturn(sportEntity);
        //when
        SportEntity result = sportService.saveSport(sportEntity);
        //then
        assertEquals("name", result.getName());
        assertEquals("decs", result.getDescription());
        sportRepository.deleteAll();
    }
}
