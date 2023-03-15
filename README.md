# airport-search
1.  **Корректная обработка ошибок:**<br>
- Программа бросате RuntimeException и выводит информационное сообщение в консоль при возникновении IOException во время чтения файла, а также при указании некорректного значения столбца, по которому осуществляется поиск.<br> 
- Когда отлавливается NumberFormatException при попытке считать число (Double) из строки (String), которую ввел пользователь, программа просто изменяет параметр isNumeric, необходимый для корректной сортировки данных.
2.  **Так как CSV файл использует UTF-8:**<br>
- Все источники и .jar используют кодировку UTF-8, поэтому для корректного отображения символов в консоли необходимо использовать следующие флаги для JVM: 
>**-Xmx7m -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8** 