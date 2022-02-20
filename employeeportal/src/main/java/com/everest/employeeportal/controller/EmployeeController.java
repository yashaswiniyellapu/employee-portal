package com.everest.employeeportal.controller;


import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.entities.EmployeeResults;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.exceptions.EmptyDataException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;
    @GetMapping(value = "")
    public EmployeeResults getAllEmployees(@RequestParam(name = "page", required = false, defaultValue = "1")
                                           @Min(value = 1, message = "The page indexing from one")
                                                   int pageNumber) {
        Page<Employee> paginatedEmployees = employeeService.fetchAllEmployees(pageNumber);
        return new EmployeeResults(paginatedEmployees);
    }
        @GetMapping(value = "/{id}")
        public ResponseEntity<Optional<Employee>> getAllEmployees (@PathVariable("id")
        @Min(value = 1, message = "Id min value is one") Long id){
            Optional<Employee> employee = employeeService.fetchEmployeeById(id);
            if (employee.isEmpty()) {
                throw new EmployeeNotFoundException(id);
            }
            return ResponseEntity.ok().body(employee);
        }
        @PostMapping(value = "")
        public ResponseEntity<Employee> createEmployee (@Valid @RequestBody Employee employee)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, Object>> deleteEmployee (@PathVariable(name = "id")
        @Min(value = 1, message = "The id must start from one")
                Long empId){
            try {
                employeeService.deleteEmployee(empId);
            } catch (Exception e) {
                throw new EmptyDataException(empId);
            }
            Map<String, Object> status = Map.of("timestamp", LocalDateTime.now(),
                    "status", "deleted successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(status);
        }
    }

