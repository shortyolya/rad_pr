package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class Tender {

    @JacksonXmlProperty(localName = "ID_user")
    @JsonProperty("idUser")
    private final String idUser;
    @JacksonXmlProperty(localName = "ETP")
    @JsonProperty("etp")
    private final String etp;
    @JacksonXmlProperty(localName = "Org_tender")
    @JsonProperty("orgTender")
    private final String tenderOrg;
    @JacksonXmlProperty(localName = "Type_tender")
    @JsonProperty("typeTender")
    private final String tenderType;
    @JacksonXmlProperty(localName = "Form_Tender")
    @JsonProperty("formTender")
    private final String tenderForm;
    @JacksonXmlProperty(localName = "Form_App")
    @JsonProperty("formApp")
    private final String appForm;
    @JacksonXmlProperty(localName = "Trade-ID")
    @JsonProperty("tradeID")
    private final String tradeId;
    @JacksonXmlProperty(localName = "Number_tender1")
    @JsonProperty("numberTender1")
    private final String numberTender1;
    @JacksonXmlProperty(localName = "URL_tender1")
    @JsonProperty("urltender1")
    private final String urlTender1;
    @JacksonXmlProperty(localName = "Number_tender2")
    @JsonProperty("numberTender2")
    private final String numberTender2;
    @JacksonXmlProperty(localName = "URL_tender2")
    @JsonProperty("urltender2")
    private final String urlTender2;
    @JacksonXmlProperty(localName = "Review_tender")
    @JsonProperty("reviewTender")
    private final String reviewTender;
    @JacksonXmlProperty(localName = "Phone")
    @JsonProperty("phone")
    private final String phoneTender;
    @JacksonXmlProperty(localName = "Amount_period")
    @JsonProperty("amountPeriod")
    private final Integer amountPeriod;
    @JacksonXmlProperty(localName = "Amount_lot")
    @JsonProperty("amountLot")
    private final Integer amountLot;
    @JacksonXmlProperty(localName = "Number_lots")
    @JsonProperty("numberLots")
    private final String numberLots;
    @JacksonXmlProperty(localName = "Step")
    @JsonProperty("step")
    private final BigDecimal step;
    @JacksonXmlProperty(localName = "Report")
    @JsonProperty("report")
    private final String tenderReport;
    @JacksonXmlProperty(localName = "Periods")
    @JsonProperty("periods")
    @JacksonXmlElementWrapper(localName = "Periods")
    private final List<Period> periods;
    @JacksonXmlProperty(localName = "Lots")
    @JacksonXmlElementWrapper(localName = "Lots")
    @JsonProperty("lots")
    private final List<Lot> lots;

    public Tender(@JacksonXmlProperty(localName = "ID_user")
                  @JsonProperty("idUser") String idUser,
                  @JacksonXmlProperty(localName = "ETP")
                  @JsonProperty("ETP") String etp,
                  @JacksonXmlProperty(localName = "Org_tender")
                  @JsonProperty("orgTender") String tenderOrg,
                  @JacksonXmlProperty(localName = "Type_tender")
                  @JsonProperty("typeTender") String tenderType,
                  @JacksonXmlProperty(localName = "Form_Tender")
                  @JsonProperty("formTender") String tenderForm,
                  @JacksonXmlProperty(localName = "Form_App")
                  @JsonProperty("formApp") String appForm,
                  @JacksonXmlProperty(localName = "Trade-ID")
                  @JsonProperty("tradeID") String tradeId,
                  @JacksonXmlProperty(localName = "Number_tender1")
                  @JsonProperty("numberTender1") String numberTender1,
                  @JacksonXmlProperty(localName = "URL_tender1")
                  @JsonProperty("urltender1") String urlTender1,
                  @JacksonXmlProperty(localName = "Number_tender2")
                  @JsonProperty("numberTender2") String numberTender2,
                  @JacksonXmlProperty(localName = "URL_tender2")
                  @JsonProperty("urltender2") String urlTender2,
                  @JacksonXmlProperty(localName = "Review_tender")
                  @JsonProperty("reviewTender") String reviewTender,
                  @JacksonXmlProperty(localName = "Phone")
                  @JsonProperty("phone") String phoneTender,
                  @JacksonXmlProperty(localName = "Amount_period")
                  @JsonProperty("amountPeriod") Integer amountPeriod,
                  @JacksonXmlProperty(localName = "Amount_lot")
                  @JsonProperty("amountLot") Integer amountLot,
                  @JacksonXmlProperty(localName = "Number_lots")
                  @JsonProperty("numberLots") String numberLots,
                  @JacksonXmlProperty(localName = "Step")
                  @JsonProperty("step") BigDecimal step,
                  @JacksonXmlProperty(localName = "Report")
                  @JsonProperty("report") String tenderReport,
                  @JacksonXmlProperty(localName = "Periods")
                  @JsonProperty("periods") List<Period> periods,
                  @JacksonXmlProperty(localName = "Lots")
                  @JsonProperty("lots") List<Lot> lots) {
        this.idUser = idUser;
        this.etp = etp;
        this.tenderOrg = tenderOrg;
        this.tenderType = tenderType;
        this.tenderForm = tenderForm;
        this.appForm = appForm;
        this.tradeId = tradeId;
        this.numberTender1 = numberTender1;
        this.urlTender1 = urlTender1;
        this.numberTender2 = numberTender2;
        this.urlTender2 = urlTender2;
        this.reviewTender = reviewTender;
        this.phoneTender = phoneTender;
        this.amountPeriod = amountPeriod;
        this.amountLot = amountLot;
        this.numberLots = numberLots;
        this.step = step;
        this.tenderReport = tenderReport;
        this.periods = periods;
        this.lots = lots;
    }

    public String getEtp() {
        return etp;
    }

    public String getTenderOrg() {
        return tenderOrg;
    }

    public String getTenderType() {
        return tenderType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getNumberTender1() {
        return numberTender1;
    }

    public String getUrlTender1() {
        return urlTender1;
    }

    public String getNumberTender2() {
        return numberTender2;
    }

    public String getUrlTender2() {
        return urlTender2;
    }

    public String getReviewTender() {
        return reviewTender;
    }

    public String getPhoneTender() {
        return phoneTender;
    }

    public Integer getAmountPeriod() {
        return amountPeriod;
    }

    public Integer getAmountLot() {
        return amountLot;
    }

    public String getNumberLots() {
        return numberLots;
    }

    public String getTenderReport() {
        return tenderReport;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public String getTenderForm() {
        return tenderForm;
    }

    public String getAppForm() {
        return appForm;
    }

    public String getIdUser() {
        return idUser;
    }

    public BigDecimal getStep() {
        return step;
    }
}
