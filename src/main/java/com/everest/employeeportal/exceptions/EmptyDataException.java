package com.everest.employeeportal.exceptions;

public class EmptyDataException extends  RuntimeException {
    public EmptyDataException(Long empId)
    {
        super("Record with employee id "+empId+" not found");
    }
}
