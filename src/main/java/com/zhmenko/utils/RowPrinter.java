package com.zhmenko.utils;

import com.zhmenko.column.Column;
import lombok.experimental.UtilityClass;


import java.util.Collection;
import java.util.List;

@UtilityClass
public class RowPrinter {
    /**
     * Выводит строки в консоль в определённом формате
     * @param rows
     * @param columnRows
     */
    public static void printRows(List<String> rows, Collection<Column> columnRows) {
        int i = 0;
        for (Column column : columnRows) {
            System.out.printf("\"%s\"[%s]%n", column.getValue(), rows.get(i));
            i++;
        }
    }
}
