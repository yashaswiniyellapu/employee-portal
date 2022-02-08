package com.everest.employeeportal.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_details")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "everest_email_id")
    private String everestEmailId;
    @Column(name = "personal_email_id")
    private String personalEmailId;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "date_of_join")
    private Date dateOfJoin;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prev_experience")
    private int prevExperience;
    @Column(name = "bio")
    private String bio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address")
    private Address presentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address")
    private Address permanentAddress;

}
