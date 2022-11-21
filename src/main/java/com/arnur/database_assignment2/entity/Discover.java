package com.arnur.database_assignment2.entity;

import com.arnur.database_assignment2.keys.DiscoverKey;

import javax.persistence.*;

@Entity
@IdClass(DiscoverKey.class)
public class Discover {

    @Id
    private String cname;

    @Id
    private String disease_code;

    @Column(name = "first_enc_date")
    private String first_enc_date;

    public Discover() {
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

    public String getFirst_enc_date() {
        return first_enc_date;
    }

    public void setFirst_enc_date(String first_enc_date) {
        this.first_enc_date = first_enc_date;
    }

    @Override
    public String toString() {
        return "Discover{" +
                "cname=" + cname +
                ", disease_code=" + disease_code +
                ", first_enc_date='" + first_enc_date + '\'' +
                '}';
    }
}
