package interview.exercise.walking;

import interview.exercise.pattern.JavaPattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DirectoryWalking {

    public static List<Path> fileScanner(String startingPath, String fileAcceptPattern) throws IOException {
        List<Path> paths = new ArrayList<>();
        Path start = Paths.get(startingPath);
        if (fileAcceptPattern != null) {
            String javaValidPattern = JavaPattern.wildcardToRegex(fileAcceptPattern);
            try (Stream<Path> pathStream = Files.walk(start).filter(p -> (p.toFile().isFile() && p.toString().matches(javaValidPattern))).collect(Collectors.toList()).parallelStream()) {
                pathStream.forEach(p -> paths.add(p));
            }
        } else {
            try (Stream<Path> pathStream = Files.walk(start).filter(p -> (p.toFile().isFile())).collect(Collectors.toList()).parallelStream()) {
                pathStream.forEach(p -> paths.add(p));
            }
        }
        return paths;
    }
}
