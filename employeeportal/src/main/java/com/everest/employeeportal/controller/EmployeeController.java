package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Employee;
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

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @GetMapping(value = "/name")
    public Page<Employee> getEmployeeByName(@RequestParam(name = "name")
                                            @NotBlank(message = "name must not blank") String name,
                                            @RequestParam(name = "page")
                                            @Min(value = 0, message = "Indexing start from zero") int pageNumber) {

        Page<Employee> employee = employeeService.findByName(name, pageNumber);
        if (employee.isEmpty()) {
            throw new NameNotFoundException(name);
        }
        return employee;
    }


}
