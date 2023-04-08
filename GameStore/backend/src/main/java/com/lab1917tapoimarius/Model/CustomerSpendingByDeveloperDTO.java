package com.lab1917tapoimarius.Model;

public class CustomerSpendingByDeveloperDTO {
    private String customerName;
    private String developerName;
    private Double totalSpending;

    public CustomerSpendingByDeveloperDTO() {
    }

    public CustomerSpendingByDeveloperDTO(String customerName, String developerName, Double totalSpending) {
        this.customerName = customerName;
        this.developerName = developerName;
        this.totalSpending = totalSpending;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public Double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(Double totalSpending) {
        this.totalSpending = totalSpending;
    }
}
