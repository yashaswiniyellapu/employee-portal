package com.everest.employeeportal.repository;

import com.everest.employeeportal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value="select * from employee_details ed where ed.first_name like %?1%", nativeQuery = true)
    List<Employee> findByFirstNameIgnoreCase(String firstName);
    @Query(value="select * from employee_details ed where ed.last_name like %?1%", nativeQuery = true)
    List<Employee> findByLastNameIgnoreCase(String lastName);

//    @Query(value="select * from employee_details ed where (?1 is null or ed.first_name = ?1)"
//            +" and (?2 is null or ed.last_name = ?2)", nativeQuery =true)
//    List<Employee> searchByFirstNameOrLastName(String firstName, String lastName);
}