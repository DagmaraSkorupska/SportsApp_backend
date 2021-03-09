package com.project.sports_app_backend.controller.exception;

public class SportNotFoundException extends RuntimeException {

    public SportNotFoundException(Long id) {
        super("Could not find sport " + id);
    }
}
