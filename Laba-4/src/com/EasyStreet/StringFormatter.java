package com.EasyStreet;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

    public static String deleteExtraSpaces(String string){
        return string.replaceAll("( +)"," ").trim();
    }

    public static LinkedList<String> findNestedStringsWithRegex(String string){
        LinkedList<String> matches = new LinkedList<>();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(string);
        while(m.find()) {
            matches.add("\""+m.group(1)+"\"");
        }

        p = Pattern.compile("\'([^\']*)\'");
        m = p.matcher(string);
        while(m.find()) {
            matches.add("\'"+m.group(1)+"\'");
        }

        return matches;
    }

    public static LinkedList<String> findNestedStrings(String string){
        LinkedList<String> matches = new LinkedList<>();
        char[] line = string.toCharArray();

        char delimeter;
        for (int i = 0; i < line.length; i++) {
            if(line[i] == '\"' || line[i] == '\'') {
                delimeter = line[i];
                String tmp = "";
                do {
                    if(line[i] != delimeter){
                        tmp += line[i];
                    }
                    i++;
                }while(line[i] != delimeter);

                matches.add(delimeter + tmp + delimeter);
                LinkedList<String> nested = findNestedStrings(tmp);
                if(nested != null){
                    matches.addAll(nested);
                }
            }
        }
        if(matches.size() == 0){
            return null;
        }
        return matches;
    }


    public static String getMostCommonWord(String string){
        String[] splited = string.split(" ");
        Arrays.sort(splited);
        int max = 0;
        int count= 1;
        String word = splited[0];
        String curr = splited[0];
        for(int i = 1; i<splited.length; i++){
            if(splited[i].equals(curr)){
                count++;
            }
            else{
                count =1;
                curr = splited[i];
            }
            if(max<count){
                max = count;
                word = splited[i];
            }
        }
        return word;
    }

    public static String replaceEachToken(String string, String newWord, ArrayList<String> tokens){
        String replaced = string;

        for (String token: tokens) {
            replaced = replaced.replaceAll(" " + token + " ", " " + newWord + " ");
        }

        return replaced;
    }
}
