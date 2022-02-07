package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee fetchEmployeeById(Long empId) {

        Employee EMPLOYEE = employeeRepository.findById(empId).orElse(null);
            return EMPLOYEE;
        }
    }
