package com.everest.employeeportal.exceptions;

public class EmptyDataException extends  RuntimeException {
    public EmptyDataException()
    {
        super("Record not found");
    }
}
