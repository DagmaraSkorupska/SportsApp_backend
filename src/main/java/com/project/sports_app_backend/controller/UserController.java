package com.project.sports_app_backend.controller;

import com.project.sports_app_backend.domain.UserDto;
import com.project.sports_app_backend.mapper.UserMapper;
import com.project.sports_app_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("v1/user")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @PostMapping("/login")
//    @RequestMapping(method = )
//    public UserDto logIn(@RequestBody UserDto user){
//        return userMapping.mapToUserDto(userService.logIn(user.getEmail(), user.getPassword()));
//    }
//
//    @RequestMapping(method = POST, value = "/create", consumes = APPLICATION_JSON_VALUE)
//    public void createUser(@RequestBody UserDto userDto){
//        userService.saveUser(userMapper.mapToUserEntity(userDto));
//    }



}
