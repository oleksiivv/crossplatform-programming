package com.EasyStreet;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Library {
    public LinkedList<Book> books;
    public LinkedList<Abonement> abonements;
    public Administrator administrator;

    public Library(){
        this.books = new LinkedList<Book>();
        this.abonements = new LinkedList<Abonement>();
        this.administrator = new Administrator();
    }

    public LinkedList<Book> sortBooksByYear(){
        this.books.sort(Comparator.comparingInt((Book b) -> b.getPublishYear()));
        return this.books;
    }

    public LinkedList<Abonement> getAbonementsWithMinBooks(int books){
        LinkedList<Abonement> abonementsEmail = new LinkedList<Abonement>();
        for (Abonement abonement:this.abonements) {
            if(abonement.getReceivedBooks().size()>=books){
                abonementsEmail.add(abonement);
            }
        }
        return abonementsEmail;
    }

    public int countGivenBooks(Author author){
        int occurrences = 0;
        for (Abonement abonement:this.abonements) {
            for (Book book:abonement.getReceivedBooks()) {
                if(book.getAuthor().getSurname().equals(author.getSurname()) && book.getAuthor().getName().equals(author.getName())){
                    occurrences++;
                }
            }
        }
        return occurrences;
    }

    public Abonement getAbonementWithMaxBooks(){

        Abonement topAbonement = this.abonements.get(0);
        int maxBooksCount = topAbonement.getReceivedBooks().size();

        for (Abonement abonement:this.abonements) {
            if(abonement.getReceivedBooks().size() > maxBooksCount){
                topAbonement = abonement;
                maxBooksCount = topAbonement.getReceivedBooks().size();
            }
        }

        return topAbonement;
    }

    public LinkedList<Abonement> getAbonementsWithMaxBooks(int count){
        LinkedList<Abonement> choosenAbonements = new LinkedList<Abonement>();
        for (Abonement abonement:this.abonements) {
            if(abonement.getReceivedBooks().size()<=count){
                choosenAbonements.add(abonement);
            }
        }
        return  choosenAbonements;
    }


    public void createMailing(){
        LinkedList<Abonement> newsGroup = getAbonementsWithMaxBooks(1);
        LinkedList<Abonement> remindsGroup = getAbonementsWithMinBooks(2);

        for (Abonement abonement:newsGroup) {
            this.administrator.sendMessage(abonement, "Some news message");
        }

        for (Abonement abonement:remindsGroup) {
            this.administrator.sendMessage(abonement, "Time to return book!");
        }
    }

    public LinkedList<Abonement> getDebtorsList(LocalDate date){
        LinkedList<Abonement> debtors = new LinkedList<Abonement>();

        for (Log log:this.administrator.getLog().history) {
            Abonement potentialDebtor = log.user;
            LinkedList<Log> currUserHistory = this.administrator.getLog().getAllEventsForUser(potentialDebtor);
            for (Log _log: currUserHistory) {
                if(_log.event == AbonementEvent.plannedBookReturn && _log.date.isBefore(date)){
                    boolean returned=false;
                    for(int j = currUserHistory.indexOf(_log); j<currUserHistory.size();j++){
                        if(currUserHistory.get(j).event.equals(AbonementEvent.bookReturn) && _log.book == currUserHistory.get(j).book){
                            returned = true;
                        }
                    }
                    if(!returned){
                        debtors.add(potentialDebtor);
                        this.administrator.sendMessage(potentialDebtor, "Deadline for book return[" + _log.book.toString() + "] expired: " + String.valueOf(Duration.between(_log.date, LocalDate.now()).toDays()));
                    }
                }
            }
        }

        return debtors;
    }


    public void saveBooks(){
        try
        {
            FileOutputStream fos = new FileOutputStream("booksData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.books);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void readBooks(){
        LinkedList<Book> booksList = new LinkedList<Book>();

        try
        {
            FileInputStream fis = new FileInputStream("booksData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            booksList = (LinkedList) ois.readObject();
            this.books.clear();
            this.books.addAll(booksList);

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public void saveAbonements(){
        try
        {
            FileOutputStream fos = new FileOutputStream("abonementsData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.abonements);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void readAbonements(){
        LinkedList<Abonement> abonementsList = new LinkedList<Abonement>();

        try
        {
            FileInputStream fis = new FileInputStream("abonementsData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            abonementsList = (LinkedList) ois.readObject();
            this.abonements.clear();
            this.abonements.addAll(abonementsList);

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public void printInfo(){
        System.out.println("All books: ");
        this.books.stream().map(x->x.toString()).forEach(System.out::println);

        System.out.println("All abonements: ");
        this.abonements.stream().map(x->x.toString()).forEach(System.out::println);

        System.out.println("Logs: ");
        this.administrator.getLog().history.stream().map(x->x.toString()).forEach(System.out::println);
    }
}
