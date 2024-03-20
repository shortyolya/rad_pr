package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class TenderFull {
    @JacksonXmlProperty(localName = "Type_tender")
    @JsonProperty("typeTender")
    private final String tenderType;
    @JacksonXmlProperty(localName = "Trade-ID")
    @JsonProperty("tradeId")
    private final String tradeId;
    @JacksonXmlProperty(localName = "Number_order")
    @JsonProperty("numberOrder")
    private final String numberOrder;
    @JacksonXmlProperty(localName = "Tender")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("tender")
    private final List<Tender> tenders;

    public TenderFull(@JacksonXmlProperty(localName = "Type_tender")
                      @JsonProperty("typeTender") String tenderType,
                      @JacksonXmlProperty(localName = "Trade-ID")
                      @JsonProperty("tradeId") String tradeId,
                      @JacksonXmlProperty(localName = "Number_order")
                      @JsonProperty("numberOrder")
                              String numberOrder,
                      @JacksonXmlProperty(localName = "Tender")
                      @JsonProperty("tender") List<Tender> tenders) {
        this.tenderType = tenderType;
        this.tradeId = tradeId;
        this.numberOrder = numberOrder;
        this.tenders = tenders;
    }

    public String getTenderType() {
        return tenderType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public List<Tender> getTenders() {
        return tenders;
    }
}
