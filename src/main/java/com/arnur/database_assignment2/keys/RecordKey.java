package com.arnur.database_assignment2.keys;

import java.io.Serializable;

public class RecordKey implements Serializable {

    private String email;

    private String cname;

    private String disease_code;

    public RecordKey(String email, String cname, String disease_code) {
        this.email = email;
        this.cname = cname;
        this.disease_code = disease_code;
    }

    public RecordKey() {
    }
}
