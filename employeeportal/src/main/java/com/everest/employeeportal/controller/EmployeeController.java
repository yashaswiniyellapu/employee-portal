package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.repository.EmployeeRepository;
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

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
                                                   @PathVariable(name = "id") Long empId)
    {
        Employee EMPLOYEE = employeeService.updateEmployee(employee, empId);
        if(EMPLOYEE==null)
        {
            throw new EmployeeNotFoundException(empId);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }

}
