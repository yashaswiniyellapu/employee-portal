package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeAlreadyExistsException;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Optional<Employee> fetchEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())) {
            throw new EmployeeAlreadyExistsException(employee.getEverestEmailId());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

}
