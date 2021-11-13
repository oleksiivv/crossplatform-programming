package com.EasyStreet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        Administrator admin = new Administrator("AdminName", "AdminMiddleName", "AdminSurname", "admin@gmail.com");
        library.administrator = admin;



        library.readBooks();
        library.readAbonements();
        admin.readLogs();

        int opt = -1;
        do {
            library.printInfo();
            System.out.println(
                    "1 - add book to library\n" +
                            "2 - create abonement\n" +
                            "3 - give book\n" +
                            "4 - take book\n" +
                            "5 - sort books by publishing year\n" +
                            "6 - get abonements with min books amount\n" +
                            "7 - count books of Author\n" +
                            "8 - find abonement with max books amount\n" +
                            "9 - create mailing group\n" +
                            "10 - get debtors list\n");

            opt = sc.nextInt();
            LibraryController.doOperation(library, opt);

        }while(opt!=-1);

        library.saveBooks();
        library.saveAbonements();
        library.administrator.saveLogs();

    }
}
