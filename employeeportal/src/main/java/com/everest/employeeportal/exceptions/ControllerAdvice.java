package com.everest.employeeportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> EmployeeNotFoundException(Exception ex)
    {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
