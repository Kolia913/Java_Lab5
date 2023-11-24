package org.example;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
    private final String inputText;

    public TextAnalyzer(String inputText) {
        this.inputText = inputText;
    }

    public HashSet<String> findQuestionSentences() {

        String questionSentencePattern = "[A-Za-z\\s]*\\?";
        Pattern questionPattern = Pattern.compile(questionSentencePattern);

        HashSet<String> questionSentences = new HashSet<>();
        Matcher questionMatcher = questionPattern.matcher(inputText);

        while (questionMatcher.find()) {
            questionSentences.add(questionMatcher.group().trim());
        }

        return questionSentences;
    }
}