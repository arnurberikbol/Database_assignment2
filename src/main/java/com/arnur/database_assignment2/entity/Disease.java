package com.arnur.database_assignment2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_code")
    private String disease_code;

    @Column(name = "pathogen")
    private String pathogen;

    @Column(name = "description")
    private String description;

    @Column(name = "id")
    private int id;

    public Disease() {
    }

    public String getDisease_code() {
        return disease_code;
    }

    public void setDisease_code(String disease_code) {
        this.disease_code = disease_code;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "disease_code=" + disease_code +
                ", pathogen='" + pathogen + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
