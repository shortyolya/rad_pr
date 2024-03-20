package com.baltinfo.radius.documents.report.excel;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 11.01.2019
 */
public class TableRow {

    private final List<TableCell> cells;

    public TableRow(List<TableCell> cells) {
        this.cells = cells;
    }

    public List<TableCell> getCells() {
        return cells;
    }
}
