package org.gnori;

import java.util.List;

public class Printer {

  private final static String PATTERN = "\nКоличество найденных строк: %d, затрачено на поиск: %d мс.\n";
  private final static String PATTERN_FOR_ABSENT_DATA = "\nДанных по запросу не найдено.\n";
  private final static String READY_TO_SEARCH_TEXT = "Введите текст для поиска: ";
  private final static String PROGRAM_CLOSURE_TEXT = "\nПрограмма завершена.";

  public static void printFoundData(List<String> fountData, long timeSpent) {
    if (fountData.size() > 0) {
      fountData.forEach(System.out::println);
      System.out.printf(PATTERN, fountData.size(), timeSpent);
    } else {
      System.out.println(PATTERN_FOR_ABSENT_DATA);
    }
  }

  public static void printEndText() {
    System.out.println(PROGRAM_CLOSURE_TEXT);
  }

  public static void printReadyToSearchText() {
    System.out.println(READY_TO_SEARCH_TEXT);
  }

}
