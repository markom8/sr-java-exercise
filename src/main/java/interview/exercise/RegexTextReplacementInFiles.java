package interview.exercise;

import interview.exercise.exception.RegexTextReplacementInFilesException;
import interview.exercise.replacement.FileTextReplacement;
import interview.exercise.replacement.Report;
import interview.exercise.validator.RegexTextReplacementInFilesParameterValidation;
import interview.exercise.walking.DirectoryWalking;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RegexTextReplacementInFiles {
    private static final RegexTextReplacementInFilesParameterValidation regexTextReplacementInFilesParameterValidation = new RegexTextReplacementInFilesParameterValidation();

    public static void process(String startingPath, String regexPattern,
                               String replacement, String fileAcceptPattern) {

        File file = new File(startingPath);
        regexTextReplacementInFilesParameterValidation.processParametersValidation(file, regexPattern, replacement);
        try {
            List<Path> pathListProcessed = DirectoryWalking.fileScanner("sample_dir", "*.processed");
            pathListProcessed.forEach(path -> path.toFile().delete());
            List<Path> pathList = DirectoryWalking.fileScanner(startingPath, fileAcceptPattern);
            Report.replaced = replacement;
            pathList.forEach(p -> {
                try {
                    FileTextReplacement.textReplacementInFile(p, regexPattern, replacement);
                } catch (Exception e) {
                    throw new RegexTextReplacementInFilesException(e.getMessage());
                }
            });

        } catch (IOException e) {
            throw new RegexTextReplacementInFilesException(e.getMessage());
        }
        Report.logReport();
    }

    public static void main(String[] args) {
        String startingDir = null, regexPattern = null, replacement = null, fileAcceptPattern = null;
        if (args.length >= 3) {
            startingDir = args[0];
            regexPattern = args[1];
            replacement = args[2];
        }
        if (args.length >= 4) {
            fileAcceptPattern = args[3];
        }
        if (startingDir != null) {
            process(startingDir, regexPattern, replacement, fileAcceptPattern);
        } else {
            System.out.println("Expected at least 3 parameters but got " + args.length);
        }
    }

}
