package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Address;
import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeAlreadyExistsException;
import com.everest.employeeportal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepo;
    private EmployeeService employeeService;
    private Employee employee;

    @BeforeEach
    void setup() {
        employeeRepo = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepo);
        employee=new Employee(null, "yashu", null,
                "yashu@Everest", null, null, null,
                "trainee", 0, "good",
                new Address(null, "ATPPreLine1", "ATPPreLine2",
                        "guljarPet", "Andhra", 515001, "India"),
                new Address(null, "ATPPerLine1", "ATPPerLine2",
                        "guljarPet", "AndhraPradesh", 515002, "India"));
    }

    @Test
    void shouldCreateEmployeeSuccessfully() {
        //arranging data
        when(employeeRepo.existsByEverestEmailId("yashu@Everest")).thenReturn(false);
        when(employeeRepo.save(employee)).then(invocation -> invocation.getArgument(0));
        //operations on data
        Employee savedEmployee = employeeService.createEmployee(employee);
        //asserting on service layer
        assertThat(savedEmployee).isNotNull();
        verify(employeeRepo).save(any(Employee.class));
    }
    @Test
    void shouldThrowErrorWhenSavingEmployeeWithExistingEverestEmailId() {
        when(employeeRepo.existsByEverestEmailId("yashu@Everest")).thenReturn(true);
        assertThrows(EmployeeAlreadyExistsException.class,()->{
            employeeService.createEmployee(employee);});
        verify(employeeRepo,never()).save(any(Employee.class));

    }
    @Test
    void shouldGetAllPaginatedEmployees()
    {
        //arranging data
        List<Employee> employeeData= List.of(employee);
        Pageable pageable = PageRequest.of(0,10);
        Page<Employee> paginatedEmployees = new PageImpl<>(employeeData,pageable,1);
        when(employeeRepo.findAll(pageable)).thenReturn(paginatedEmployees);
        //operations on data
        Page<Employee> employeeList= employeeService.findAllEmployees(1);
        //asserting data
        assertThat(employeeList.getTotalElements()).isEqualTo(1);
        verify(employeeRepo).findAll(any(Pageable.class));
    }


}