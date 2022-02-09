package com.everest.employeeportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

   @ExceptionHandler(NameNotFoundException.class)
   public ResponseEntity<Object> employeeNameNotFound(Exception e, WebRequest r)
   {
       Map<String,Object> body = new HashMap<>();
       body.put("timestamp", LocalDateTime.now());
       body.put("message",e.getMessage());
       return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
   }
}
