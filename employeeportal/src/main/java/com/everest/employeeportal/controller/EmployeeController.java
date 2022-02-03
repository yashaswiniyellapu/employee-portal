package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {

        return employeeRepository.getAllEmployees();
    }

    @GetMapping(value = "/name")
    public ResponseEntity<Employee> getEmployeesByName() {
        final Employee employee = employeeRepository.getEmployeeByName();
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(employee);
        }
    }

    @GetMapping(value = "/nameByDate")
    public List<Employee> getEmployeesSortNameByDateOfJoin() {

        return employeeRepository.getEmployeesSortNameByDateOfJoin();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRepository.updateEmployee(employee, id));
    }

    @PostMapping(value = "")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.createEmployee(employee));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeRepository.deleteEmployee(employeeId);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted record") ;
    }

}
