package com.zhmenko.ui;

import com.zhmenko.column.Column;
import com.zhmenko.searcher.CSVSearcher;
import com.zhmenko.utils.FileReader;
import com.zhmenko.utils.Number;
import com.zhmenko.utils.RowPrinter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class Menu {
    public final static String QUIT_PHRASE = "!quit";
    private final CSVSearcher rowSearcher;
    private final FileReader fileReader;

    public void start() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите строку (" + QUIT_PHRASE + " - для завершения работы): ");
            input = scanner.nextLine();
            if (input.equals(QUIT_PHRASE)) break;

            // Поиск строк
            long start = System.currentTimeMillis();
            SortedSet<Column> columnRows = rowSearcher.findRows(input);
            // сортировка в случае, если имеем дело с числами
            if (columnRows.size() != 0 && Number.isNumeric(columnRows.first().getValue())) {
                SortedSet<Column> columnNumericSortedRows = new TreeSet<>((o1, o2) -> {
                    Double o1Double = Double.parseDouble(o1.getValue());
                    Double o2Double = Double.parseDouble(o2.getValue());
                    int cmp = o1Double.compareTo(o2Double);
                    return cmp == 0 ? Long.compare(o1.getRowOffset(), o2.getRowOffset()) : cmp;
                });
                columnNumericSortedRows.addAll(columnRows);
                columnRows = columnNumericSortedRows;
            }
            long elapsed = System.currentTimeMillis() - start;
            // Чтение строк из файла
            List<String> rows = fileReader.readRowsByOffsetAndLength(columnRows);
            long elapsedSearchAndRead = System.currentTimeMillis() - start;
            // Вывод столбцов и соответствующих строк в консоль
            RowPrinter.printRows(rows, columnRows);
            long elapsedAtAll = System.currentTimeMillis() - start;
            String logMsg = "Количество найденных строк: " + columnRows.size()
                    + ". Время, затраченное на поиск: " + elapsed + "мс";
            System.out.println(logMsg);
            log.info(String.format("Фраза поиска: \"%s\"; %s, с чтением из файла: %sмс, с выводом в консоль: %sмс",
                    input, logMsg, elapsedSearchAndRead, elapsedAtAll));
        }
    }
}
