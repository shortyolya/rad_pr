package com.baltinfo.radius.db.dto;

/**
 * @author Suvorina Aleksandra
 * @since 12.10.2021
 */
public class LoadAuctionDto {

    private final Long lstUnid;
    private final Long lsUnid;
    private final Long tpaUnid;
    private final Integer laStageNum;
    private final String laAuctionName;
    private final AuctionDto auctionDto;

    public LoadAuctionDto(Long lstUnid, Long lsUnid, Long tpaUnid, Integer laStageNum, String laAuctionName, AuctionDto auctionDto) {
        this.lstUnid = lstUnid;
        this.lsUnid = lsUnid;
        this.tpaUnid = tpaUnid;
        this.laStageNum = laStageNum;
        this.laAuctionName = laAuctionName;
        this.auctionDto = auctionDto;
    }

    public Long getLstUnid() {
        return lstUnid;
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public Integer getLaStageNum() {
        return laStageNum;
    }

    public String getLaAuctionName() {
        return laAuctionName;
    }

    public AuctionDto getAuctionDto() {
        return auctionDto;
    }
}
