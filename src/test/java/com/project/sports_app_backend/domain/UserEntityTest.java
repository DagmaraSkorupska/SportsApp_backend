package com.project.sports_app_backend.domain;

import com.project.sports_app_backend.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.project.sports_app_backend.domain.UserType.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserEntityTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    public void saveNewUserTest() {
        //Given
        UserEntity user1 = new UserEntity(1L,USER, "user1", "abc", "test@mail.com", "password1", "desc", "1324679", null, null);
        userRepository.save(user1);

        //When
        long countUser = userRepository.count();

        //Then
        assertEquals(1, countUser);

        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void deleteUserTest(){
        //Given
        UserEntity user1 = new UserEntity(10L,USER, "user2", "abc2", "test@mail.com", "password1", "desc", "1324679", null,null);
        userRepository.save(user1);

        //When
        userRepository.delete(user1);
        long countUser = userRepository.count();

        //Then
        assertEquals(0, countUser);

        //cleanUp
        userRepository.deleteAll();
    }
}
