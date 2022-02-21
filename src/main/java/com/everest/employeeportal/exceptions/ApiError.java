package com.everest.employeeportal.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;
}
