package com.everest.employeeportal.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "everest_email_id")
    @Email
    private String everestEmailId;
    @Column(name = "personal_email_id")
    @Email
    private String personalEmailId;
    @Column(name = "date_of_birth")
    @Pattern(regexp = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "date_of_join")
    @Pattern(regexp = "yyyy-MM-dd")
    private Date dateOfJoin;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prev_experience")
    private int prevExperience;
    @Column(name = "bio")
    @NotBlank(message="The field must contain one word")
    private String bio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
