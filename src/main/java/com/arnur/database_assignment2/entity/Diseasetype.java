package com.arnur.database_assignment2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diseasetype")
public class Diseasetype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DiseaseType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
