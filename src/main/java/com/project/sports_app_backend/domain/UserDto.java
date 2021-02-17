package com.project.sports_app_backend.domain;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private UserType type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String description;
    private String phone;
    private  List<SportDto> sportId = new ArrayList<>();
    private  List<ReservationDto> reservationDtos = new ArrayList<>() ;
}