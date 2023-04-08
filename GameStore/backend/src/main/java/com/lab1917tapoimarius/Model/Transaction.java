package com.lab1917tapoimarius.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
public class Transaction {
    private @Id
    @GeneratedValue Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customerEntity;

    private String format;

    private Integer quantity;

    public Transaction() {
    }

    public Transaction(Game game, Customer customer, String format, Integer quantity) {
        this.game = game;
        this.customerEntity = customer;
        this.format = format;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGame() {
        return game.getId();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getCustomer() {
        return customerEntity.getId();
    }

    public void setCustomer(Customer customer) {
        this.customerEntity = customer;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Game getGameEntity(){return game;}

    public Customer getCustomerEntity(){return customerEntity;}
}
