package interview.exercise.replacement;

import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class Worker implements Callable<Result> {
    private static final String PROCESSED = ".processed";
    private final Pattern pattern;
    private final String replacement;
    ArrayBlockingQueue<List<String>> queue;
    FileWriter fileWriter;

    public Worker(ArrayBlockingQueue<List<String>> queue, FileWriter fileWriter, Pattern pattern, String replacement) {
        this.queue = queue;
        this.fileWriter = fileWriter;
        this.pattern = pattern;
        this.replacement = replacement;
    }

    @Override
    public Result call() throws Exception {
        Result result = new Result();
        StringBuilder totalStr = new StringBuilder();
        while (true) {
            var lines = queue.take();
            if (lines.equals(List.of("poison"))) {
                break;
            }
            for (var line : lines) {
                var matcher = pattern.matcher(line);
                while (matcher.find()) {
                    result.addReplacementCount(matcher.group(0));
                    line = (null != matcher.group(1)) ? line.replaceAll(matcher.group(1), replacement) : " ";
                }

                totalStr.append(line + "\n");
                result.incrementLineNumber();
            }
        }
        fileWriter.write(totalStr.toString());
        return result;
    }
}
