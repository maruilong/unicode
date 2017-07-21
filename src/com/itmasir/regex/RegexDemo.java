package com.itmasir.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    private static final String TEXT = "Tommy%^TANG23@126_me";
    //private static final String TEST1 = "d1_";
    private static final String REGEX = "(^\\w+){0,15}";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(TEXT);
        if (matcher.matches()) {
            System.out.println(matcher.group());
        } else {
            System.out.println("字符串:" + TEXT + "不满足表达式:" + REGEX);
        }
    }
}
