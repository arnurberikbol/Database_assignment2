package com.arnur.database_assignment2.keys;

import java.io.Serializable;

public class DiscoverKey implements Serializable {

    private String cname;

    private String disease_code;

    public DiscoverKey(String cname, String disease_code) {
        this.cname = cname;
        this.disease_code = disease_code;
    }

    public DiscoverKey() {
    }
}
