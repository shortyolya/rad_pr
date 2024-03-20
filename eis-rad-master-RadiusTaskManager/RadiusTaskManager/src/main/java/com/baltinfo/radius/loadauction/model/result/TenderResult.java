package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "results")
public class TenderResult {

    @JsonProperty("bank")
    private final Bank bank;

    @JsonProperty("tenders")
    private final Tenders tenders;

    @JsonCreator
    public TenderResult(@JsonProperty("bank") Bank bank, @JsonProperty("tenders") Tenders tenders) {
        this.bank = bank;
        this.tenders = tenders;
    }

    public Bank getBank() {
        return bank;
    }

    public Tenders getTenders() {
        return tenders;
    }
}
