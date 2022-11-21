package com.arnur.database_assignment2.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cname")
    private String cname;

    @Column(name = "population")
    private BigInteger population;

    public Country() {
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public BigInteger getPopulation() {
        return population;
    }

    public void setPopulation(BigInteger population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "cname='" + cname + '\'' +
                ", population=" + population +
                '}';
    }
}
