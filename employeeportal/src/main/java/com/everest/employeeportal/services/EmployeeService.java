package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;

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
    public Page<Employee> findByName(String name, int pageNumber) {
        return employeeRepository.findAll(propertiesLike(name), PageRequest.of(pageNumber, 5));
    }

    private Specification<Employee> propertiesLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(criteriaBuilder
                        .like(root.get("firstName"), "%" + name + "%"), criteriaBuilder
                        .like(root.get("lastName"), "%" + name + "%"));
    }

    @Transactional(readOnly = true)
    public Page<Employee> sortByProperties(String name, String doj, int pageNumber) {

        if (name != null && doj != null) {
            String[] byName = name.split(":");
            String[] byDoj = doj.split(":");
            List<Sort.Order> orderList = List.of(Sort.Order.by(byName[0]).with(Sort.Direction.valueOf(byName[1].toUpperCase()))
                    .withProperty(byDoj[0]).with(Sort.Direction.valueOf(byDoj[1].toUpperCase())));
            return employeeRepository.findAll(PageRequest.of(pageNumber, 5, Sort.by(orderList)));
        } else if (doj != null) {
            String[] byDoj = doj.split(":");
            return employeeRepository.findAll(PageRequest.of(pageNumber, 5,
                    Sort.by(Sort.Direction.valueOf(byDoj[1].toUpperCase()), byDoj[0])));
        } else if (name != null) {
            String[] byName = name.split(":");
            return employeeRepository.findAll(PageRequest.of(pageNumber, 5,
                    Sort.by(Sort.Direction.valueOf(byName[1].toUpperCase()), byName[0])));
        } else return null;
    }
}

