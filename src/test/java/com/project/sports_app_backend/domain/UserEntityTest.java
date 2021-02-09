package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.project.sports_app_backend.domain.UserType.USER;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveNewUserTest() {
        //Given
        UserEntity user1 = new UserEntity(USER, "user1", "abc", "test@mail.com", "password1", "desc", "1324679", null);
        userRepository.save(user1);

        //When
        long countUser = userRepository.count();

        //Then
        Assert.assertEquals(1, countUser);

        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void deleteUserTest(){
        //Given
        UserEntity user1 = new UserEntity(USER, "user1", "abc", "test@mail.com", "password1", "desc", "1324679", null);
        userRepository.save(user1);

        //When
        userRepository.delete(user1);
        long countUser = userRepository.count();

        //Then
        Assert.assertEquals(0, countUser);

        //cleanUp
        userRepository.deleteAll();
    }
}
