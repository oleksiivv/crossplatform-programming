package com.EasyStreet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int len;
        System.out.print("Enter word length: ");
        len = sc.nextInt();

        TextSearchController controller = new TextSearchController();
        controller.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "My second tt question? Sed amet elementum."
                + "Integer nec nn diam erat, eu consectetur hi nibh?"
                + "Cum sociis natoque penatibus et magnis dis parturient montes.");

        controller.search(len);

        for (String sentence:controller.getSentences()) {
            System.out.println("Sentence: " + sentence);
        }

        System.out.println("Matches: ");
        for (String word:controller.getMatches()) {
            System.out.print("[" + word + "]\t");
        }
    }
}
