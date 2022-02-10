package com.everest.employeeportal.exceptions;

public class NameNotFoundException extends RuntimeException{

    public NameNotFoundException(String firstName, String lastName)
    {
        super("The name "+firstName+" "+lastName+" not found");
    }
}