package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Long> {

    @Override
    List<WorkoutEntity> findAll();

    @Override
    Optional<WorkoutEntity> findById(Long id);

    @Override
    <Workout2 extends WorkoutEntity> Workout2 save(Workout2 workout);

    @Override
    void deleteById(Long id);

    @Override
    void delete(WorkoutEntity workoutEntity);

    void findByHour(String hour);
    void findByDay(String day);
//    WorkoutEntity findByPay();


    @Query("select w from WorkoutEntity w " +
            "where lower(w.name) like lower(concat('%', :searchTerm, '%'))")
    List<WorkoutEntity> search(@Param("searchTerm") String filterText);
}
