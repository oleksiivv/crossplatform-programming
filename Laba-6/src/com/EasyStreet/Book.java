package com.EasyStreet;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable {

    private Author author;
    private String name;
    private int publishYear;

    public Book(){}

    public Book(Author author, String name, int publishYear){
        this.author = author;
        this.name = name;
        this.publishYear = publishYear;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Book setPublishYear(int publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    /*public static Book readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String name = fread.nextLine();
            int year = Integer.parseInt(fread.nextLine());
            String authorName = fread.nextLine();
            String authorMiddleName = fread.nextLine();
            String authorSurname = fread.nextLine();

            Author a = new Author(authorName, authorMiddleName, authorSurname);

            return new Book(a, name, year);
        }
        return null;
    }*/

    @Override
    public String toString() {
        return "[name="+this.name+", publishYear="+this.publishYear+", author="+this.author.toString()+"]";
    }
}
