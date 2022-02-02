package com.everest.employeeportal.entities;

import lombok.Data;

import java.util.Optional;

@Data
public class Address {
    private String addressLine1;
    private Optional<String> addressLine2;
    private String city;
    private String state;
    private int zipcode;
    private String country;
}
