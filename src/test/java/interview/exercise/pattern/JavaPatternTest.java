package interview.exercise.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class JavaPatternTest {

    @Test
    public void wildcardToRegexTest() {
        String  test = "123ABC";
        Assert.assertTrue(Pattern.matches(JavaPattern.wildcardToRegex("1*"), test));
        Assert.assertTrue(Pattern.matches(JavaPattern.wildcardToRegex("?2*"), test));
        Assert.assertFalse(Pattern.matches(JavaPattern.wildcardToRegex("??2*"), test));
        Assert.assertTrue(Pattern.matches(JavaPattern.wildcardToRegex("*A*"), test));
        Assert.assertFalse(Pattern.matches(JavaPattern.wildcardToRegex("*Z*"), test));
        Assert.assertTrue(Pattern.matches(JavaPattern.wildcardToRegex("123*"), test));
        Assert.assertFalse(Pattern.matches(JavaPattern.wildcardToRegex("123"), test));
        Assert.assertTrue(Pattern.matches(JavaPattern.wildcardToRegex("*ABC"), test));
        Assert.assertFalse(Pattern.matches(JavaPattern.wildcardToRegex("*abc"), test));
        Assert.assertFalse(Pattern.matches(JavaPattern.wildcardToRegex("ABC*"), test));
    }
}