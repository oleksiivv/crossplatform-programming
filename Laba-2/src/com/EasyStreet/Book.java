package com.EasyStreet;

import java.util.Formatter;
import java.util.Scanner;

public class Book extends LibraryProductItem{

    protected BookCategory category;
    protected BookGenre genre;

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public BookCategory getCategory() {
        return category;
    }

    public Book setGenre(BookGenre genre) {
        this.genre = genre;
        return this;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public Book(){
        this.name = "";
        this.pagesCount = 1;
        this.genre = BookGenre.scienceFiction;
        this.category = BookCategory.allAges;
    }

    public Book(String name, int pagesCount, BookGenre genre, BookCategory category){
        this.name = name;
        this.pagesCount = pagesCount;
        this.genre = genre;
        this.category = category;
    }

    protected String getType(){
        return "Book";
    }

    public void printInfo(){
        System.out.println(this.getType());
        System.out.println("Name: " + this.name);
        System.out.println("Pages: " + this.pagesCount);
        System.out.println("Genre: " + this.genre.toString());
        System.out.println("Category: " + this.category);
    }

    public static LibraryProductItem readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String name = fread.nextLine();
            int pagesCount = Integer.parseInt(fread.nextLine());
            BookGenre genre = BookGenre.valueOf(fread.nextLine());
            BookCategory category = BookCategory.valueOf(fread.nextLine());

            return new Book(name, pagesCount, genre, category);
        }
        return null;
    }

    public void saveIntoFile(){
        Formatter f = null;
        try{
            f=new Formatter("books.txt");
            f.format("%s\n%d\n%s\n%s\n",this.name,this.pagesCount,this.genre, this.category);
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
}
