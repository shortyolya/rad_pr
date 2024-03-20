package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Tender {

    @JsonProperty("tradeId")
    private final String tradeId;
    @JsonProperty("typeTender")
    private final String typeTender;
    @JsonProperty("torgResult")
    private final Integer torgResult;
    @JsonProperty("lots")
    private final List<Lot> lots;

    @JsonCreator
    public Tender(@JsonProperty("tradeId") String tradeId,
                  @JsonProperty("typeTender") String typeTender,
                  @JsonProperty("torgResult") Integer torgResult,
                  @JsonProperty("lots") List<Lot> lots) {
        this.tradeId = tradeId;
        this.typeTender = typeTender;
        this.torgResult = torgResult;
        this.lots = lots;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getTypeTender() {
        return typeTender;
    }

    public Integer getTorgResult() {
        return torgResult;
    }

    public List<Lot> getLots() {
        return lots;
    }
}
