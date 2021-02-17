package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
}
