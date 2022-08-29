package com.zhmenko;

import com.zhmenko.column.Column;
import com.zhmenko.searcher.CSVSearcher;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.SortedSet;

public class AppTest {
    private static final String FILE_PATH = "airports.csv";
    @Test
    public void okTest() throws IOException {
        CSVSearcher csvSearcher = new CSVSearcher(FILE_PATH, 2);
        SortedSet<Column> rows = csvSearcher.findRows("Bo");
        assertEquals(68, rows.size());
    }
}
