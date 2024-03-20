package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

/**
 * <p>
 * Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class LotInfo {

    @JacksonXmlProperty(localName = "ID_lot")
    @JsonProperty("idLot")
    private final String lotId;
    @JacksonXmlProperty(localName = "Number_period")
    @JsonProperty("numberPeriod")
    private final Integer numberPeriod;
    @JacksonXmlProperty(localName = "Price_period")
    @JsonProperty("pricePeriod")
    private final BigDecimal pricePeriod;
    @JacksonXmlProperty(localName = "Deposit")
    @JsonProperty("deposit")
    private final BigDecimal deposit;
    @JacksonXmlProperty(localName = "Percentage_reduction")
    @JsonProperty("percentageReduction")
    private final BigDecimal percentageReduction;

    public LotInfo(@JacksonXmlProperty(localName = "ID_lot")
                   @JsonProperty("idLot") String lotId,
                   @JacksonXmlProperty(localName = "Number_period")
                   @JsonProperty("numberPeriod") Integer numberPeriod,
                   @JacksonXmlProperty(localName = "Price_period")
                   @JsonProperty("pricePeriod") BigDecimal pricePeriod,
                   @JacksonXmlProperty(localName = "Deposit")
                   @JsonProperty("deposit") BigDecimal deposit,
                   @JacksonXmlProperty(localName = "Percentage_reduction")
                   @JsonProperty("percentageReduction") BigDecimal percentageReduction) {
        this.lotId = lotId;
        this.numberPeriod = numberPeriod;
        this.pricePeriod = pricePeriod;
        this.deposit = deposit;
        this.percentageReduction = percentageReduction;
    }

    public String getLotId() {
        return lotId;
    }

    public Integer getNumberPeriod() {
        return numberPeriod;
    }

    public BigDecimal getPricePeriod() {
        return pricePeriod;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public BigDecimal getPercentageReduction() {
        return percentageReduction;
    }
}
