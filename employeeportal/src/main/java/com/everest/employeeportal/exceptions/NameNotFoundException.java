package com.everest.employeeportal.exceptions;

public class NameNotFoundException extends RuntimeException{

    public NameNotFoundException(String firstName)
    {
        super("The name "+firstName+" not found");
    }
}