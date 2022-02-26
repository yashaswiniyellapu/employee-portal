package com.everest.employeeportal.controller;

import com.everest.employeeportal.entities.Address;
import com.everest.employeeportal.entities.Employee;
import com.everest.employeeportal.exceptions.EmployeeNotFoundException;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$.data.size()", is(employeesList.size())))
                .andExpect(jsonPath("$.currentPageNumber", is(0)))
                .andExpect(jsonPath("$.totalElements", is(employeesList.size())))
                .andExpect(jsonPath("$.pageSize", is(10)));
    }

    @Test
    void shouldFindEmployeeById() throws Exception {
        when(employeeService.findEmployeeById(1L))
                .thenReturn(Optional.of(employeesList.get(0)));
        mockMvc.perform(get("/api/employees/{id}", employeesList.get(0).getEmpId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId", is(1)))
                .andExpect(jsonPath("$.everestEmailId", is(employeesList.get(0).getEverestEmailId())))
                .andExpect(jsonPath("$.size()", is(12)));
    }

    @Test
    void shouldThrowNotFoundErrorWhenFetchingNonExistingUser() throws Exception {
        Long employeeId = 1L;
        mockMvc.perform(get("/api/employees/{id}", employeeId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(is("Employee with id " + employeeId + " not found"))));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).then(invocation -> invocation.getArgument(0));
        Employee newEmployee =
                new Employee(1L, "yashu", "yellapu", "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 515001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 515002, "India"));
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.everestEmailId", is(newEmployee.getEverestEmailId())));
    }

    //    @Test
//    void shouldThrowBadRequestIfEmployeeAlreadyExists() throws Exception {
//        when(employeeService.createEmployee(any(Employee.class))).thenThrow(EmployeeAlreadyExistsException.class);
//        Employee newEmployee =
//                new Employee(1L, "yashu", "yellapu", "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 515001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 515002, "India"));
//        mockMvc.perform(post("/api/employees")
//                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newEmployee)))
//                .andExpect(status().isBadRequest());
//    }
//    @Test
//    void shouldThrowBadRequestIfEmployeeDataFailsInConstraintValidation() throws Exception {
//        when(employeeService.createEmployee(any(Employee.class))).thenThrow(ConstraintViolationException.class);
//        Employee newEmployee =
//                new Employee(1L, "yashu", "yellapu", "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 414001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 415002, "India"));
//        mockMvc.perform(post("/api/employees")
//                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newEmployee)))
//                .andExpect(status().isBadRequest());
//    }
    @Test
    void shouldUpdateEmployeeWithExistingId() throws Exception {
        Employee updatedData =
                new Employee(1L, "yashu", "yellapu", "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 515001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 515002, "India"));
        when(employeeService.updateEmployee(any(Employee.class), eq(1L))).then(invocation -> (invocation.getArgument(0)));
        mockMvc.perform(put("/api/employees/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId", is(1)));
    }

    @Test
    void shouldThrowNotFoundWhenUpdatingEmployeeWithNonExistingId() throws Exception {
        Employee updatedData =
                new Employee(1L, "yashu", "yellapu", "yashu@Everest", null, null, null, "trainee", 0, "good", new Address(null, "ATPPreLine2", "ATPPreLine3", "guljarPet", "Andhra", 515001, "India"), new Address(null, "ATPPerLine2", "ATPPerLine3", "guljarPet", "AndhraPradesh", 515002, "India"));
        when(employeeService.updateEmployee(any(Employee.class), eq(1L))).thenThrow(EmployeeNotFoundException.class);
        mockMvc.perform(put("/api/employees/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isNotFound());
    }

}