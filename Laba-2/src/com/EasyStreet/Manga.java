package com.EasyStreet;

import java.util.Formatter;
import java.util.Scanner;

public class Manga extends Book{
    public Manga(){
        this.name = "";
        this.pagesCount = 1;
        this.genre = BookGenre.scienceFiction;
        this.category = BookCategory.allAges;
    }

    public Manga(String name, int pagesCount, BookGenre genre, BookCategory category){
        this.name = name;
        this.pagesCount = pagesCount;
        this.genre = genre;
        this.category = category;
    }

    protected String getType(){
        return "Manga";
    }

    public static LibraryProductItem readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String name = fread.nextLine();
            int pagesCount = Integer.parseInt(fread.nextLine());
            BookGenre genre = BookGenre.valueOf(fread.nextLine());
            BookCategory category = BookCategory.valueOf(fread.nextLine());

            return new Manga(name, pagesCount, genre, category);
        }
        return null;
    }

    public void saveIntoFile(){
        Formatter f = null;
        try{
            f=new Formatter("manga.txt");
            f.format("%s\n%d\n%s\n%s\n",this.name,this.pagesCount,this.genre, this.category);
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
}
