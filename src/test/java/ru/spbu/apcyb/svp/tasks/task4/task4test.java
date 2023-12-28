package ru.spbu.apcyb.svp.tasks.task4;

import static ru.spbu.apcyb.svp.tasks.task4.task4.multiStream;
import static ru.spbu.apcyb.svp.tasks.task4.task4.singleStream;
import static ru.spbu.apcyb.svp.tasks.task4.task4.writingNumbersInFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class task4test {
  @Test
  void oneNum() throws IOException, ExecutionException, InterruptedException {
    String sNum = "FileOfNumbers.txt";
    String sTan = "FileOfTangent.txt";
    writingNumbersInFile(sNum, 1);
    boolean sstream = singleStream(sNum, sTan, 1);
    boolean mstream = multiStream(sNum, sTan, 1, 10);
    Assertions.assertTrue(sstream);
    Assertions.assertTrue(mstream);
  }
  @Test
  void oneHunNum() throws IOException, ExecutionException, InterruptedException {
    int count = 100;
    String sNum = "FileOfNumbers.txt";
    String sTan = "FileOfTangent.txt";
    writingNumbersInFile(sNum, count);
    boolean sstream = singleStream(sNum, sTan, count);
    boolean mstream = multiStream(sNum, sTan, count, 10);
    Assertions.assertTrue(sstream);
    Assertions.assertTrue(mstream);
  }
  @Test
  void oneMilNum() throws IOException, ExecutionException, InterruptedException {
    int count = 1000000;
    String sNum = "FileOfNumbers.txt";
    String sTan = "FileOfTangent.txt";
    writingNumbersInFile(sNum, count);
    boolean sstream = singleStream(sNum, sTan, count);
    boolean mstream = multiStream(sNum, sTan, count, 10);
    Assertions.assertTrue(sstream);
    Assertions.assertTrue(mstream);
  }
}
