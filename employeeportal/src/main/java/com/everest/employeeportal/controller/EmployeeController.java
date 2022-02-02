package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return null;
    }
    @GetMapping(value = "/name")
    public List<Employee> getEmployeesByName() {
        return null;
    }
    @GetMapping(value = "")
    public List<Employee> getEmployeesSortNameByDateOfJoin() {
        return null;
    }
    @GetMapping(value = "/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        return null;
    }
    @GetMapping(value = "")
    public List<Employee> createEmployee(@RequestBody Employee employee) {
        return null;
    }
    @GetMapping(value ="/{id}")
    public void deleteEmployee(@PathVariable("id") Long employeeId)
    {

    }

}
