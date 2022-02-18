package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.entities.EmployeeResults;
import com.everest.employeeportal.exceptions.NameNotFoundException;
import com.everest.employeeportal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    public final EmployeeService employeeService;

//       @GetMapping(value = "")
//    public List<Employee> getAllEmployees() {
//        return employeeService.findAllEmployees();
//    }

    @GetMapping(value = "/search")
    public EmployeeResults getEmployeeBySearch(@RequestParam(name = "query")
                                               @NotBlank(message = "name must not blank") String name,
                                               @RequestParam(name = "page", required = false, defaultValue = "1")
                                               @Min(value = 1, message = "Indexing start from one") int pageNumber) {

        Page<Employee> paginatedEmployees = employeeService.findByName(name, pageNumber);
        if (paginatedEmployees.isEmpty()) {
            throw new NameNotFoundException(name);
        }
        return new EmployeeResults(paginatedEmployees);
    }

    @GetMapping(value = "")
    public EmployeeResults sortEmployeeByNameAndDateOfJoin(@RequestParam(name = "sort", required = false) String query,
                                                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                                           @Min(value = 1, message = "Page indexing from one")
                                                                   int pageNumber) {

        if (query != null) {
            Page<Employee> paginatedEmployees = employeeService.sortBy(query, pageNumber);
            return new EmployeeResults(paginatedEmployees);
        }
        return new EmployeeResults(employeeService.findAllEmployees(pageNumber));
    }


}
