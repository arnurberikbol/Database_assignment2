package com.arnur.database_assignment2.keys;

import java.io.Serializable;

public class SpecializeKey implements Serializable {

    private int id;

    private String email;

    public SpecializeKey(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public SpecializeKey() {
    }
}
