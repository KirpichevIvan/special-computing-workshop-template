package ru.spbu.apcyb.svp.tasks.task5;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class task5 {
  private static final Logger logger = Logger.getLogger(task5.class.getName());
  public static void main(String[] args) throws IOException {
    correctArguments(args);
    Path readingFile = Path.of(args[0]);
    Map<String, Long> wordWithCount = mapFormation(readingFile);
    Path dir = Path.of(args[1]);
    if (!Files.exists(dir)) {
      Files.createDirectory(dir);
    }
    recordingToFileCount(wordWithCount, Path.of(args[1] + "/count.txt"));

    writingWordsToFileInDir(wordWithCount, args[1] + "/FolderWithWords/");
  }

  /**
   * Checks that the correct arguments are entered
   * there must be 2 arguments, the file is specified and exists
   */
  public static void correctArguments(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("there should be 2 arguments");
    }
    if (!Files.exists(Path.of(args[0]))) {
      throw new FileNotFoundException("there is no such file found");
    }
    if (!new File(args[0]).isFile()) {
      throw new FileSystemException("the file is not indicated");
    }
  }

  /**
   * Map formation (number of occurrences of each word)
   * Reading from the source file
   */
  public static Map<String, Long> mapFormation(Path readingFile) {
    try {
      try (Stream<String> stream = Files.lines(readingFile)) {
        return stream.flatMap(line -> Arrays.stream(line.split(" ")))
            .map(word -> word.trim().replaceAll("\\p{Punct}", "")
                .toLowerCase())
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      }
    } catch (IOException e) {
      logger.info("Error map formation : " + e.getMessage());
    }
    return Collections.emptyMap();
  }

  /**
   * All information from the map is recorded in a file /count.txt
   */
  public static void recordingToFileCount(Map<String, Long> wordWithCount, Path countFile) {
    try (BufferedWriter bw = Files.newBufferedWriter(countFile)) {
      for (Entry<String, Long> entry : wordWithCount.entrySet()) {
        bw.write(entry.getKey() + " " + entry.getValue() + "\n");
      }
    } catch (IOException e) {
      logger.info("Error recording to the file count.txt: " + e.getMessage());
    }
  }

  /**
   * Using multithreading, all words are written to the directory
   * Each word in its own file with its own counter
   */
  public static void writingWordsToFileInDir(Map<String, Long> wordWithCount, String writingFile) {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    try {
      Path directory = Path.of(writingFile);
      if (!Files.exists(directory)) {
        Files.createDirectory(directory);
      }
      wordWithCount.forEach((word, count) -> CompletableFuture.runAsync(
          () -> writingWordsInTheirFiles(word, count, writingFile), executor).join());
    } catch (IOException e) {
      logger.info("Error writing to the file: " + e.getMessage());
    } finally {
        executor.shutdown();
    }
  }

  /**
   * An auxiliary function for writing words to a directory.
   * A word is written to the file with the input name
   * in the amount of the size passed to the function.
   */
  public static void writingWordsInTheirFiles(String word, Long count, String strWritingFile) {
    Path writingFile = Path.of(strWritingFile + word + ".txt");
    try (BufferedWriter bw = Files.newBufferedWriter(writingFile)) {
      for (long i = 0; i < count; i++) {
        bw.write(word + " ");
      }
    } catch (IOException e) {
      logger.info("Error when recording a word ( " + word + " ) in the file: "
          + e.getMessage());
    }
  }
}
