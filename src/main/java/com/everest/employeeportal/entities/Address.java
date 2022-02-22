package com.everest.employeeportal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The addressLine1 field must not blank")
    private String addressLine1;
    private String addressLine2;
    @Column(name = "city")
    @NotEmpty(message = "The city field must not empty")
    private String city;
    @NotEmpty(message = "The state filed must contain empty")
    private String state;
    @NotNull(message = "The zipcode field must not null")
    @Min(value = 515000, message = "The zipcode field must have 515000 value")
    private int zipcode;
    @NotBlank(message = "The field must contain country")
    private String country;
}

