package com.everest.employeeportal.exceptions;

public class NameNotFoundException extends RuntimeException{

    public NameNotFoundException(String name)
    {
        super("The name "+name+" not found");
    }
}