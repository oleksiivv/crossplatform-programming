package com.EasyStreet;


/*

Потрібно створити класи, що описують сутності запропоновані в заданні. Відобразити UML-діаграму класів. Створити програму та продемонструвати її  роботу.

Загальні рекомендації:
Використовувати можливості ООП: класи, успадкування, поліморфізм, інкапсуляцію.
Кожний клас повинен мати назву, яка повністю описує його суть, і інформативний склад. Атрибути і методи класів слід визначити самостійно.
Успадкування потрібно використовувати тільки тоді, коли воно має сенс. У випадку використання наслідування кількість класів-нащадків має бути не меншою 2 і не більшою 4-ьох
При записі програми потрібно використовувати домовленості щодо оформлення коду java code convention.
Зображати всі пари set/get (ака сеттери/геттери) для атрибутів класу не потрібно з метою уникнення засмічення діаграми
Для реалізації операцій пошуку/сортування слід реалізувати окремий клас (в назві якого має бути присутнє слово Manager)
Реалізувати один з методів сортування з використанням компаратора, реалізованого як статичний вкладений клас (static inner class)
Реалізувати наступний з методів сортування з використанням компаратора, реалізованого як вкладений клас (inner class)
Додати ще один метод сортування (додатковий), який реалізує сортування з використанням анонімного класу (anonymous inner class)
Додати ще один, 4-й метод сортування з використанням лямбда-виразів .


Книжковий магазин. Реалізувати ієрархію товарів, доступних в книжковому магазині (наприклад книги, календарі, розмальовки і т.д)
Реалізувати пошук книги за певним жанром, або товару з певною категорією (наприклад дитячі товари)..
Якщо якийсь з товарів немає сторінок - то вважати кількість сторінок для нього рівним 1
Реалізувати можливість  сортування знайденого товару за двома типами параметрів (на вибір, реалізовано як два окремі методи)
Реалізація сортування має передбачати можливість сортувати як за спаданням, так і за зростанням
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        LibraryItemsManager manager = new LibraryItemsManager();

        LinkedList<LibraryProductItem> items = manager.getFileSystemIO().readItemsFromFiles();
        LinkedList<Book> booksInLibrary = manager.getOnlyBooks(items);

        System.out.println("\nAll items in library:\n");
        manager.printInfoAboutAllItems(items);

        System.out.println("\nAll books in library:\n");
        manager.printInfoAboutBooks(booksInLibrary);

        System.out.println("\nAll available genres:\n");
        int cnt=1;
        for (BookGenre genre: BookGenre.values()) {
            System.out.println(String.valueOf(cnt) + " - "+genre.name());
            cnt++;
        }
        System.out.println("Enter genre:");
        int genreId = sc.nextInt();
        genreId--;

        LinkedList<Book> filteredBooks = booksInLibrary;

        if(genreId >= 0 && genreId < BookGenre.values().length){
            System.out.println("\nResult:");
            filteredBooks = manager.searchBookByGenre(booksInLibrary, BookGenre.values()[genreId]);
            System.out.println(BookGenre.values()[genreId].name() + ":");
            manager.printInfoAboutBooks(filteredBooks);
            System.out.println("===================");
        }

        System.out.println("\nAll available categories:\n");
        cnt=1;
        for (BookCategory category: BookCategory.values()) {
            System.out.println(String.valueOf(cnt) + " - "+category.name());
            cnt++;
        }
        System.out.println("Enter category:");
        int categoryId = sc.nextInt();
        categoryId--;

        if(categoryId >= 0 && categoryId < BookCategory.values().length){
            System.out.println("\nResult:");
            filteredBooks = manager.searchBookByCategory(filteredBooks, BookCategory.values()[categoryId]);
            System.out.println(BookCategory.values()[categoryId].name() + ":");
            manager.printInfoAboutBooks(filteredBooks);
            System.out.println("===================");
        }

        System.out.println("1 - sort all items by name");
        System.out.println("2 - sort all items by page count");
        System.out.println("3 - sort filtered books by name");
        System.out.println("4 - sort filtered books by page count");
        System.out.println("5 - sort filtered books by category");
        System.out.println("6 - sort filtered books by genre");

        System.out.println(">>>");
        int sortOption = sc.nextInt();

        System.out.println("\nResult:");
        switch (sortOption){
            case 1:
                Collections.sort(items, new LibraryItemsManager.SortByNameManager());
                manager.printInfoAboutAllItems(items);
                break;
            case 2:
                Collections.sort(items, new LibraryItemsManager().new SortByPagesCountManager());
                manager.printInfoAboutAllItems(items);
                break;
            case 3:
                Collections.sort(filteredBooks, new LibraryItemsManager.SortByNameManager());
                manager.printInfoAboutBooks(filteredBooks);
                break;
            case 4:
                Collections.sort(filteredBooks, new LibraryItemsManager().new SortByPagesCountManager());
                manager.printInfoAboutBooks(filteredBooks);
                break;
            case 5:
                Collections.sort(filteredBooks, Collections.reverseOrder(LibraryItemsManager.SortBooksByCategory));
                manager.printInfoAboutBooks(filteredBooks);
                break;
            case 6:
                manager.sortBooksByGenre(filteredBooks);
                manager.printInfoAboutBooks(filteredBooks);
                break;
        }

        //Collections.sort(items, Collections.reverseOrder(new LibraryItemsManager.SortByNameManager()));
        //LibraryItemsManager.saveItemsIntoFile(items);

    }
}
