package com.project.sports_app_backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SportNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String sportNotFoundHandler(SportNotFoundException ex){
        return ex.getMessage();
    }
}
