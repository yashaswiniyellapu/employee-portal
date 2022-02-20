package com.everest.employeeportal.exceptions;


public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String everestEmailId) {
        super("The employee with everestMailId " + everestEmailId + " already exists");
    }
}
