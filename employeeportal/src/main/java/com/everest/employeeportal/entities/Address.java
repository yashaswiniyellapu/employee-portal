package com.everest.employeeportal.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "present_address")
@SecondaryTable(name ="permanent_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "address_line1",table = "permanent_address")
    private String addressLine1;
    @Column(name = "address_line2",table = "permanent_address")
    private String addressLine2;
    @Column(name = "city",table = "permanent_address")
    private String city;
    @Column(name = "state",table = "permanent_address")
    private String state;
    @Column(name = "zipcode",table = "permanent_address")
    private int zipcode;
    @Column(name = "country",table = "permanent_address")
    private String country;
}
