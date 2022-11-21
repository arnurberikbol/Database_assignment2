package com.arnur.database_assignment2.entity;

import javax.persistence.*;

@Entity
@Table(name = "publicservant")
public class Publicservant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;

    public Publicservant() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "PublicServant{" +
                "email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}