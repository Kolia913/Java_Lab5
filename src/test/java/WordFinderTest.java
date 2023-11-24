import org.example.TextAnalyzer;
import org.example.WordFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordFinderTest {
    private WordFinder wordFinder;
    private TextAnalyzer textAnalyzer;

    @Before
    public void setUp() {
        textAnalyzer = new TextAnalyzer("This is a sample text. Is it working? Yes, it is.");
        wordFinder = new WordFinder(2);
    }

    @Test
    public void testFindWords() {
        HashSet<String> questionSentences = textAnalyzer.findQuestionSentences();

        HashSet<String> foundWords = wordFinder.findWordsInQuestions(questionSentences);

        assertEquals(2, foundWords.size());
        assertTrue(foundWords.contains("Is"));
        assertTrue(foundWords.contains("it"));
    }

    @Test
    public void testFindWordsInQuestions_SingleWordMatch() {
        HashSet<String> questionSentences = new HashSet<>();
        questionSentences.add("What is your name?");
        questionSentences.add("How old are you?");

        WordFinder wordFinder = new WordFinder(2);
        HashSet<String> result = wordFinder.findWordsInQuestions(questionSentences);

        HashSet<String> expected = new HashSet<>();
        expected.add("is");

        assertEquals(expected, result);
    }

    @Test
    public void testFindWordsInQuestions_MultipleWordMatches() {
        HashSet<String> questionSentences = new HashSet<>();
        questionSentences.add("What is your name?");
        questionSentences.add("How old are you?");
        questionSentences.add("Where do you live?");

        WordFinder wordFinder = new WordFinder(3);
        HashSet<String> result = wordFinder.findWordsInQuestions(questionSentences);

        HashSet<String> expected = new HashSet<>();
        expected.add("How");
        expected.add("are");
        expected.add("old");
        expected.add("you");

        assertEquals(expected, result);
    }

    @Test
    public void testFindWordsInQuestions_NoWordMatches() {
        HashSet<String> questionSentences = new HashSet<>();
        questionSentences.add("Who is she?");
        questionSentences.add("Where am I?");

        WordFinder wordFinder = new WordFinder(6);
        HashSet<String> result = wordFinder.findWordsInQuestions(questionSentences);

        assertTrue(result.isEmpty());
    }

}