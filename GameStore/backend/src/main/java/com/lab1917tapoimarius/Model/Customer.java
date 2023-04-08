package com.lab1917tapoimarius.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer {
    private @Id @GeneratedValue Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotBlank(message = "Last name is mandatory")
    private String lastname;

    @Email
    private String email;

    @NotBlank(message = "Adress is mandatory")
    private String address;
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String address, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
