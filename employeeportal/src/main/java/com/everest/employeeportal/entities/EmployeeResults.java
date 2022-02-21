package com.everest.employeeportal.entities;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class EmployeeResults {

    private List<Employee> data;
    private int pageSize;
    private int pageNumber;
    private boolean hasNext;
    private boolean hasPrevious;
    private int totalElements;



    public EmployeeResults(Page<Employee> paginatedEmployees)
    {

        setData(paginatedEmployees.getContent());
        setPageSize(paginatedEmployees.getSize());
        setPageNumber(paginatedEmployees.getNumber());
        setHasNext(paginatedEmployees.hasNext());
        setHasPrevious(paginatedEmployees.hasPrevious());
        setTotalElements(paginatedEmployees.getNumberOfElements());
    }

}
