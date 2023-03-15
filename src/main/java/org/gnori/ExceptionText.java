package org.gnori;

public enum ExceptionText {
  INVALID_COLUMN("Значение стобца указанно некорректно. Введите значение от 1 до 14 включительно."),
  READ_FILE_ERROR("Ошибка чтения файла"),
  ROW_NOT_PROCESSED("Ошибка поиска, не обработана строка: ");
  private final String text;

  ExceptionText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

}
