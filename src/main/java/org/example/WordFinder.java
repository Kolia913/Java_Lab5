package org.example;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFinder {
    private final int wordLength;

    public WordFinder(int wordLength) {
        this.wordLength = wordLength;
    }

    public HashSet<String> findWordsInQuestions(HashSet<String> questionSentences) {

        String wordPattern = "\\b\\w{" + wordLength + "}\\b";
        Pattern wordPatternRegex = Pattern.compile(wordPattern);

        HashSet<String> foundWords = new HashSet<>();

        for (String questionSentence : questionSentences) {
            Matcher wordMatcher = wordPatternRegex.matcher(questionSentence);

            while (wordMatcher.find()) {
                foundWords.add(wordMatcher.group());
            }
        }

        return foundWords;
    }
}
