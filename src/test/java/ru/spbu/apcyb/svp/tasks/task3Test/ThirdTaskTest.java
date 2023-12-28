package ru.spbu.apcyb.svp.tasks.task3Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task3.task3;


public class ThirdTaskTest {

  @Test
  void invalidInputExceptionTest() {
    String[] args = {"..//"};
    assertThrows(IllegalArgumentException.class, () -> task3.main(args));
  }
  @Test
  void FileNotFoundExceptionTest() {
    String[] args = {"non-existent directory/", "src/test/correctAnswerTask3.txt"};
    assertThrows(FileNotFoundException.class, () -> task3.main(args));
  }
  @Test
  void FileSystemExceptionTest() {
    String[] args = {"..//", "..//"};
    assertThrows(FileSystemException.class, () -> task3.main(args));
  }
  @Test
  void FileAlreadyExistsExceptionTest() {
    String[] args = {"..//", "src/test/correctAnswerTask3.txt"};
    assertThrows(FileAlreadyExistsException.class, () -> task3.main(args));
  }
  @Test
  void genTest() throws IOException {
    String pathToDirectory = "src/test/Task3FilesandFolders/";
    Path currentFile = Path.of("src/test/currentFileForTask3.txt");
    String[] args = {pathToDirectory, currentFile.toString()};
    task3.main(args);
    boolean equals = true;
    try (BufferedReader currentReader = new BufferedReader(new FileReader(
        currentFile.toFile())); BufferedReader correctReader = new BufferedReader(
        new FileReader(Path.of("src/test/correctAnswerTask3.txt").toFile()))) {

      String cur = currentReader.readLine();
      String cor = correctReader.readLine();
      while (cur != null && cor != null) {
        if (!cor.equals(cur)) {
          equals = false;
        }
        cur = currentReader.readLine();
        cor = correctReader.readLine();
      }
      if (cur != null || cor != null) {
        equals = false;
      }
    }
    Files.delete(currentFile);
    assertTrue(equals);
  }
}
