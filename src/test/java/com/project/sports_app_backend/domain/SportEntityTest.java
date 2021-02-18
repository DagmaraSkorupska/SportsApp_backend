package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.SportRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SportEntityTest {
    @Autowired
    private SportRepository sportRepository;

    @BeforeEach
    public void cleanUp(){
        sportRepository.deleteAll();
    }

    @Test
    public void testSaveNewSport(){
        //Given
        SportEntity sport1 = new SportEntity(1L,"Swim", "desc",null, null);
        sportRepository.save(sport1);

        //When
        long countSports = sportRepository.count();
        String nameSports = sport1.getName();

        //Then
        assertEquals(1, countSports);
        assertEquals("Swim", nameSports);

        //CleanUp
        sportRepository.deleteAll();
    }

    @Test
    public void testDeleteSport(){
        //Given
        SportEntity sport1 = new SportEntity(1L, "Swim2", "desc2", null, null);
        SportEntity save = sportRepository.save(sport1);

        //When
        sportRepository.delete(save);
        long countSports = sportRepository.count();

        //Then
        assertEquals(0, countSports);

        //CleanUp
        sportRepository.deleteAll();

    }
}
