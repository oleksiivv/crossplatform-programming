package com.EasyStreet;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.setFile(new File("text.txt"));

        String content = fileManager.readFromFile();

        String contentWithoutExtraSpaces = StringFormatter.deleteExtraSpaces(content);
        System.out.println("Text without extra spaces:");
        System.out.println(contentWithoutExtraSpaces);

        LinkedList<String> nestedStrings = StringFormatter.findNestedStrings(contentWithoutExtraSpaces);
        System.out.println("Nested strings: ");
        for (String nestedStr : nestedStrings) {
            System.out.print(nestedStr+"\t");
        }
        System.out.println();

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add("in");
        tokens.add("et");

        String mostCommonInText = StringFormatter.getMostCommonWord(contentWithoutExtraSpaces);
        String replaced = StringFormatter.replaceEachToken(contentWithoutExtraSpaces, mostCommonInText, tokens);

        System.out.println("Most common word: " + mostCommonInText);
        System.out.println("Text with replaced tokens: ");
        System.out.println(replaced);
    }
}
