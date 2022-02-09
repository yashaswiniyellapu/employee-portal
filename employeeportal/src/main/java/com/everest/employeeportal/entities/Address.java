package com.everest.employeeportal.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@MappedSuperclass
@Data
public abstract class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zipcode")
    private int zipcode;
    @Column(name = "country")
    private String country;

}
@Entity
@Table(name ="present_address")
class PresentAddress extends Address{
}
@Entity
@Table(name ="permanent_address")
class PermanentAddress extends Address {

}
