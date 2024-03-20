package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Tenders {
    @JsonProperty("tradeId")
    private final String tradeId;
    @JsonProperty("etp")
    private final String etp;
    @JsonProperty("orgTender")
    private final String orgTender;
    @JsonProperty("tender")
    private final List<Tender> tender;

    @JsonCreator
    public Tenders(@JsonProperty("tradeId") String tradeId,
                   @JsonProperty("etp") String etp,
                   @JsonProperty("orgTender") String orgTender,
                   @JsonProperty("tender") List<Tender> tender) {
        this.tradeId = tradeId;
        this.etp = etp;
        this.orgTender = orgTender;
        this.tender = tender;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getEtp() {
        return etp;
    }

    public String getOrgTender() {
        return orgTender;
    }

    public List<Tender> getTender() {
        return tender;
    }
}
