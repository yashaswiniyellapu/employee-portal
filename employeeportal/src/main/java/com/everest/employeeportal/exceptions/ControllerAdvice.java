
package com.everest.employeeportal.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<Object> nameNotFoundException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<Object> propertyNotFoundNotFoundException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
      

      return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);

    }


  


    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<Object> emptyDataException(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

  @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Object> employeeAlreadyExists(Exception ex,

                                                               WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST)
        body.put("client",request.getDescription(true));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
       

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex,
                HttpHeaders headers, HttpStatus status,
                WebRequest request){
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", ex.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<Object> constraintViolationException (ConstraintViolationException ex,
                WebRequest request){
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

    }



