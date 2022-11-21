package com.arnur.database_assignment2.entity;

import com.arnur.database_assignment2.keys.SpecializeKey;

import javax.persistence.*;

@Entity
@IdClass(SpecializeKey.class)
public class Specialize {

    @Id
    private int id;

    @Id
    private String email;

    public Specialize() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Specialize{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
