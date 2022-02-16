package com.everest.employeeportal.exceptions;

public class EmptyDataException extends  RuntimeException {
    public EmptyDataException(Long empId)
    {
        super("Record not with id "+empId+" found");
    }
}
