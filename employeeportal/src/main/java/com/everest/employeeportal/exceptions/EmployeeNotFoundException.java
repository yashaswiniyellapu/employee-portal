package com.everest.employeeportal.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long empId)
    {
        super("The employee with Id "+empId+" not found");
    }
}
