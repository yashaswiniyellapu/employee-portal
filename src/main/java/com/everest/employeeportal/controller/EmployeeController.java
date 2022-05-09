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
import javax.validation.constraints.NotBlank;
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
    public EmployeeResults getAllEmployees(@RequestParam(name = "sort", required = false) String query,
                                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                           @Min(value = 1, message = "Page indexing from one")
                                                   int pageNumber) {

        if (query != null) {
            Page<Employee> paginatedEmployees = employeeService.sortBy(query, pageNumber);
            return new EmployeeResults(paginatedEmployees);
        }
        return new EmployeeResults(employeeService.findAllEmployees(pageNumber));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeByID(@PathVariable("id")
                                                              @Min(value = 1, message = "Id min value is one") Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return ResponseEntity.ok().body(employee);
    }
    @GetMapping(value = "/search")
    public EmployeeResults getEmployeeBySearch(@RequestParam(name = "query")
                                               @NotBlank(message = "name must not blank") String name,
                                               @RequestParam(name = "page", required = false, defaultValue = "1")
                                               @Min(value = 1, message = "Indexing start from one") int pageNumber) {

        Page<Employee> paginatedEmployees = employeeService.findByName(name, pageNumber);
        return new EmployeeResults(paginatedEmployees);
    }

    @PostMapping(value = "")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee,
                                                   @PathVariable("id") @Min(value = 1, message = "min value of id 1")
                                                           Long empId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee, empId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable(name = "id")
                                                              @Min(value = 1, message = "The id must start from one")
                                                                      Long empId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.deleteEmployee(empId));
    }
}


