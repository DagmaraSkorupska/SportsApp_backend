package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity logIn(String email, String password){
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            UserEntity loggingUser = user.get();
            if(loggingUser.getEmail().equals(email) && loggingUser.getPassword().equals(password)){
                return loggingUser;
            }
            return new UserEntity();
        }
        return user.orElse(new UserEntity());
    }

    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUser(Long id){
        return userRepository.findById(id);
    }
}
