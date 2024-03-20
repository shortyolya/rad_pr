package com.baltinfo.radius.fias.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.Objects;

public class FiasInfoFile {

    @JsonProperty("VersionId")
    private Long versionId;

    @JsonProperty("TextVersion")
    private String textVersion;

    @JsonProperty("FiasCompleteDbfUrl")
    private String fiasCompleteDbfUrl;

    @JsonProperty("FiasCompleteXmlUrl")
    private String fiasCompleteXmlUrl;

    @JsonProperty("FiasDeltaDbfUrl")
    private String fiasDeltaDbfUrl;

    @JsonProperty("FiasDeltaXmlUrl")
    private String fiasDeltaXmlUrl;

    @JsonProperty("Kladr4ArjUrl")
    private String kladr4ArjUrl;

    @JsonProperty("Kladr47ZUrl")
    private String kladr47ZUrl;

    @JsonProperty("GarXMLFullURL")
    private String garXMLFullURL;

    @JsonProperty("GarXMLDeltaURL")
    private String garXMLDeltaURL;

    @JsonProperty("Date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getTextVersion() {
        return textVersion;
    }

    public void setTextVersion(String textVersion) {
        this.textVersion = textVersion;
    }

    public String getFiasCompleteDbfUrl() {
        return fiasCompleteDbfUrl;
    }

    public void setFiasCompleteDbfUrl(String fiasCompleteDbfUrl) {
        this.fiasCompleteDbfUrl = fiasCompleteDbfUrl;
    }

    public String getFiasCompleteXmlUrl() {
        return fiasCompleteXmlUrl;
    }

    public void setFiasCompleteXmlUrl(String fiasCompleteXmlUrl) {
        this.fiasCompleteXmlUrl = fiasCompleteXmlUrl;
    }

    public String getFiasDeltaDbfUrl() {
        return fiasDeltaDbfUrl;
    }

    public void setFiasDeltaDbfUrl(String fiasDeltaDbfUrl) {
        this.fiasDeltaDbfUrl = fiasDeltaDbfUrl;
    }

    public String getFiasDeltaXmlUrl() {
        return fiasDeltaXmlUrl;
    }

    public void setFiasDeltaXmlUrl(String fiasDeltaXmlUrl) {
        this.fiasDeltaXmlUrl = fiasDeltaXmlUrl;
    }

    public String getKladr4ArjUrl() {
        return kladr4ArjUrl;
    }

    public void setKladr4ArjUrl(String kladr4ArjUrl) {
        this.kladr4ArjUrl = kladr4ArjUrl;
    }

    public String getKladr47ZUrl() {
        return kladr47ZUrl;
    }

    public void setKladr47ZUrl(String kladr47ZUrl) {
        this.kladr47ZUrl = kladr47ZUrl;
    }

    public String getGarXMLFullURL() {
        return garXMLFullURL;
    }

    public void setGarXMLFullURL(String garXMLFullURL) {
        this.garXMLFullURL = garXMLFullURL;
    }

    public String getGarXMLDeltaURL() {
        return garXMLDeltaURL;
    }

    public void setGarXMLDeltaURL(String garXMLDeltaURL) {
        this.garXMLDeltaURL = garXMLDeltaURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiasInfoFile that = (FiasInfoFile) o;
        return versionId.equals(that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId);
    }

    @Override
    public String toString() {
        return "FiasFileInfo{" +
                "versionId=" + versionId +
                ", textVersion='" + textVersion + '\'' +
                ", fiasCompleteDbfUrl='" + fiasCompleteDbfUrl + '\'' +
                ", fiasCompleteXmlUrl='" + fiasCompleteXmlUrl + '\'' +
                ", fiasDeltaDbfUrl='" + fiasDeltaDbfUrl + '\'' +
                ", fiasDeltaXmlUrl='" + fiasDeltaXmlUrl + '\'' +
                ", kladr4ArjUrl='" + kladr4ArjUrl + '\'' +
                ", kladr47ZUrl='" + kladr47ZUrl + '\'' +
                ", garXMLFullURL='" + garXMLFullURL + '\'' +
                ", garXMLDeltaURL='" + garXMLDeltaURL + '\'' +
                ", date=" + date +
                '}';
    }
}