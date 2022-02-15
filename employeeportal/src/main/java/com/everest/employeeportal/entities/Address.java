package com.everest.employeeportal.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address_line_1")
    @NotEmpty
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "city")
    @NotEmpty
    private String city;
    @Column(name = "state")
    @NotEmpty
    private String state;
    @Column(name = "zipcode")
    @NotNull
    private int zipcode;
    @Column(name = "country")
    @NotEmpty
    private String country;
}
