package interview.exercise.replacement;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class Producer implements Callable<Result> {

    private static final int BATCH_SIZE = 50_000;
    private static final int WORKERS = 2;
    ArrayBlockingQueue<List<String>> queue;
    Path path;

    public Producer(ArrayBlockingQueue<List<String>> queue, Path path) {
        this.queue = queue;
        this.path = path;
    }

    @Override
    public Result call() throws Exception {
        try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            var readLine = "";
            var lines = new ArrayList<String>(BATCH_SIZE);
            var batchIndex = 0;
            while ((readLine = reader.readLine()) != null) {
                lines.add(readLine);
                batchIndex++;
                if (batchIndex == BATCH_SIZE) {
                    queue.put(lines);
                    lines = new ArrayList<>(BATCH_SIZE);
                    batchIndex = 0;
                }
            }
            // last batch
            queue.put(lines);
            for (int i = 0; i < WORKERS; i++) {
                queue.put(List.of("poison"));
            }
            return new Result();
        }
    }
}
