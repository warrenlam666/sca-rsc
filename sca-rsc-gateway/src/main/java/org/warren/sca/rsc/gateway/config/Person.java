package org.warren.sca.rsc.gateway.config;

import java.io.Serializable;

public class Person implements Serializable {
    private int id = 1;
    private long sid = 717691538615963648L;
    private String name = "warren";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
