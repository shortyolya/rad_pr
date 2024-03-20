package com.baltinfo.radius.bankruptcy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Suvorina Aleksandra
 * @since 14.10.2019
 */
public class BkrPublicationBodyParams implements IBkrPublicationBodyParams {

    private static final Logger logger = LoggerFactory.getLogger(BkrPublicationBodyParams.class);

    private Long typePublicationBody;
    private String fileName;
    private byte[] fileBody;

    public BkrPublicationBodyParams(String fileName, String filePath, long typePublicationBody) {
        this.fileName = fileName;
        this.typePublicationBody = typePublicationBody;
        this.fileBody = readData(filePath);
    }

    public BkrPublicationBodyParams(String fileName, byte[] fileBody, long typePublicationBody) {
        this.fileName = fileName;
        this.typePublicationBody = typePublicationBody;
        this.fileBody = fileBody;
    }

    private byte[] readData(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return bytes;
        } catch (Exception ex) {
            logger.error("Error when getting document data by filePath = {}", filePath, ex);
        }
        return null;
    }

    @Override
    public Long getTypePublicationBody() {
        return typePublicationBody;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public byte[] getFileBody() {
        return fileBody;
    }

    @Override
    public BigDecimal getPbNum() {
        return null;
    }
}
