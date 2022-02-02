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

        return employeeRepository.getAllEmployees();
    }

    @GetMapping(value = "/name")
    public List<Employee> getEmployeesByName() {
        return employeeRepository.getEmployeesByName();
    }

    @GetMapping(value = "/nameByDate")
    public List<Employee> getEmployeesSortNameByDateOfJoin() {

        return employeeRepository.getEmployeesSortNameByDateOfJoin();
    }

    @PutMapping(value = "/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {

        return employeeRepository.updateEmployee(employee);
    }

    @PostMapping(value = "")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.createEmployee(employee);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

}
