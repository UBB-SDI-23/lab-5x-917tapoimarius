package com.lab1917tapoimarius.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Formula;

@Entity
//@Table(name = "CUSTOMER")
public class Customer {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @NotBlank(message = "Last name is mandatory")
    private String lastname;

    @Email(message = "Email address is invalid")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String address;
    @Digits(integer = 10, fraction =0, message = "The phone number must be at least 10 digits long")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Formula("(SELECT SUM(transaction.quantity) " +
            "FROM transaction " +
            "INNER JOIN customer ON transaction.customer_id = customer.id " +
            "WHERE customer.id = id)")
    private Integer totalNumberOfBoughtGames;

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

    public Integer getTotalNumberOfBoughtGames() {
        return totalNumberOfBoughtGames;
    }
}
