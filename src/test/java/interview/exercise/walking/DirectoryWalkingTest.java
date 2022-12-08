package interview.exercise.walking;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class DirectoryWalkingTest {

    @Test
    public void testDirectoryWalkingFileScanner_FileAcceptPatternNull() throws IOException {
        List<Path> pathList = DirectoryWalking.fileScanner("sample_dir", null);
        Assert.assertEquals(pathList.size(),8);
    }

    @Test
    public void testDirectoryWalkingFileScanner() throws IOException {
        List<Path> pathList = DirectoryWalking.fileScanner("sample_dir", "*.txt");
        Assert.assertEquals(pathList.size(),7);
    }

}