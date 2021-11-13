package com.EasyStreet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Log implements Serializable {
    public LocalDate date;
    public AbonementEvent event;
    public Abonement user;
    public Book book;

    public Log(LocalDate date, AbonementEvent event, Abonement user, Book book){
        this.date = date;
        this.event = event;
        this.user = user;
        this.book = book;
    }

    @Override
    public String toString() {
        return "[date="+this.date.toString()+", event="+this.event.name()+", user="+this.user.toString()+", book="+this.book.toString()+"]";
    }
}
