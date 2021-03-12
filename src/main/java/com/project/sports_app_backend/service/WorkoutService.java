package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.SportRepository;
import com.project.sports_app_backend.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;
    private SportRepository sportRepository;

    public WorkoutService(WorkoutRepository workoutRepository, SportRepository sportRepository) {
        this.workoutRepository = workoutRepository;
        this.sportRepository = sportRepository;
    }

    public List<WorkoutEntity> getAllWorkout(){
        return workoutRepository.findAll();
    }

    public List<WorkoutEntity> getAllWorkout(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return workoutRepository.findAll();
        } else {
            return workoutRepository.search(filterText);
        }
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
    public void addSport(){
        SportEntity sportEntity1 = new SportEntity("Swim", "Learning swim in the pool", null, null);
        SportEntity sportEntity2 = new SportEntity("Run", "Learning to run marathons", null, null);
        SportEntity sportEntity3 = new SportEntity("Gym", "Gym classes", null, null);
        sportRepository.save(sportEntity1);
        sportRepository.save(sportEntity2);
        sportRepository.save(sportEntity3);

        WorkoutEntity workoutEntity1 = new WorkoutEntity("Run in the forest", "Learn run", 60, 50, "ul.Kozia 32", null);
        WorkoutEntity workoutEntity2 = new WorkoutEntity("Swim preper to triathlon", "Learn croul", 60, 100, "ul.Wrocławska 2", null);
        WorkoutEntity workoutEntity3 = new WorkoutEntity("Pull up", "Learn pull up", 60, 80, "ul.Ruslka 5", null);
        workoutRepository.save(workoutEntity1);
        workoutRepository.save(workoutEntity2);
        workoutRepository.save(workoutEntity3);
   }
}
