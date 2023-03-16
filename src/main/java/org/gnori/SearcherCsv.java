package org.gnori;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearcherCsv implements Searcher {

  private final int column;
  private final String delimiterRow = ",";
  private final String filePath;
  private final IndexStorage storage;
  private final boolean isNumericColumn;
  private long timeSpent;

  public SearcherCsv(int column, String filePath) {
    this.column = column;
    this.filePath = filePath;
    storage = indexColumn(filePath);
    isNumericColumn = column == 1 || (column > 6 && column < 11);
  }

  private IndexStorage indexColumn(String filePath) {
    if (column < 1 || column > 14) {
      throw new RuntimeException(ExceptionText.INVALID_COLUMN.getText());
    }
    //IndexStorage newIndexStorage = new PrefixTree(' ');
    IndexStorage newIndexStorage = new Dictionary();

    try (var bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(
        ClassLoader.getSystemResourceAsStream(filePath)), StandardCharsets.UTF_8))) {

      var row = bufferedReader.readLine();
      while (row != null) {
        var rowsArray = row.split(delimiterRow);
        newIndexStorage.insert(
            Integer.parseInt(rowsArray[0]),
            rowsArray[column - 1].replaceAll("\"", "").trim()
        );
        row = bufferedReader.readLine();
      }
      return newIndexStorage;
    } catch (IOException e) {
      throw new RuntimeException(ExceptionText.READ_FILE_ERROR.getText(), e);
    }
  }

  @Override
  public List<String> searchData(String searchTerm) {
    var startTime = System.nanoTime();
    var isNumericColumn = this.isNumericColumn;

    if (isNumericColumn) {
      isNumericColumn = checkNumeric(searchTerm);
    }

    var keys = storage.getIdsFor(searchTerm);

    List<String> outputData = new ArrayList<>();

    try (var bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(
        ClassLoader.getSystemResourceAsStream(filePath)), StandardCharsets.UTF_8))) {

      for (var key : keys) {
        boolean keyIsFound = false;
        while (!keyIsFound) {
          var row = bufferedReader.readLine();
          if (row != null) {
            var id = Integer.parseInt(row.split(delimiterRow)[0]);
            if (id == key) {
              var columnData = row.split(delimiterRow)[column - 1]
                  .replaceAll("\"", "")
                  .trim();
              outputData.add(String.format("%s [%s]", columnData, row));
              keyIsFound = true;
            }
          } else {
            keyIsFound = true;
            System.err.println(ExceptionText.ROW_NOT_PROCESSED.getText() + key);
          }
        }
      }
      if (isNumericColumn) {
        outputData.sort(
            Comparator.comparingDouble(a -> Double.parseDouble(a.substring(0, a.indexOf("["))))
        );
      } else {
        Collections.sort(outputData);
      }
      timeSpent = (System.nanoTime() - startTime) / 1_000_000;
      return outputData;
    } catch (IOException e) {
      throw new RuntimeException(ExceptionText.READ_FILE_ERROR.getText(), e);
    }
  }

  @Override
  public long getTimeSpent() {
    return timeSpent;
  }

  private boolean checkNumeric(String searchTerm) {
    try {
      Double.parseDouble(searchTerm);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
