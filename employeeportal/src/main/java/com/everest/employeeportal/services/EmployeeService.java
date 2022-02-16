package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee updateEmployee(Employee employee, Long empId) {
        if (!employeeRepository.existsById(empId)) {
            return null;
        }
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }
}
