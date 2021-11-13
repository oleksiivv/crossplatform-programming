package com.EasyStreet;

import java.io.Serializable;

public class Author extends User{

    public Author(String name, String middlename, String surname){
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
    }
    @Override
    public String toString() {
        return "[name="+this.getName()+", middlename="+this.getMiddlename()+", surname="+this.getSurname()+"]";
    }
}
