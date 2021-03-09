package com.project.sports_app_backend.controller.exception;

public class UserNotEmailException extends RuntimeException {

    public UserNotEmailException(String email) {
        super("Could not find user" + email);
    }
}
