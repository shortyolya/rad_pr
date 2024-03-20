package com.baltinfo.radius.db.dto;

/**
 * @author Suvorina Aleksandra
 * @since 13.10.2021
 */
public class BlockAuctionDto {

    private final String baName;
    private final String baAsvId;
    private final Boolean baIndBlock;
    private final Long templateDebitorPropertyUnid;

    public BlockAuctionDto(String baName, String baAsvId, Boolean baIndBlock, Long templateDebitorPropertyUnid) {
        this.baName = baName;
        this.baAsvId = baAsvId;
        this.baIndBlock = baIndBlock;
        this.templateDebitorPropertyUnid = templateDebitorPropertyUnid;
    }

    public String getBaName() {
        return baName;
    }

    public String getBaAsvId() {
        return baAsvId;
    }

    public Boolean getBaIndBlock() {
        return baIndBlock;
    }

    public Long getTemplateDebitorPropertyUnid() {
        return templateDebitorPropertyUnid;
    }
}
