package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee,
                                                   @PathVariable("id") @Min(value = 1, message = "min value of id 1")
                                                           Long empId) {
        Employee updatedEmployee = employeeService.updateEmployee(employee, empId);
        if (updatedEmployee == null) {
            throw new EmployeeNotFoundException(empId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee, empId));
    }


}
