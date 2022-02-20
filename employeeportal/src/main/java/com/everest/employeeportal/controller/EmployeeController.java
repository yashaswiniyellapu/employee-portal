package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.EmptyDataException;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;

import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable(name = "id")
                                                 @Min(value = 1, message = "The id must start from one")
                                                         Long empId) {
        try {
            employeeService.deleteEmployee(empId);
        } catch (Exception e) {
            throw new EmptyDataException(empId);
        }
        Map<String,Object> status = Map.of("timestamp", LocalDateTime.now(),
                "status","deleted successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(status);
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

