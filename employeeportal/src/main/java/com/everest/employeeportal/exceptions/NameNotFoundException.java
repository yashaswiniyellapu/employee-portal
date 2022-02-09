package com.everest.employeeportal.exceptions;

public class NameNotFoundException extends RuntimeException{

    public NameNotFoundException(String empName)
    {
        super("The name "+empName+" not found");
    }
}
