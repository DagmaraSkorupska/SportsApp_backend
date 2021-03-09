package com.project.sports_app_backend.controller.exception;

public class WorkoutNotFoundException extends RuntimeException{

    public WorkoutNotFoundException(Long id ) {
        super("Could not find workout " + id);
    }
}
