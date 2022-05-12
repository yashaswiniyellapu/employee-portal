package com.everest.employeeportal.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotBlank(message = "The firstName field must not blank")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "The secondName field must not empty")
    private String lastName;
    private String password;
    @Column(name = "everest_email_id")
    @Email(message = "The everestEmailId field must contain valid emailId")
    private String everestEmailId;
    @Column(name = "personal_email_id")
    @Nullable
    @Email(message = "The personalEmailId field must contain valid emailId")
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
    @NotBlank(message = "The bio field must contain one word")
    private String bio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
