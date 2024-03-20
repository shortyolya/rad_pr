package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
@JacksonXmlRootElement
@JsonSerialize
@JsonRootName(value = "publication")
public class Publication {
    @JacksonXmlProperty(localName = "Bank")
    @JsonProperty("Bank")
    private final Bank bank;
    @JacksonXmlProperty(localName = "Tenders")
    @JsonProperty("Tenders")
    private final TenderFull tenderFulls;

    @JsonCreator
    public Publication(
            @JacksonXmlProperty(localName = "Bank")
            @JsonProperty("Bank") Bank bank,
            @JacksonXmlProperty(localName = "Tenders")
            @JsonProperty("Tenders") TenderFull tenderFulls) {
        this.bank = bank;
        this.tenderFulls = tenderFulls;
    }

    public Bank getBank() {
        return bank;
    }

    public TenderFull getTenderFulls() {
        return tenderFulls;
    }
}
