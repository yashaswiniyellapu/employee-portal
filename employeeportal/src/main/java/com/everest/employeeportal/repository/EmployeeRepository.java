package com.everest.employeeportal.repository;

import com.everest.employeeportal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
