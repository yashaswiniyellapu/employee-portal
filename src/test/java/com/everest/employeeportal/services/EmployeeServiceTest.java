package com.everest.employeeportal.services;

import com.everest.employeeportal.entities.Address;
import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepo;
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        employeeRepo = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepo);
    }

    @Test
    void shouldCreateEmployeeSuccessfully() {
        Employee employee = new Employee(null, "yashu", null,
                "yashu@Everest", null, null, null,
                "trainee", 0, "good",
                new Address(null, "ATPPreLine1", "ATPPreLine2",
                        "guljarPet", "Andhra", 515001, "India"),
                new Address(null, "ATPPerLine1", "ATPPerLine2",
                        "guljarPet", "AndhraPradesh", 515002, "India"));
        Mockito.when(employeeRepo.existsByEverestEmailId("yashu@Everest")).thenReturn(false);
        Mockito.when(employeeRepo.save(employee)).then(invocation -> invocation.getArgument(0));
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertThat(savedEmployee).isNotNull();
        verify(employeeRepo).save(any(Employee.class));
    }
}