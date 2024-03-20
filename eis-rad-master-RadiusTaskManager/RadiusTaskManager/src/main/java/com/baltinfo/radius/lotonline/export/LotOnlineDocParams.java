package com.baltinfo.radius.lotonline.export;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Suvorina Aleksandra
 * @since 22.03.2021
 */
public class LotOnlineDocParams {

    private static final String USER_DOCUMENT_NEW_STATE = "NEW";

    private final String fileName;
    private final String documentName;
    private final String hashSum;
    private final String fileExt;
    private final String state;
    private final byte[] data;

    public LotOnlineDocParams(String fileName, String documentName, String fileExt, byte[] data) {
        this.fileName = fileName;
        this.documentName = documentName;
        this.fileExt = fileExt;
        this.data = data;
        this.state = USER_DOCUMENT_NEW_STATE;
        this.hashSum = DigestUtils.md5Hex(data);
    }

    public String getFileName() {
        return fileName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getHashSum() {
        return hashSum;
    }

    public String getFileExt() {
        return fileExt;
    }

    public String getState() {
        return state;
    }

    public byte[] getData() {
        return data;
    }
}
