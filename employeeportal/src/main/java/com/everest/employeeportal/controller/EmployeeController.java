package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long empId) {
        Employee updatedEmployee = employeeService.updateEmployee(employee, empId);
        if (updatedEmployee == null) {
            throw new EmployeeNotFoundException(empId);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.updateEmployee(employee, empId));
    }


}
