package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeAlreadyExistsException;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Optional<Employee> findEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAllEmployees(int pageNumber) {
        return employeeRepository.findAll(PageRequest.of(pageNumber - 1, 10));
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())) {
            throw new EmployeeAlreadyExistsException(employee.getEverestEmailId());
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long empId) {
        if (!employeeRepository.existsById(empId)) {
            return null;
        }
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

}


