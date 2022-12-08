package interview.exercise.replacement;

import interview.exercise.exception.RegexTextReplacementInFilesException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class FileTextReplacement {
    private static final ArrayBlockingQueue<List<String>> queue = new ArrayBlockingQueue<>(10);
    private static final int WORKERS = 2;

    private static void totals(List<Result> results) {
        Map<String, Integer> wordSearchMap = results
                .stream()
                .flatMap(r -> r.textReplacementMap.entrySet().stream())
                .collect(Collectors.groupingBy(e -> e.getKey(), Collectors.summingInt(e -> e.getValue())));

        wordSearchMap.forEach((key, value) -> Report.textReplacementReport.merge(key, value, Integer::sum));
        Report.processedFiles++;
    }

    public static void textReplacementInFile(Path path, String regex, String replacement) {
        var executor = Executors.newCachedThreadPool();
        var futures = new ArrayList<Future<Result>>();
        var producer = executor.submit(new Producer(queue, path));
        Pattern regexPattern = Pattern.compile(regex);
        futures.add(producer);
        File processedFile = new File(path.toFile() + ".processed");
        if (processedFile.exists()) {
            processedFile.delete();
        }
        try (FileWriter fileWriter = new FileWriter(processedFile)) {
            for (int i = 0; i < WORKERS; i++) {
                var worker = executor.submit(new Worker(queue, fileWriter, regexPattern, replacement));
                futures.add(worker);
            }
            List<Result> results = new ArrayList<>();
            for (var future : futures) {
                Result result = null;
                try {
                    result = future.get();
                } catch (InterruptedException e) {
                    throw new RegexTextReplacementInFilesException(e.getMessage());
                } catch (ExecutionException e) {
                    throw new RegexTextReplacementInFilesException(e.getMessage());
                }
                results.add(result);
            }
            totals(results);
        } catch (IOException e) {
            throw new RegexTextReplacementInFilesException(e.getMessage());
        }
        executor.shutdown();
    }
}
