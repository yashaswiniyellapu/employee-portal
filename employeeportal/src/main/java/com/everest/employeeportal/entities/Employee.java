package com.everest.employeeportal.entities;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class Employee {
    private Long empId;
    private String firstName;
    private String lastName;
    private String everestEmailId;
    private String personalEmailId;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private String designation;
    private int prevExperience;
    private String bio;
    private Address presentAddress;
    private Address permanentAddress;

}
