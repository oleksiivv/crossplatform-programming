package com.EasyStreet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchMatchesInSentencesTest {
    @Test
    public void wordsSearchTest(){
        TextSearchController controller = new TextSearchController();
        controller.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "My second tt question? Sed amet elementum."
                + "Integer nec nn diam erat, eu consectetur hi nibh?"
                + "Cum sociis natoque penatibus et magnis dis parturient montes.");

        controller.search(2);
        assertEquals("My",controller.getMatches().get(0));
    }

    @Test
    public void sentencesSearchTest(){
        TextSearchController controller = new TextSearchController();
        controller.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + "My second tt question? Sed amet elementum."
                + "Integer nec nn diam erat, eu consectetur hi nibh?"
                + "Cum sociis natoque penatibus et magnis dis parturient montes.");

        controller.search(2);
        assertEquals("My second tt question?",controller.getSentences().get(0));
    }
}