package com.EasyStreet;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class LibraryController {
    public static Scanner sc = new Scanner(System.in);
    public static boolean doOperation(Library lib, int operation){
        if(operation<=0){
            return false;
        }
        else{
            switch (operation){
                case 1:
                    addBook(lib);
                    break;
                case 2:
                    addAbonement(lib);
                    break;
                case 3:
                    giveBook(lib);
                    break;
                case 4:
                    takeBook(lib);
                    break;
                case 5:
                    sortBooksByPublishYear(lib);
                    break;
                case 6:
                    getAbonementsWithMinBooksAmount(lib);
                    break;
                case 7:
                    countAuthorBooks(lib);
                    break;
                case 8:
                    findAbonementWithMaxBooksAmount(lib);
                    break;
                case 9:
                    createMailingGroup(lib);
                    break;
                case 10:
                    getDebtors(lib);
                    break;
            }
            return true;
        }
    }

    public static void addBook(Library lib){
        Book b = new Book();

        String name;
        System.out.println("Name: ");
        name = sc.nextLine();
        b.setName(name);

        System.out.println("Author (name, middle name, surname): ");
        String authorName = sc.nextLine();
        String authorMiddlename = sc.nextLine();
        String authorSurname = sc.nextLine();
        b.setAuthor(new Author(authorName, authorMiddlename, authorSurname));

        System.out.println("Publish year: ");
        int pubYear = sc.nextInt();
        b.setPublishYear(pubYear);

        lib.books.add(b);

    }

    public static void addAbonement(Library lib){

        System.out.println("name, middle name, surname, email: ");
        String name = sc.nextLine();
        String middlename = sc.nextLine();
        String surname = sc.nextLine();
        String email = sc.nextLine();

        Abonement a = new Abonement(name, middlename, surname, email);

        lib.abonements.add(a);
    }

    public static  void giveBook(Library lib){
        System.out.println("Abonement email: ");
        String email = sc.nextLine();
        System.out.println("Book name: ");
        String bookName = sc.nextLine();

        lib.administrator.giveBook(
                lib.abonements.stream().filter(x -> x.getEmail().equals(email)).findFirst().get(),
                lib.books.stream().filter(x->x.getName().equals(bookName)).findFirst().get()
        );
    }

    public static void takeBook(Library lib){
        System.out.println("Abonement email: ");
        String email = sc.nextLine();
        System.out.println("Book name: ");
        String bookName = sc.nextLine();

        lib.administrator.getBook(
                lib.abonements.stream().filter(x -> x.getEmail().equals(email)).findFirst().get(),
                lib.books.stream().filter(x->x.getName().equals(bookName)).findFirst().get()
        );
    }

    public static  void sortBooksByPublishYear(Library lib){
        lib.sortBooksByYear().stream().map(x->x.toString()).forEach(System.out::println);;
    }

    public static void getAbonementsWithMinBooksAmount(Library lib){
        System.out.println("Min books amount: ");
        int amount = sc.nextInt();
        lib.getAbonementsWithMinBooks(amount).stream().map(x->x.toString()).forEach(System.out::println);
    }

    public static void countAuthorBooks(Library lib){
        System.out.println("Author (name, middle name, surname): ");
        String authorName = sc.nextLine();
        String authorMiddlename = sc.nextLine();
        String authorSurname = sc.nextLine();
        Author a = new Author(authorName, authorMiddlename, authorSurname);
        int res = lib.countGivenBooks(a);
        System.out.println("Given books: "+String.valueOf(res));
    }

    public static void findAbonementWithMaxBooksAmount(Library lib){
        System.out.println("Max books amount: ");
        int amount = sc.nextInt();
        lib.getAbonementsWithMaxBooks(amount).stream().map(x->x.toString()).forEach(System.out::println);
    }

    public static void createMailingGroup(Library lib){
        lib.createMailing();
    }

    public static void getDebtors(Library lib){
        lib.getDebtorsList(LocalDate.now()).stream().map(x->x.toString()).forEach(System.out::println);;
    }

    /*
    private LinkedList<Book> readBooksFromFile(File file) {
        try {
            LinkedList<Book> items = new LinkedList<Book>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(Book.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }
    */
}
