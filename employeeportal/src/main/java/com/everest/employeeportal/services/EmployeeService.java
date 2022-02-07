package com.everest.employeeportal.services;

import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);

    }
}
