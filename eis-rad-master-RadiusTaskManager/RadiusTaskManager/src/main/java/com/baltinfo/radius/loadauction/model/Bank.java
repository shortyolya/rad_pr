package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class Bank {
    @JacksonXmlProperty(localName = "Name_bank")
    @JsonProperty("nameBank")
    private final String bankName;
    @JacksonXmlProperty(localName = "INN")
    @JsonProperty("inn")
    private final String bankInn;
    @JacksonXmlProperty(localName = "BIK")
    @JsonProperty("bik")
    private final String bankBik;
    @JacksonXmlProperty(localName = "OGRN")
    @JsonProperty("ogrn")
    private final String bankOgrn;

    @JsonCreator
    public Bank(
            @JacksonXmlProperty(localName = "Name_bank")
            @JsonProperty("nameBank") String bankName,
            @JacksonXmlProperty(localName = "INN")
            @JsonProperty("inn") String bankInn,
            @JacksonXmlProperty(localName = "BIK")
            @JsonProperty("bik") String bankBik,
            @JacksonXmlProperty(localName = "OGRN")
            @JsonProperty("ogrn") String bankOgrn) {
        this.bankName = bankName;
        this.bankInn = bankInn;
        this.bankBik = bankBik;
        this.bankOgrn = bankOgrn;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankInn() {
        return bankInn;
    }

    public String getBankBik() {
        return bankBik;
    }

    public String getBankOgrn() {
        return bankOgrn;
    }
}
