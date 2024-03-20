package com.baltinfo.radius.bankruptcy;

import com.baltinfo.radius.utils.ImageWatermark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @author Suvorina Aleksandra
 * @since 06.09.2018
 */
public class BkrPictureParams implements IBkrPublicationBodyParams {

    private static final Logger logger = LoggerFactory.getLogger(BkrPictureParams.class);

    private Long typePublicationBody;
    private String fileName;
    private String logoFilePath;
    private byte[] pictureData;
    private BigDecimal pbNum;

    public BkrPictureParams(String fileName, String filePath, String logoFilePath, long typePublicationBody, Integer pbNum) {
        this.fileName = fileName;
        this.logoFilePath = logoFilePath;
        this.typePublicationBody = typePublicationBody;
        this.pictureData = readData(filePath);
        this.pbNum = pbNum != null ? new BigDecimal(pbNum) : null;
    }

    private byte[] readData(String filePath) {
        try (InputStream is = this.getClass().getResourceAsStream(logoFilePath)) {
            byte[] bytes = new ImageWatermark(filePath, is).add();
            return bytes;
        } catch (Exception ex) {
            logger.error("Error when getting picture data by filePath = {}", filePath, ex);
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
        return pictureData;
    }

    @Override
    public BigDecimal getPbNum() {
        return pbNum;
    }

}
