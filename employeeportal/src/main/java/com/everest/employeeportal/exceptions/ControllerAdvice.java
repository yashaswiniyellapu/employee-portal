package com.everest.employeeportal.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<Object> emptyDataException(Exception e) {
        return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}