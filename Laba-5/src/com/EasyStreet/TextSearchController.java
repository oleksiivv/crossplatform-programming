package com.EasyStreet;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSearchController {

    private LinkedList<String> sentences;
    private LinkedList<String> words;

    private String text;

    public TextSearchController(){
        sentences = new LinkedList<String>();
        words = new LinkedList<String>();
    }

    public void search(int len){
        if(this.text.length() > 0) {
            this.searchQuestionSentences();
            this.searchWordsInSentence(len);
        }
    }

    public LinkedList<String> getMatches() {
        return words;
    }

    public LinkedList<String> getSentences() {
        return sentences;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    private void searchQuestionSentences(){
        String str = this.text;
        Pattern sentencePattern = Pattern.compile("([^.?!]*)\\?");
        Matcher sentenceMatcher = sentencePattern.matcher(str);

        while(sentenceMatcher.find())
        {
            this.sentences.add(sentenceMatcher.group());
        }
    }

    private void searchWordsInSentence(int len){
        Pattern wordsPattern = Pattern.compile(String.format("\\b\\w{%d}\\b", len));
        for (String sentence:this.sentences) {
            Matcher wordsMatcher = wordsPattern.matcher(sentence);
            while (wordsMatcher.find()) {
                String word = wordsMatcher.group(0);
                if(!words.contains(word)) {
                    words.add(word);
                }
            }
        }
    }

}
