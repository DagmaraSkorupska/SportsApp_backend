package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.SportRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportEntityTest {
    @Autowired
    private SportRepository sportRepository;

    @Test
    public void saveNewSport(){
        //Given
        SportEntity sport1 = new SportEntity("Swim", "desc");
        sportRepository.save(sport1);

        //When
        long countSports = sportRepository.count();
        String nameSports = sport1.getName();

        //Then
        Assert.assertEquals(1, countSports);
        Assert.assertEquals("Swim", nameSports);

        //CleanUp
        sportRepository.deleteAll();
    }

    @Test
    public void deleteSport(){
        //Given
        SportEntity sport1 = new SportEntity("Swim", "desc");
        sportRepository.save(sport1);

        //When
        sportRepository.delete(sport1);
        long countSports = sportRepository.count();

        //Then
        Assert.assertEquals(0, countSports);

        //CleanUp
        sportRepository.deleteAll();

    }
}
