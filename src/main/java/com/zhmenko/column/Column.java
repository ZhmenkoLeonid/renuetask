package com.zhmenko.column;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Column implements Comparable<Column> {
    private long rowOffset;
    private int lineLength;
    private String value;

    @Override
    public int compareTo(Column o) {
        int cmp = this.value.toUpperCase().compareTo(o.value.toUpperCase());
        return cmp == 0 ? Long.compare(this.rowOffset, o.rowOffset) : cmp;
    }
}