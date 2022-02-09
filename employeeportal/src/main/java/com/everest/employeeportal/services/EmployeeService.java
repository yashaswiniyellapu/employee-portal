package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }


    public Employee findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
}
