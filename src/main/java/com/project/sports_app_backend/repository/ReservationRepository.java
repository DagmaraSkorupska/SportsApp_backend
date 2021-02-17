package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.Reservation;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//@EnableJpaRepositories
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Override
    Optional<Reservation> findById(Long id);


//    Optional<Reservation> findByDate(Date date);
//
//
//    List<Reservation> findByUser(UserEntity user);

    @Override
    <Reservation2 extends Reservation> Reservation2 save(Reservation2 reservation);

    @Override
    void deleteById(Long id);

    @Override
    void delete(Reservation reservation);


}
