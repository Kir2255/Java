package com.lab3.parser;

import java.util.regex.Pattern;

public class DateParser {
    private static final String expression = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)(((1)[6-9](\\d{2}))|([2-9](\\d{3})))$";

    public static boolean isCorrectDate(String string){
        return Pattern.matches(expression, string);
    }
}
