package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;

import com.everest.employeeportal.exceptions.EmployeeNotFoundException;

import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @PostMapping(value = "")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getAllEmployees(@PathVariable("id")
                                                        @Min(value= 1, message = "Id min value is one") Long id) {
        Employee employee = employeeService.fetchEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return ResponseEntity.ok().body(employee);
    }
}

