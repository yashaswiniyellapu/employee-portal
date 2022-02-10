package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.NameNotFoundException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/name")
    public List<Employee> getEmployeeByName(@RequestParam(required = false)String firstName,
                                            @RequestParam(required = false)String lastName) {

       List<Employee> employee= employeeService.findByName(firstName,lastName);
       if(employee.isEmpty())
       {
           throw new NameNotFoundException(firstName,lastName);
       }
        return employee;
    }

}
