package com.baltinfo.radius.bankruptcy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Suvorina Aleksandra
 * @since 07.09.2018
 */
public class BkrAdditionalDataParams {

    private static final Logger logger = LoggerFactory.getLogger(BkrAdditionalDataParams.class);

    private String fileName;
    private String note;
    private long tadUnid;
    private int indInclude;
    private int addNum;
    private BigDecimal auctionUnid;
    private BigDecimal addAddUnid;
    private long indActual;
    private long rsUnid;
    private byte[] addData;

    public BkrAdditionalDataParams(String fileName, long tadUnid, String pathToFiles) {
        this.fileName = fileName;
        this.note = null;
        this.tadUnid = tadUnid;
        this.indInclude = 0;
        this.addNum = 0;
        this.auctionUnid = null;
        this.addAddUnid = null;
        this.indActual = 1L;
        this.rsUnid = 0L;
        this.addData = readData(pathToFiles);
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

    public String getFileName() {
        return fileName;
    }

    public String getNote() {
        return note;
    }

    public long getTadUnid() {
        return tadUnid;
    }

    public int getIndInclude() {
        return indInclude;
    }

    public int getAddNum() {
        return addNum;
    }

    public BigDecimal getAuctionUnid() {
        return auctionUnid;
    }

    public BigDecimal getAddAddUnid() {
        return addAddUnid;
    }

    public long getIndActual() {
        return indActual;
    }

    public long getRsUnid() {
        return rsUnid;
    }

    public byte[] getAddData() {
        return addData;
    }
}
