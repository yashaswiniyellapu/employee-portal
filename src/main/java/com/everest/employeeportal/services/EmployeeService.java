package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeAlreadyExistsException;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEverestEmailId(employee.getEverestEmailId())) {
            throw new EmployeeAlreadyExistsException(employee.getEverestEmailId());
        }
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findAllEmployees(int pageNumber) {

        return employeeRepository.findAll(PageRequest.of(pageNumber - 1, 10));
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Transactional(readOnly = true)
    public Page<Employee> findByName(String name, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return employeeRepository.findByName(name, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Employee> sortBy(String query, int pageNumber) {
        String[] property = query.split(",");
        return employeeRepository.findAll(PageRequest.
                of(pageNumber - 1, 10, Sort.by(Sort.Order.by(property[0])
                        .with(Sort.Direction.valueOf(property[1].toUpperCase())))));
    }

    public Employee updateEmployee(Employee employee, Long empId) {
        if (!employeeRepository.existsById(empId)) {
                throw new EmployeeNotFoundException(empId);
        }
        employee.setEmpId(empId);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

}

