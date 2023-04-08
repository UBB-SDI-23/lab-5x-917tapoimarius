package com.lab1917tapoimarius.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
//@Table(name = "DEVELOPER")
public class Developer {
    //@Column
    private @Id @GeneratedValue Long id;
    //@Column
    private String name;
    //@Column
    private String hq;
    //@Column
    private String publisher;
    //@Column
    private Integer foundedIn;
    //@Column
    private Integer revenue;

    @OneToMany(mappedBy = "developer")
    @JsonIgnore
    private Set<Game> games;

    public Developer() {
    }

    public Developer(String name, String hq, String publisher, Integer foundedIn, Integer revenue) {
        this.name = name;
        this.hq = hq;
        this.publisher = publisher;
        this.foundedIn = foundedIn;
        this.revenue = revenue;
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

    public String getHq() {
        return hq;
    }

    public void setHq(String hq) {
        this.hq = hq;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getFoundedIn() {
        return foundedIn;
    }

    public void setFoundedIn(Integer foundedIn) {
        this.foundedIn = foundedIn;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hq='" + hq + '\'' +
                ", publisher='" + publisher + '\'' +
                ", foundedIn=" + foundedIn +
                ", revenue=" + revenue +
                '}';
    }
}
