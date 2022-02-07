package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

}
