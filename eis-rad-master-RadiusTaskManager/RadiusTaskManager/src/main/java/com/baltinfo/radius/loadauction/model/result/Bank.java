package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bank {
    @JsonProperty("nameBank")
    private final String bankName;
    @JsonProperty("inn")
    private final String bankInn;
    @JsonProperty("ogrn")
    private final String bankOgrn;

    @JsonCreator
    public Bank(
            @JsonProperty("nameBank") String bankName,
            @JsonProperty("inn") String bankInn,
            @JsonProperty("ogrn") String bankOgrn) {
        this.bankName = bankName;
        this.bankInn = bankInn;
        this.bankOgrn = bankOgrn;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankInn() {
        return bankInn;
    }

    public String getBankOgrn() {
        return bankOgrn;
    }
}
