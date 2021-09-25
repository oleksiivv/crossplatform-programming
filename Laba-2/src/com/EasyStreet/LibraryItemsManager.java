package com.EasyStreet;

import java.io.File;
import java.util.*;

public class LibraryItemsManager {

    private FileSystemIO fsystem;

    public LibraryItemsManager(){
        fsystem = new FileSystemIO();
    }

    public FileSystemIO getFileSystemIO() {
        return fsystem;
    }

    public LinkedList<Book> searchBookByCategory(List<Book> items, BookCategory category){
        LinkedList<Book> result = new LinkedList<>();

        for (LibraryProductItem item : items) {
            if(item instanceof Book){
                if(((Book) item).getCategory() == category){
                    result.add((Book)item);
                }
            }
        }

        return result;
    }

    public LinkedList<Book> searchBookByGenre(List<Book> items, BookGenre genre){

        LinkedList<Book> result = new LinkedList<>();

        for (LibraryProductItem item : items) {
            if(item instanceof Book){
                if(((Book) item).getGenre() == genre){
                    result.add((Book)item);
                }
            }
        }

        return result;
    }
    public static class SortByNameManager implements Comparator<LibraryProductItem>{
        @Override
        public int compare(LibraryProductItem item1, LibraryProductItem item2) {
                    return item1.getName().compareTo(item2.getName());
        }
    }

    public class SortByPagesCountManager implements Comparator<LibraryProductItem>{
        @Override
        public int compare(LibraryProductItem item1, LibraryProductItem item2) {
            int cnt1 = item1.getPagesCount();
            int cnt2 = item2.getPagesCount();

            if (cnt1 == cnt2)
                return 0;
            else if (cnt1 > cnt2)
                return 1;
            else
                return -1;
        }
    }

    public static Comparator<Book> SortBooksByCategory = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            return b1.getCategory().name().compareTo(b2.getCategory().name());
        }
    };

    public void sortBooksByGenre(LinkedList<Book> items){
        items.sort((Book b1, Book b2)->b1.getGenre().name().compareTo(b2.getGenre().name()));
    }


    public LinkedList<Book> getOnlyBooks(LinkedList<LibraryProductItem> items){
        LinkedList<Book> booksInLibrary = new LinkedList<Book>();

        for (LibraryProductItem b: items) {
            if(b instanceof Book){
                booksInLibrary.add((Book)b);
            }
        }

        return booksInLibrary;
    }

    public void printInfoAboutAllItems(LinkedList<LibraryProductItem> items){
        for (LibraryProductItem item: items) {
            item.printInfo();
            System.out.println("--------------------");
        }
    }

    public void printInfoAboutBooks(LinkedList<Book> items){
        for (Book item: items) {
            item.printInfo();
            System.out.println("--------------------");
        }
    }

}
