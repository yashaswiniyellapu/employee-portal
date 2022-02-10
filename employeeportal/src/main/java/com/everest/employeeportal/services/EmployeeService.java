package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }


    public List<Employee> findByName(String firstName, String lastName) {
        if(firstName!=null)
        {
            return employeeRepository.findByFirstNameIgnoreCase(firstName);
        }
        else {
            return employeeRepository.findByLastNameIgnoreCase(lastName);}
    }
}
