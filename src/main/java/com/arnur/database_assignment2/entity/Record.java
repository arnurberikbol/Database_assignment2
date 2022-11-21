package com.arnur.database_assignment2.entity;

import com.arnur.database_assignment2.keys.RecordKey;

import javax.persistence.*;

@Entity
@IdClass(RecordKey.class)
public class Record {

    @Id
    private String email;

    @Id
    private String cname;

    @Id
    private String disease_code;

    @Column(name = "total_deaths")
    private int total_deaths;

    @Column(name = "total_patients")
    private int total_patients;

    public Record() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDisease_code() {
        return disease_code;
    }

    public void setDisease_code(String disease_code) {
        this.disease_code = disease_code;
    }

    public int getTotal_deaths() {
        return total_deaths;
    }

    public void setTotal_deaths(int total_deaths) {
        this.total_deaths = total_deaths;
    }

    public int getTotal_patients() {
        return total_patients;
    }

    public void setTotal_patients(int total_patients) {
        this.total_patients = total_patients;
    }

    @Override
    public String toString() {
        return "Record{" +
                "email='" + email + '\'' +
                ", cname='" + cname + '\'' +
                ", disease_code='" + disease_code + '\'' +
                ", total_deaths=" + total_deaths +
                ", total_patients=" + total_patients +
                '}';
    }
}
