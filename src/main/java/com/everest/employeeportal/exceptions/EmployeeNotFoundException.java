package com.everest.employeeportal.exceptions;


public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long empId) {
        super("Employee with id " + empId + " not found");
    }
}
