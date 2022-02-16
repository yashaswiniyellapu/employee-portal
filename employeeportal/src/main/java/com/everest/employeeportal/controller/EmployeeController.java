package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @GetMapping(value = "")
    public Page<Employee> getAllEmployees(@RequestParam(name="page")
                                                      @Min(value=0, message="The page indexing from zero")
                                                      int pageNumber) {
        return employeeService.fetchAllEmployees(pageNumber);
    }

}
