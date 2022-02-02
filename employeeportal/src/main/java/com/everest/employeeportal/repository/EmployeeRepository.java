package com.everest.employeeportal.repository;

import com.everest.employeeportal.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    public List<Employee> getAllEmployees() {
        return null;
    }
    public List<Employee> getEmployeesByName()
    {
        return null;
    }
    public List<Employee> getEmployeesSortNameByDateOfJoin()
    {
       return null;
    }
    public Employee createEmployee(Employee employee)
    {
        return null;
    }
    public Employee updateEmployee(Employee employee)
    {
        return null;
    }
    public void deleteEmployee(Long employeeId)
    {

    }

}
