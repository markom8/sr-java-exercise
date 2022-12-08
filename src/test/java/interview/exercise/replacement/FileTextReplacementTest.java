package interview.exercise.replacement;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTextReplacementTest {

    @Test
    public void textReplacementInFile_folder1_sample1() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-sample1.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Report.replaced = replacement;
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n", Report.logReport());
    }

    @Test
    public void textReplacementInFile_folder1sample2() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-sample2.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        Report.replaced = replacement;
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n" +
                " * landing : 5 occurence\n" +
                " * plants : 1 occurence\n" +
                " * plans : 1 occurence\n" +
                " * lands : 1 occurence\n" +
                " * Planitia : 1 occurence\n" +
                " * lander : 1 occurence\n", Report.logReport());
    }

    @Test
    public void textReplacementInFile_folder1_sample1_1() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-1/folder1-1-sample1.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Report.replaced = replacement;
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n", Report.logReport());
    }

    @Test
    public void textReplacementInFile_folder1sample1_2() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-1/folder1-1-sample2.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Report.replaced = replacement;
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n" +
                " * landing : 5 occurence\n" +
                " * plants : 1 occurence\n" +
                " * plans : 1 occurence\n" +
                " * lands : 1 occurence\n" +
                " * Planitia : 1 occurence\n" +
                " * lander : 1 occurence\n", Report.logReport());
    }

    @Test
    public void textReplacementInFile_folder1sample1_3() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-1/folder1-1-sample3.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Report.replaced = replacement;
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n", Report.logReport());
    }

    @Test
    public void textReplacementInFile_folder1sample1_4() throws Exception {
        Report.resetReport();
        Path path = Paths.get("sample_dir/folder1/folder1-1/folder1-1-sample4.txt");
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        FileTextReplacement.textReplacementInFile(path, regexPattern, replacement);
        Report.replaced = replacement;
        Assert.assertEquals("Processed 1 files\n" +
                "\n" +
                "Replaced to '<-replaced->':\n", Report.logReport());
    }
}