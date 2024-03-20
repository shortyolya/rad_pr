package com.baltinfo.radius.lotonline.export;

/**
 * @author Suvorina Aleksandra
 * @since 19.03.2021
 */
public class LotOnlinePictureParams {

    private final String eisFilePath;
    private final String fileName;
    private final String fileExt;
    private final byte[] data;

    public LotOnlinePictureParams(String eisFilePath, String fileName, String fileExt, byte[] data) {
        this.eisFilePath = eisFilePath;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileExt() {
        return fileExt;
    }

    public String getEisFilePath() {
        return eisFilePath;
    }
}
