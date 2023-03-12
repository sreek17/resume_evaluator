package com.resumeevaluater.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class EmailIdExtractor {
    public  String convertToSingleLine(String input) {
        return input.replaceAll("\\s+", " ");
    }
    public  List<String> extractEmailIds(String input) {
        List<String> emailIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String emailAddress = matcher.group();
            emailIds.add(emailAddress);
        }
        return emailIds;
    }
    public  List<String> extractNames(String input) {
        List<String> names = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\s[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String name = matcher.group();
            names.add(name.toLowerCase());
        }
        return names;
    }
    public  List<String> extractPhoneNumbers(String input) {
        List<String> phoneNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\+?[0-9][-0-9()\\s]+[0-9]\\b");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String phoneNumber = matcher.group();
            phoneNumbers.add(phoneNumber);
        }
        return phoneNumbers;
    }
}