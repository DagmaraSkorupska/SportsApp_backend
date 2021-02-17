package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public List<WorkoutEntity> getAllWorkout(){
        return workoutRepository.findAll();
    }

    public Optional<WorkoutEntity> getWorkoutById(Long id){
        return workoutRepository.findById(id);
    }

    public WorkoutEntity saveWorkout(WorkoutEntity workoutEntity){
        return workoutRepository.save(workoutEntity);
    }

    public void deleteById(Long id){
        workoutRepository.deleteById(id);
    }
}
