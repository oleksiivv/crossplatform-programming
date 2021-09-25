package com.EasyStreet;

import java.util.Formatter;

abstract public class LibraryProductItem {
    protected String name;
    protected int pagesCount;

    public LibraryProductItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public LibraryProductItem setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
        return this;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public abstract void printInfo();

    public abstract void saveIntoFile();

}
