package com.resumeevaluater.util;

import org.springframework.stereotype.Component;

@Component
public class YesNoChecker {

    public  String checkForHello(String input) {
        if (input.toLowerCase().contains("yes")) {
            return "yes";
        } else if (input.toLowerCase().contains("no")) {
            return "no";
        } else {
            return "neither yes nor no";
        }
    }

    }


