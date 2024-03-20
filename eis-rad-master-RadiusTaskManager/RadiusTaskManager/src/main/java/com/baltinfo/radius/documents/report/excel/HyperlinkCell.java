package com.baltinfo.radius.documents.report.excel;

public class HyperlinkCell {
    private final String value;
    private final String address;

    public HyperlinkCell(String value, String address) {
        this.value = value;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }
}
