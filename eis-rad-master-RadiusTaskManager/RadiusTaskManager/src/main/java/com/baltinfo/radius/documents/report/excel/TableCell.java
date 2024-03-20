package com.baltinfo.radius.documents.report.excel;

/**
 * @author Suvorina Aleksandra
 * @since 11.01.2019
 */
public class TableCell<T> {

    private final T value;

    public TableCell(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Double getDoubleValue() {
        return (Double) value;
    }

    public String getStringValue() {
        return value.toString();
    }
    public HyperlinkCell getHyperlinkValue() {
        return (HyperlinkCell) value;
    }

    public Class getType() {
        return value.getClass();
    }
}
