package interview.exercise.validator;

import interview.exercise.exception.IncorrectStartingDirectoryException;
import interview.exercise.exception.InvalidRegexException;
import interview.exercise.exception.InvalidReplacementException;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class RegexTextReplacementInFilesParameterValidation {
    private  static final Logger logger = Logger.getLogger(RegexTextReplacementInFilesParameterValidation.class.getName());

    public RegexTextReplacementInFilesParameterValidation() {
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        logger.addHandler(handlerObj);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
    }

    private void startingDirectoryValidation(File file) {
        if (!(file.exists() && (file.isDirectory() || file.isFile()))) {
            logger.log(Level.SEVERE, "StartingDir is not valid.");
            throw new IncorrectStartingDirectoryException("StartingDir is not valid.");
        }
    }

    private void regexPatternValidation(String regexPattern) {
        try {
            Pattern.compile(regexPattern);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Regex pattern is not valid.");
            throw new InvalidRegexException("Regex pattern is not valid.");
        }
    }

    private void replacementValidation(String replacement) {
        if (replacement == null || replacement.isEmpty()) {
            logger.log(Level.SEVERE, "Replacement is not valid.");
            throw new InvalidReplacementException("Replacement is not valid.");
        }
    }

    public void processParametersValidation(File file, String regexPattern, String replacement) {
            startingDirectoryValidation(file);
            regexPatternValidation(regexPattern);
            replacementValidation(replacement);
            logger.log(Level.INFO, "All parameters in the process are validated.");
    }
}