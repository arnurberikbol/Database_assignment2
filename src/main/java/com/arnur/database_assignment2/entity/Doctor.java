package com.arnur.database_assignment2.entity;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;

@Entity
@Table(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String email;

    @Column(name = "degree")
    private String degree;

    public Doctor() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "email=" + email +
                ", degree='" + degree + '\'' +
                '}';
    }
}
