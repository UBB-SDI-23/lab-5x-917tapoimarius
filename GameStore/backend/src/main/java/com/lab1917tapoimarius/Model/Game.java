package com.lab1917tapoimarius.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
//@Table(name = "GAME")
public class Game {
    //@Column
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    //@Column
    private String name;
    //Column
    private String genre;
    //@Column
    private String modes;
    //@Column
    private Integer yearOfRelease;
    //@Column
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "developer_id")
    private Developer developer;

    String description;

    @Formula("(SELECT sum(transaction.quantity)\n" +
            "FROM transaction INNER JOIN game ON transaction.game_id = game.id\n" +
            "WHERE game.id = id)")
    private Integer totalNumberOfBoughtQuantity;


    public Game() {
    }

    public Game(String name, String genre, String modes, Integer yearOfRelease, Double price, Developer developer, String description) {
        this.name = name;
        this.genre = genre;
        this.modes = modes;
        this.yearOfRelease = yearOfRelease;
        this.price = price;
        this.developer = developer;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String developer) {
        this.modes = developer;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getDeveloper() {
        return developer.getId();
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Developer getDeveloperEntity(){
        return developer;
    }

    public Integer getTotalNumberOfBoughtQuantity() {
        return totalNumberOfBoughtQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", modes='" + modes + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", price=" + price +
                ", developer=" + developer +
                '}';
    }
}
