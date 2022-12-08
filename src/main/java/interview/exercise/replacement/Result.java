package interview.exercise.replacement;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public int count = 0;

    public Map<String, Integer> textReplacementMap = new HashMap<>();

    public void incrementLineNumber() {
        count++;
    }

    public void addReplacementCount(String replacement) {
        textReplacementMap.merge(replacement, 1, Integer::sum);
    }
}
