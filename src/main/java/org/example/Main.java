package org.example;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст:");
        String inputText = scanner.nextLine();

        System.out.println("Введіть довжину слів, які потрібно знайти:");
        int wordLength = scanner.nextInt();
        scanner.close();

        TextAnalyzer textAnalyzer = new TextAnalyzer(inputText);
        HashSet<String> questionSentences = textAnalyzer.findQuestionSentences();

        WordFinder wordFinder = new WordFinder(wordLength);
        HashSet<String> foundWords = wordFinder.findWordsInQuestions(questionSentences);

        System.out.println("Знайдені слова заданої довжини в питальних реченнях без повторів:");
        for (String foundWord : foundWords) {
            System.out.println(foundWord);
        }

    }
}