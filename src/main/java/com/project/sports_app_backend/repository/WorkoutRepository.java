package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Long> {
}
