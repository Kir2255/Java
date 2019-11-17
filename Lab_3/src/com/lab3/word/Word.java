package com.lab3.word;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    private static final  String REGEX_WORD = "[A-Za-z]+";

    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static List<Word> separatorText(String someText) {
        if (someText.isEmpty()) throw new NullPointerException();

        List<Word> words = new ArrayList<>();
        Matcher matcher = Pattern.compile(REGEX_WORD).matcher(someText);

        while (matcher.find()) {
            words.add(new Word(matcher.group(0)));
        }

        return words;
    }

    public static void sorting(List<Word> words){
        List<String> temp = new ArrayList<>();
        for(Word word : words){
            temp.add(word.getWord());
        }

        Collections.sort(temp);

        String letter = temp.get(0).substring(0, 1);
        boolean redline = true;

        for (String word : temp) {
            if(!word.substring(0, 1).equals(letter)){
                redline = true;
                letter = word.substring(0, 1);
            }
            if(redline){
                System.out.println("\t" + word);
            } else {
                System.out.println(word);
            }
            redline = false;
        }
    }
}
