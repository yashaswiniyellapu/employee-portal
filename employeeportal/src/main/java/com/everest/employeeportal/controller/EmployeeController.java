package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.entities.EmployeeResults;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @GetMapping(value = "")
    public EmployeeResults getAllEmployees(@RequestParam(name = "page", required = false, defaultValue = "1")
                                           @Min(value = 1, message = "The page indexing from zero")
                                                   int pageNumber) {
        Page<Employee> paginatedEmployees = employeeService.fetchAllEmployees(pageNumber);
        return new EmployeeResults(paginatedEmployees);
    }

}
