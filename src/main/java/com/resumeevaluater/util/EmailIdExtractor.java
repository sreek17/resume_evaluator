package com.resumeevaluater.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailIdExtractor {
	public String convertToSingleLine(String input) {
		return input.replaceAll("\\s+", " ");
	}

	public String extractEmailIds(String input) {
		String emailIds = "";
		Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			emailIds = matcher.group();
		}
		return emailIds;
	}

	public String extractNames(String input) {
		Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\s[a-zA-Z]+\\b");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group();

		}
		return "";
	}

	public String extractPhoneNumbers(String input) {
		Pattern pattern = Pattern.compile("\\b\\+?[0-9][-0-9()\\s]+[0-9]\\b");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			return matcher.group();
		}
		return "";
	}
}