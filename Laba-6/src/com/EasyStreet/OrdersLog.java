package com.EasyStreet;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class OrdersLog implements Serializable {
    public LinkedList<Log> history;

    public OrdersLog(){
        history = new LinkedList<Log>();
    }

    public LinkedList<Log> getAllEventsForUser(Abonement abonement){
        return this.history.stream().filter(x -> x.user.getEmail().equals(abonement.getEmail())).collect(Collectors.toCollection(LinkedList::new));
    }
}
