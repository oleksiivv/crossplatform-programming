package com.EasyStreet;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class FileSystemIO {

    public LinkedList<LibraryProductItem> readItemsFromFiles(){
        LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();

        LinkedList<LibraryProductItem> books = readBooksFromFile(new File("books.txt"));
        LinkedList<LibraryProductItem> comics = readComicsFromFile(new File("comics.txt"));
        LinkedList<LibraryProductItem> manga = readMangaFromFile(new File("manga.txt"));
        LinkedList<LibraryProductItem> coloringBooks = readColoringBooksFromFile(new File("coloring-books.txt"));
        LinkedList<LibraryProductItem> calendars = readCalendarsFromFile(new File("calendars.txt"));

        if(books!=null) items.addAll(books);
        if(comics!=null) items.addAll(comics);
        if(manga!=null) items.addAll(manga);
        if(coloringBooks!=null) items.addAll(coloringBooks);
        if(calendars!=null) items.addAll(calendars);

        return items;

    }

    public void saveItemsIntoFile(LinkedList<LibraryProductItem> items){
        for (LibraryProductItem item : items) {
            item.saveIntoFile();
        }
    }

    private LinkedList<LibraryProductItem> readBooksFromFile(File file) {
        try {
            LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();
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

    private LinkedList<LibraryProductItem> readComicsFromFile(File file) {
        try {
            LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(Comics.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }

    private LinkedList<LibraryProductItem> readMangaFromFile(File file) {
        try {
            LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(Manga.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }

    private LinkedList<LibraryProductItem> readCalendarsFromFile(File file) {
        try {
            LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(Calendar.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }

    private LinkedList<LibraryProductItem> readColoringBooksFromFile(File file) {
        try {
            LinkedList<LibraryProductItem> items = new LinkedList<LibraryProductItem>();
            Scanner fread = new Scanner(file);

            while (fread.hasNext()) {
                items.add(ColoringBook.readFromFile(fread));
            }
            fread.close();

            return items;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }
}
