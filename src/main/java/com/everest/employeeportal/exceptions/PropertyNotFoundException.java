package com.everest.employeeportal.exceptions;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException()
    {
        super("Only Name and dateOfJoin fields are allowed to sort");
    }
}
