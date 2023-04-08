package com.lab1917tapoimarius.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
//@Table(name = "GAME")
public class Game {
    //@Column
    private @Id @GeneratedValue Long id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "dev_id")
    private Developer developer;

    public Game() {
    }

    public Game(String name, String genre, String modes, Integer yearOfRelease, Double price, Developer developer) {
        this.name = name;
        this.genre = genre;
        this.modes = modes;
        this.yearOfRelease = yearOfRelease;
        this.price = price;
        this.developer = developer;
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
