package com.EasyStreet;

import java.io.Serializable;

public class User implements Serializable {

    protected String name;
    protected String surname;
    protected String middlename;

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getName() {
        return name;
    }
}
