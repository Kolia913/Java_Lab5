import org.example.TextAnalyzer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextAnalyzerTest {
    private TextAnalyzer textAnalyzer;

    @Before
    public void setUp() {
        textAnalyzer = new TextAnalyzer("This is a sample text. Is it working? Yes, it is.");
    }

    @Test
    public void testFindQuestionSentences() {
        HashSet<String> questionSentences = textAnalyzer.findQuestionSentences();

        assertEquals(1, questionSentences.size());
        assertTrue(questionSentences.contains("Is it working?"));
    }

    @Test
    public void testFindQuestionSentences_SingleQuestionSentence() {
        TextAnalyzer analyzer = new TextAnalyzer("Is this a question sentence?");
        HashSet<String> result = analyzer.findQuestionSentences();

        HashSet<String> expected = new HashSet<>();
        expected.add("Is this a question sentence?");

        assertEquals(expected, result);
    }

    @Test
    public void testFindQuestionSentences_MultipleQuestionSentences() {
        TextAnalyzer analyzer = new TextAnalyzer("What is this? How are you? Why is the sky blue?");
        HashSet<String> result = analyzer.findQuestionSentences();

        HashSet<String> expected = new HashSet<>();
        expected.add("What is this?");
        expected.add("How are you?");
        expected.add("Why is the sky blue?");

        assertEquals(expected, result);
    }

    @Test
    public void testFindQuestionSentences_NoQuestionSentences() {
        TextAnalyzer analyzer = new TextAnalyzer("This is not a question. Neither is this.");
        HashSet<String> result = analyzer.findQuestionSentences();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindQuestionSentences_EmptyInput() {
        TextAnalyzer analyzer = new TextAnalyzer("");
        HashSet<String> result = analyzer.findQuestionSentences();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindQuestionSentences_WithMultipleSpaces() {
        TextAnalyzer analyzer = new TextAnalyzer("  Is   this    a   question  sentence?   ");
        HashSet<String> result = analyzer.findQuestionSentences();

        HashSet<String> expected = new HashSet<>();
        expected.add("Is   this    a   question  sentence?");

        assertEquals(expected, result);
    }

    @Test
    public void testFindQuestionSentences_WithSpecialCharacters() {
        TextAnalyzer analyzer = new TextAnalyzer("What is this?! How are you? This is not a question.");
        HashSet<String> result = analyzer.findQuestionSentences();

        HashSet<String> expected = new HashSet<>();
        expected.add("What is this?");
        expected.add("How are you?");

        assertEquals(expected, result);
    }

    @Test
    public void testFindQuestionSentences_MixedCase() {
        TextAnalyzer analyzer = new TextAnalyzer("Is this a QuEsTiOn SeNtEnCe? Why NoT? hOw AbOuT tHiS?");
        HashSet<String> result = analyzer.findQuestionSentences();

        HashSet<String> expected = new HashSet<>();
        expected.add("Is this a QuEsTiOn SeNtEnCe?");
        expected.add("Why NoT?");
        expected.add("hOw AbOuT tHiS?");

        assertEquals(expected, result);
    }

}