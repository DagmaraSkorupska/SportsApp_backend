package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    public Optional<UserEntity> getUserByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }


}
