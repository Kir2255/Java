package com.lab3.word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PunctuationMark {
    private static final String REGEX_MARKS_SEPARATOR = "\\s?[.!?]+\\s?";
    private String symbol;

    public PunctuationMark(String symbol) {
        this.symbol = symbol;
    }

    public static List<PunctuationMark> separatorPunctuationMark(String text) {
        List<PunctuationMark> punctuationMarks = new ArrayList<>();
        Matcher matcher = Pattern.compile(REGEX_MARKS_SEPARATOR).matcher(text);

        while (matcher.find()) punctuationMarks.add(new PunctuationMark(matcher.group(0)));

        return punctuationMarks;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
