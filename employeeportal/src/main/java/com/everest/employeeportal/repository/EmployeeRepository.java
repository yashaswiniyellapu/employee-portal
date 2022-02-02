package com.everest.employeeportal.repository;

import com.everest.employeeportal.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeRepository {
    private final AtomicLong NEXT_ID = new AtomicLong(0);
    private final HashMap<Long, Employee> EMPLOYEES = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(EMPLOYEES.values());
    }

    public List<Employee> getEmployeesByName() {
        return null;
    }

    public List<Employee> getEmployeesSortNameByDateOfJoin() {
        return null;
    }

    public Employee createEmployee(Employee employee) {
        employee.setEmpId(NEXT_ID.getAndIncrement());
        EMPLOYEES.put(employee.getEmpId(), employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee, Long emp_id) {
        EMPLOYEES.put(emp_id, employee);
        return employee;
    }

    public void deleteEmployee(Long employeeId) {
        EMPLOYEES.remove(employeeId);
    }

}
