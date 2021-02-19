package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.Reservation;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.repository.ReservationRepository;
import com.project.sports_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id){
        return reservationRepository.findById(id);
    }

    public Optional<Reservation> getReservationByDate(LocalDateTime date){
        return reservationRepository.findByDate(date);
    }

    public List<Reservation> getReservationByUserLogin(String login){
        UserEntity user = userRepository.findByEmail(login).orElse(new UserEntity());
        List<Reservation> reservations = new ArrayList<>();
        if(user.getEmail() !=null){
            reservations = reservationRepository.findByUserEntity(user);
        }
        return reservations;
    }

    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public void deleteById(Long id){
        reservationRepository.deleteById(id);
    }
}
