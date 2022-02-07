package com.everest.employeeportal.controller;

import com.everest.employeeportal.exceptions.EmptyDataException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") Long empId) {
        try {
            employeeService.deleteEmployee(empId);
        } catch (Exception e) {
            throw new EmptyDataException();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("deleted successfully");
    }

}
