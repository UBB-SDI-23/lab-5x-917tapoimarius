package com.lab1917tapoimarius.Model;

public class TransactionDTO {
    private String firstname;
    private String lastname;
    private Double totalSpent;

    public TransactionDTO(String firstname, String lastname, Double totalSpent) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalSpent = totalSpent;
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

//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double avgSpent) {
        this.totalSpent = avgSpent;
    }
}
