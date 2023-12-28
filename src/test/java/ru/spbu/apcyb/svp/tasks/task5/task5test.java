package ru.spbu.apcyb.svp.tasks.task5;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class task5test {

  @Test
  void correctArgTest1() {
    String[] args = new String[] {"1", "2", "3"};
    assertThrows(IllegalArgumentException.class, () -> task5.main(args));
  }
  @Test
  void correctArgTest2() {
    String[] args = new String[] {"non-existent file.txt", "2"};
    assertThrows(FileNotFoundException.class, () -> task5.main(args));
  }
  @Test
  void correctArgTest3() {
    String[] args = new String[] {"..//", "2"};
    assertThrows(FileSystemException.class, () -> task5.main(args));
  }
  @Test
  void testOnSmallInputFile() throws IOException {
    String readingFile = "src/test/Task5FilesandFolders/example.txt";
    String writingFile = "src/test/Task5FilesandFolders/DirectoryForCheck";
    String[] args = {readingFile, writingFile};

    task5.main(args);

    assertTrue(Files.exists(Path.of("src/test/Task5FilesandFolders/DirectoryForCheck")));

    assertEquals(-1, Files.mismatch(
        Path.of("src/test/Task5FilesandFolders/DirectoryForCheck/count.txt"),
        Path.of("src/test/Task5FilesandFolders/count.txt")
    ));
  }
  @Test
  void bigText() {
    String readingFile = "src/test/Task5FilesandFolders/bigText.txt";
    String writingFile = "src/test/Task5FilesandFolders/DirectoryForCheckBigText";
    String[] args = {readingFile, writingFile};

    assertDoesNotThrow(() -> task5.main(args));
  }
}

