package com.EasyStreet;

import java.util.Formatter;
import java.util.Scanner;

public class ColoringBook extends LibraryProductItem{
    private int colorsNumber;

    public ColoringBook(){
        this.name = "";
        this.pagesCount = 1;
    }

    public ColoringBook(String name, int pagesCount, int colorsNumber){
        this.name = name;
        this.pagesCount = pagesCount;
        this.colorsNumber = colorsNumber;
    }


    public ColoringBook setColorsNumber(int colorsNumber) {
        this.colorsNumber = colorsNumber;
        return this;
    }

    public int getColorsNumber() {
        return colorsNumber;
    }


    public void printInfo(){
        System.out.println("Coloring book");
        System.out.println("Name: " + this.name);
        System.out.println("Pages: " + this.pagesCount);
        System.out.println("Colors: " + this.colorsNumber);
    }

    public static LibraryProductItem readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String name = fread.nextLine();
            int pagesCount = Integer.parseInt(fread.nextLine());
            int colorsNumber = Integer.parseInt(fread.nextLine());

            return new ColoringBook(name, pagesCount, colorsNumber);
        }
        return null;
    }

    public void saveIntoFile(){
        Formatter f = null;
        try{
            f=new Formatter("coloring-books.txt");
            f.format("%s\n%d\n%d\n",this.name,this.pagesCount, this.colorsNumber);
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
}
