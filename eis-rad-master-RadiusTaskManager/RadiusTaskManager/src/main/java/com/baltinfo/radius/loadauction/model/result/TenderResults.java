package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TenderResults {

    @JsonProperty("results")
    private final TenderResult tenderResult;

    @JsonCreator
    public TenderResults(TenderResult tenderResult) {
        this.tenderResult = tenderResult;
    }

    public TenderResult getTenderResult() {
        return tenderResult;
    }
}
