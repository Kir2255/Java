package com.lab3.parser;

import java.util.regex.Pattern;

public class DateParser {
    private static final String expression = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";

    public static boolean isCorrectDate(String string){
        return Pattern.matches(expression, string);
    }
}
