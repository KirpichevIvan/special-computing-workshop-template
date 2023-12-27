package ru.spbu.apcyb.svp.tasks.bankomat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main class.
 */
public class bankomat {

  public static final Logger logger =
      Logger.getLogger(ru.spbu.apcyb.svp.tasks.bankomat.bankomat.class.getName());


  /**
   * Here start point of the program.
   */
  public static void main(String[] args) {

    logger.info("Input the sum you want to change:");
    final long sum = getSum();

    logger.info("Input the available banknotes for exchange:");
    final List<Long> banknotes = getBanknotes();

    wrappingfindCombinations(sum, banknotes);
  }

  /**
   * Processing the entered sum.
   */
  public static long getSum() {
    Scanner scanner = new Scanner(System.in);
    long s;

    try{
      s = scanner.nextLong();
      scanner.nextLine();
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Not only numbers were put in");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("The sum has not been input");
    }
    if (s <= 0) {
      throw new ArithmeticException("The sum is less than zero or equal to zero!!!");
    }
    return s;
  }

  /**
   * Processing of the entered banknote denominations.
   * Presenting them as a sorted list .
   */
  public static List<Long> getBanknotes() {
    Scanner scanner = new Scanner(System.in);
    String strofBanknotes;

    try {
      strofBanknotes = scanner.nextLine();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("The banknotes has not been input");
    }
    if (Objects.equals(strofBanknotes, "")) {
      throw new NullPointerException("The banknotes has not been input");
    } else {
      List<Long> listofBanknotes;
      try {
        listofBanknotes = Arrays.stream(strofBanknotes.trim().split(" "))
            .map(Long::parseLong).sorted(Comparator.reverseOrder()).toList();
      } catch (NumberFormatException e) {
        throw new NumberFormatException("Incorrectly entered banknotes");
      }

      for (Long banknotes : listofBanknotes) {
        if (banknotes <= 0) {
          throw new NumberFormatException("The banknotes is less than zero or equal to zero!!!");

        }
      }

      return listofBanknotes;
    }

  }

  /**
   * Wrapping function for the function findCombinations.
   */
  public static List<List<Long>> wrappingfindCombinations(long sum, List<Long> banknotes) {
    List<Long> currentCombination = new ArrayList<>();
    List<List<Long>> combinations = new ArrayList<>();

    long maxnbanknotes = banknotes.get(0);
    findCombinations(sum, maxnbanknotes, banknotes, currentCombination, combinations);
    logger.log(Level.INFO, "There are only {0} size combinations", combinations.size());
    return combinations;
  }

  /**
   * A method that counts the number of banknote combinations.
   */
  public static void findCombinations(long sum, long maxnbanknotes, List<Long> banknotes,
      List<Long> currentCombination, List<List<Long>> combinations) {

    if (sum == 0) {
      logger.log(Level.INFO, currentCombination.toString());
      combinations.add(new ArrayList<>(currentCombination));
      return;
    }

    long currentDenomination;
    int i;
    for (i = 0; i < banknotes.size(); i++) {
      currentDenomination = banknotes.get(i);
      if ((sum >= currentDenomination) && (maxnbanknotes >= currentDenomination)) {
        currentCombination.add(currentDenomination);
        findCombinations(sum - currentDenomination, currentDenomination, banknotes,
            currentCombination, combinations);
        currentCombination.remove(currentCombination.size() - 1);
      }
    }
  }
}
