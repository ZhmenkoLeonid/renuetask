package com.zhmenko.searcher;

import com.zhmenko.column.Column;
import com.zhmenko.utils.CSVParser;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CSVSearcher {
    private final TreeSet<Column> columnTreeSet;
    public CSVSearcher(String filePath, int columnNumber) throws IOException {
        List<Column> fileLines = new CSVParser().readFileColumn(filePath, columnNumber);
        columnTreeSet = new TreeSet<>(fileLines);
    }
    /**
     * Поиск строк, начинающихся на str в наборе columnTreeSet
     * @param str - строка, на которую должны начинаться искомые строки таблицы
     * @return Сортированные в лексикографическом порядке (по переменной value класса Column) набор строк, начинающиеся на str
     */
    public SortedSet<Column> findRows(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.replace(str.length() - 1, str.length(), Character.toString(str.charAt(str.length() - 1) + 1));
        String endString = stringBuilder.toString();

        return columnTreeSet.subSet(new Column(1, 1, str), new Column(1, 1, endString));
    }
}
