package com.EasyStreet;

import java.io.Serializable;
import java.util.LinkedList;

public class Abonement extends User implements Serializable {

    private String email;
    private LinkedList<Book> receivedBooks;

    public Abonement(){
        receivedBooks = new LinkedList<Book>();
    }

    public Abonement(String name, String middlename, String surname, String email){
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.email = email;
        this.receivedBooks = new LinkedList<Book>();
    }

    public Abonement setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LinkedList<Book> getReceivedBooks() {
        return receivedBooks;
    }

    public void receiveBook(Book book){
        this.receivedBooks.add(book);
    }

    public void returnBook(Book book){
        this.receivedBooks.remove(this.receivedBooks.stream().filter(x->x.getName().equals(book.getName())).findFirst().get());
    }

    @Override
    public String toString() {
        String serialized = "[name="+this.getName()+", middlename="+this.getMiddlename()+", surname="+this.getSurname()+", email="+this.getEmail()+", receivedBooks=[";
        for (Book book:this.receivedBooks) {
            serialized+=book.toString()+", ";
        }
        serialized = serialized.substring(0, serialized.length() - 2);
        serialized+=" ]";
        return serialized+"]";
    }

    public void receiveMessage(String message){
        System.out.println("Abonement ["+this.getEmail()+"] received message: "+message);
    }
}
