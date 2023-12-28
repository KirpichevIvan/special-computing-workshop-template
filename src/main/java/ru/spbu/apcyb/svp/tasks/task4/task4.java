package ru.spbu.apcyb.svp.tasks.task4;

import static java.lang.Double.parseDouble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class task4 {

  private static final Logger logger = Logger.getLogger(task4.class.getName());
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    String sNum = "FileOfNumbers.txt";
    String sTan = "FileOfTangent.txt";
    int count = 10000;
    writingNumbersInFile(sNum, count);
    singleStream(sNum, sTan, count);
    multiStream(sNum, sTan, count,  10);
  }
  /**
   * Writes random numbers from 0 to 1 in the count size to the file
   */
  public static void writingNumbersInFile(String fileName, int count) throws IOException {
    try(FileWriter fw = new FileWriter(fileName)) {
      SecureRandom random = new SecureRandom();
      for(int i = 0; i < count; i++){
        fw.write(Float.toString(random.nextFloat()) + " ");
      }
      fw.flush();
    }
  }
  /**
   * Calculates and writes tangents to a file fileOfTan for numbers from a file fileOfNum
   * in single-threaded mode, returns false if the recording fails and true
   * if recording is completed, count - the amount of numbers in the fileOfNum file
   */
  public static boolean singleStream(String fileOfNum, String fileOfTan, int count)
      throws IOException {
    long time;
    try (FileReader fr = new FileReader(fileOfNum)) {
      try (FileWriter fw = new FileWriter(fileOfTan, false)) {
        fw.write("The count of calculated numbers: " + count + "\n");

        BufferedReader br = new BufferedReader(fr);
        String sb =  br.readLine();
        String[] str = sb.split(" ");

        time = System.nanoTime();
        for (String s : str) {
          fw.write(Math.tan(parseDouble(s)) + " ");
        }
        fw.flush();
      }
    }
    logger.info("Single stream run for " + (System.nanoTime() - time) +
        " nanoseconds, count : " + count);
    return true;
  }
  /**
   * Calculates and writes tangents to a file fileOfTan for numbers from a file fileOfNum
   * in multithreaded mode, returns false if the recording fails and true
   * if recording is completed, count - the amount of numbers in the fileOfNum file
   * streams - number of threads
   */
  public static boolean multiStream(String fileOfNum, String fileOfTan,
      int count, int streams) throws IOException, ExecutionException, InterruptedException {
    try (FileReader fr = new FileReader(fileOfNum)) {
      try (FileWriter fw = new FileWriter(fileOfTan, false)) {
        fw.write("The count of calculated numbers: " + count + "\n");
        BufferedReader br = new BufferedReader(fr);
        String sb =  br.readLine();
        String[] str = sb.split(" ");
        ExecutorService executor = null;
        try {
          executor = Executors.newFixedThreadPool(streams);
          long time = System.nanoTime();

          List<CompletableFuture<Double>> futures = new ArrayList<>();
          for (String s : str) {
            futures.add(CompletableFuture.supplyAsync(() -> Math.tan(parseDouble(s)), executor));
          }
          CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
          for (Future<Double> future : futures) {
            fw.write(future.get() + " ");
          }
          fw.flush();
          logger.info("Multi stream run for " + (System.nanoTime() - time) +
              " nanoseconds, count : " + count);
        } finally {
          if (executor != null){
            executor.shutdown();
          }
        }
      }
    }
    return true;
  }
}
