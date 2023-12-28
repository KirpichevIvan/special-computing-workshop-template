package ru.spbu.apcyb.svp.tasks.task3;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

public class task3 {
  public static void main(String[] args) throws IOException {
    BufferedWriter bw;
    correctArguments(args);
    Path writingfile = Files.createFile(Path.of(args[1]).toAbsolutePath());
    bw = Files.newBufferedWriter(writingfile);
    writeFilesAndFolders(args[0], 0, bw);
    bw.close();
  }

  /**
   * Checks that the correct arguments are entered
   * there must be 2 arguments, the specified directory exists
   * the file is specified and exists
   */
  public static void correctArguments(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("there should be 2 arguments");
    }
    if (!Files.exists(Path.of(args[0]))) {
      throw new FileNotFoundException("there is no such directory found");
    }
    if (new File(args[1]).isDirectory()) {
      throw new FileSystemException("the file is not indicated");
    }
    if (Files.exists(Path.of(args[1]))) {
      throw new FileAlreadyExistsException("this file already exists");
    }
  }

  /**
   * Gets all folders and files from the directory
   * and writes their names to the target file
   */
  public static void writeFilesAndFolders(String path, int i, BufferedWriter bw)
      throws IOException {
    File[] files = getfiles(path);
    for (File file : files) {
      offset(i, bw);
      if (file.isDirectory()) {
        bw.write(file.getName() + "\n");
        writeFilesAndFolders(file.getAbsolutePath(), i + 1, bw);
      } else {
        bw.write(file.getName() + "\n");
      }
    }
  }

  /**
   * Offset to structure folders and files at different levels of depth.
   */
  public static void offset(int index, BufferedWriter bw) throws IOException {
    for (int i = 0; i < index; ++i) {
      bw.write("  ");
    }
  }

  /**
   * Getting all the elements at the same depth level.
   * @return an array of files on the same level
   */
  private static File[] getfiles(String path) {
    File root = new File(path);
    return root.listFiles();
  }
}
