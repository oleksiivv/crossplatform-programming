package com.EasyStreet;

import java.util.Formatter;
import java.util.Scanner;

public class Calendar extends LibraryProductItem{

    private int year;

    public Calendar(){
        this.year = 2021;
        this.pagesCount = 12;
    }

    public Calendar(int year){
        this.year = year;
        this.pagesCount = 12;
    }

    public Calendar setYear(int year) {
        this.year = year;
        return this;
    }

    public int getYear() {
        return year;
    }

    public void printInfo(){
        System.out.println("Calendar");
        System.out.println("Year: " + this.year);
    }

    public static LibraryProductItem readFromFile(Scanner fread){
        if(fread.hasNextLine()) {
            String name = fread.nextLine();
            int year = Integer.parseInt(fread.nextLine());
            LibraryProductItem calendar = new Calendar(year);
            calendar.setName(name);
            return calendar;
        }
        return null;
    }

    public void saveIntoFile(){
        Formatter f = null;
        try{
            f=new Formatter("calendars.txt");
            f.format("%s\n%d\n",this.name,this.year);
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
}
