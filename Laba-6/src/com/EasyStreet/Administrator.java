package com.EasyStreet;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class Administrator extends User{

    private String email;
    private OrdersLog log;

    public Administrator(){
        this.log = new OrdersLog();
    }

    public Administrator(String name, String middlename, String surname, String email){
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.email = email;
        this.log = new OrdersLog();
    }

    public Administrator setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void giveBook(Abonement abonement, Book book){
        log.history.add(new Log(LocalDate.now(),AbonementEvent.giveBook, abonement, book));
        log.history.add(new Log(LocalDate.now().plusDays(30),AbonementEvent.plannedBookReturn, abonement, book));
        abonement.receiveBook(book);
    }

    public void getBook(Abonement abonement, Book book){
        log.history.add(new Log(LocalDate.now(),AbonementEvent.bookReturn, abonement, book));
        abonement.returnBook(book);
    }

    public OrdersLog getLog() {
        return log;
    }

    public void sendMessage(Abonement abonement, String message){
        abonement.receiveMessage(message);
    }

    public void saveLogs(){
        try
        {
            FileOutputStream fos = new FileOutputStream("logsData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.log.history);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void readLogs(){
        LinkedList<Log> logs = new LinkedList<Log>();

        try
        {
            FileInputStream fis = new FileInputStream("logsData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            logs = (LinkedList) ois.readObject();
            this.log.history.clear();
            this.log.history.addAll(logs);

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

}
