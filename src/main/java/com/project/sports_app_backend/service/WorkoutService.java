package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

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

    @PostConstruct
//    @EventListener(ApplicationReadyEvent.class)
    public void addWorkout(){
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutEntity workoutEntity1 = new WorkoutEntity("Swim", "Learn croul", 60, 100, "ul.Wrocławska 2", sportEntities );
        WorkoutEntity workoutEntity2 = new WorkoutEntity("Run", "Learn run", 60, 50, "ul.Kozia 32", null );
        WorkoutEntity workoutEntity3 = new WorkoutEntity("Gym", "Learn pull up", 60, 80, "ul.Ruslka 5", null );
       workoutRepository.save(workoutEntity1);
       workoutRepository.save(workoutEntity2);
       workoutRepository.save(workoutEntity3);
    }
}
