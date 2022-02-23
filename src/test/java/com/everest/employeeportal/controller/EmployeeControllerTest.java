package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Address;
import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Employee> employeesList;

    @BeforeEach
    void setup() {
        employeesList = new ArrayList<>();
        employeesList.add(new Employee(1L, "yashu", null, "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 515001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 515002, "India")));
        employeesList.add(new Employee(2L, "berlin", null, "berlin@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine1", "ATPPreLine2", "guljarPet", "Andhra", 515006, "India"), new Address(null, "ATPPerLine1", "ATPPerLine2", "guljarPet", "AndhraPradesh", 515006, "India")));
        employeesList.add(new Employee(3L, "jessy", null, "jessy@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine4", "ATPPreLine5", "guljarPet", "Andhra", 515005, "India"), new Address(null, "ATPPerLine4", "ATPPerLine5", "guljarPet", "AndhraPradesh", 515005, "India")));

    }


    @Test
    void shouldFindAllEmployees() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        when(employeeService.findAllEmployees(1)).thenReturn(new PageImpl<>(employeesList, pageable, 3));
        this.mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(employeesList.size())));
    }


}