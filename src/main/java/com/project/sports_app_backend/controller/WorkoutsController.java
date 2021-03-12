package com.project.sports_app_backend.controller;

import com.project.sports_app_backend.controller.exception.WorkoutNotFoundException;
import com.project.sports_app_backend.domain.WorkoutDto;
import com.project.sports_app_backend.mapper.WorkoutMapper;
import com.project.sports_app_backend.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class WorkoutsController {

    @Autowired
    WorkoutMapper workoutMapper;

    @Autowired
    WorkoutService workoutService;

    @RequestMapping(method = RequestMethod.GET, value = "/workouts")
    public List<WorkoutDto> getAllWorkouts(){
        return workoutMapper.mapToWorkoutDtoList(workoutService.getAllWorkout());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/workouts/{workoutId}")
    public WorkoutDto getWorkout(@PathVariable Long workoutId) throws WorkoutNotFoundException {
        return workoutMapper.mapToWorkoutDto(workoutService.getWorkoutById(workoutId).orElseThrow(() -> new WorkoutNotFoundException(workoutId)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/workouts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createWorkout(@RequestBody WorkoutDto workoutDto){
        workoutService.saveWorkout(workoutMapper.mapToWorkoutEntity(workoutDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/workouts")
    public WorkoutDto updateWorkout(@RequestBody WorkoutDto workoutDto){
        return workoutMapper.mapToWorkoutDto(workoutService.saveWorkout(workoutMapper.mapToWorkoutEntity(workoutDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/workouts/{workoutId}")
    public void deleteWorkout(@PathVariable Long workoutId){
        workoutService.deleteById(workoutId);
    }
}
