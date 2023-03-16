package org.gnori;

import java.util.Scanner;

public class Application {

  private final static String END_COMMAND = "!quit";
  private final static String FILE_PATH = "airports.csv";

  public static void run(int column) {
    Searcher reader = new SearcherCsv(column, FILE_PATH);
    var scanner = new Scanner(System.in);
    Printer.printReadyToSearchText();

    while (true) {
      var input = scanner.nextLine().trim();
      if (END_COMMAND.equals(input)) {
        break;
      } else if (!input.isEmpty()) {
        var foundData = reader.searchData(input);
        Printer.printFoundData(foundData, reader.getTimeSpent());
        Printer.printReadyToSearchText();
      } else {
        Printer.printReadyToSearchText();
      }
    }
    scanner.close();
    Printer.printEndText();
  }

}
