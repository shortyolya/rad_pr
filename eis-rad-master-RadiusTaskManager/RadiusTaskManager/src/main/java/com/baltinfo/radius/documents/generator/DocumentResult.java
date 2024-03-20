package com.baltinfo.radius.documents.generator;

import java.util.List;

public class DocumentResult {

    private byte[] buf;
    private List<String> invalidObjectList;

    public DocumentResult(byte[] buf, List<String> invalidObjectList) {
        this.buf = buf;
        this.invalidObjectList = invalidObjectList;
    }

    public DocumentResult(byte[] buf) {
        this.buf = buf;
    }

    public byte[] getBuf() {
        return buf;
    }

    public List<String> getInvalidObjectList() {
        return invalidObjectList;
    }
}
