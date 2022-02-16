package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Employee> findByName(String name) {

        return employeeRepository.findAll(propertiesLike(name));
    }

    private Specification<Employee> propertiesLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder
                        .like(root.get("firstName"), "%" + name + "%"), criteriaBuilder
                        .like(root.get("lastName"), "%" + name + "%"));
    }
}

