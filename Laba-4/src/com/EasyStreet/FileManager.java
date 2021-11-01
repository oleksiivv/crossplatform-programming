package com.EasyStreet;

import java.io.File;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Scanner;

public class FileManager {

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String readFromFile(){
        try {
            String content = new Scanner(this.file).useDelimiter("\\Z").next();
            return content;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
            return null;
        }
    }

    public boolean saveIntoFile(String content){
        Formatter f = null;
        try{
            f=new Formatter(this.file.getName());
            f.format("%s",content);
            f.close();

            return true;
        }
        catch(Exception e) {
            System.out.println("Error");
            return false;
        }
    }
}
