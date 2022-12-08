package interview.exercise;

import interview.exercise.replacement.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RegexTextReplacementInFilesTest {

    @Test
    public void testSanity() {
        System.out.println("JUnit test works");
    }

    @Test
    public void processTestWithFileAcceptPattern() throws IOException {
        Report.resetReport();
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        RegexTextReplacementInFiles.process("sample_dir", regexPattern, replacement, "*.txt");
        Assert.assertEquals("Processed 7 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n" +
                " * landing : 10 occurence\n" +
                " * plants : 2 occurence\n" +
                " * plans : 2 occurence\n" +
                " * lands : 2 occurence\n" +
                " * Planitia : 2 occurence\n" +
                " * lander : 2 occurence\n", Report.logReport());
    }

    @Test
    public void processTest() throws IOException {
        Report.resetReport();
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        RegexTextReplacementInFiles.process("sample_dir", regexPattern, replacement, null);
        Assert.assertEquals("Processed 8 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n" +
                " * landing : 10 occurence\n" +
                " * plants : 2 occurence\n" +
                " * plans : 2 occurence\n" +
                " * lands : 2 occurence\n" +
                " * Planitia : 2 occurence\n" +
                " * lander : 2 occurence\n", Report.logReport());
    }
}
