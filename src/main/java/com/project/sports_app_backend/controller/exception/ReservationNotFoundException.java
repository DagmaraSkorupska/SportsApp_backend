package com.project.sports_app_backend.controller.exception;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(Long id) {
        super("Could not find reservation " + id);
    }
}
