package com.appxemphim.Utils;

public class CapitalizeWords {
    public static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder capitalizedString = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                capitalizedString.append(capitalizedWord).append(" ");
            }
        }

        return capitalizedString.toString().trim();
    }
}
