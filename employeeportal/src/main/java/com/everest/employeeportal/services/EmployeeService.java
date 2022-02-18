package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return employeeRepository.findByName(name, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Employee> sortBy(String query, int pageNumber) {
        String[] property = query.split(",");
        if (property[1].isEmpty()) {
            property[1] = "asc";
        }
        return employeeRepository.findAll(PageRequest.
                of(pageNumber - 1, 10, Sort.by(Sort.Order.by(property[0])
                        .with(Sort.Direction.valueOf(property[1].toUpperCase())))));
    }
}

