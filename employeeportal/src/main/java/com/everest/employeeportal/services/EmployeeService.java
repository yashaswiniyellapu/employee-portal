package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private AtomicLong nextValue= new AtomicLong(0);
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        employee.setEmpId(nextValue.getAndIncrement());
       return employeeRepository.save(employee);
    }
}
