# airport-search
1.  **Корректная обработка ошибок (Correct error handling):**<br>
- Программа бросате RuntimeException и выводит информационное сообщение в консоль при возникновении IOException во время чтения файла, а также при указании некорректного значения столбца, по которому осуществляется поиск. <br>( The program throws a RuntimeException and displays an informational message to the console when an IOException occurs while reading a file, and also when an incorrect value is specified for the column being searched. )<br> 
- Когда отлавливается NumberFormatException при попытке считать число (Double) из строки (String), которую ввел пользователь, программа просто изменяет параметр isNumeric, необходимый для корректной сортировки данных.<br>
( When a NumberFormatException is caught when trying to read a number (Double) from a string (String) entered by the user, the program simply changes the isNumeric parameter, which is necessary to sort the data correctly. )
2.  **Так как CSV файл использует UTF-8(Since the CSV file uses UTF-8):**<br>
- Все источники и .jar используют кодировку UTF-8, поэтому для корректного отображения символов в консоли необходимо использовать следующие флаги для JVM:<br>
( All sources and .jar use UTF-8 encoding, so the following flags for the JVM must be used to display characters correctly in the console: )
>**-Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8** 
## Описание(description):
Консольная программа, которая ищет данные по CSV файлу, ячейки которых начинаются на переданную в консоли строку. Столбец по-которому осуществляется поиск передается единственным параметром при запуске .jar.(
A console program that searches for data in a CSV file whose cells begin with a string passed to the console. The column to search by is passed as the only parameter when the .jar is run.
)
