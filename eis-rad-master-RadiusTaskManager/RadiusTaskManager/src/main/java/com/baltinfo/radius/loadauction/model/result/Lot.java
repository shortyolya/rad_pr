package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Lot {

    @JsonProperty("id")
    private final Long id;
    @JsonProperty("radNumber")
    private final String radNumber;
    @JsonProperty("dateReport")
    private final String dateReport;
    @JsonProperty("lotResult")
    private final Integer lotResult;
    @JsonProperty("successDate")
    private final String successDate;
    @JsonProperty("faultDate")
    private final String faultDate;
    @JsonProperty("lotCancelReason")
    private final String lotCancelReason;
    @JsonProperty("infoLot")
    private final List<InfoLot> infoLot;
    @JsonProperty("applicants")
    private final List<Applicant> applicants;

    @JsonCreator
    public Lot(@JsonProperty("id") Long id,
               @JsonProperty("radNumber") String radNumber,
               @JsonProperty("dateReport") String dateReport,
               @JsonProperty("lotResult") Integer lotResult,
               @JsonProperty("successDate") String successDate,
               @JsonProperty("faultDate") String faultDate,
               @JsonProperty("lotCancelReason") String lotCancelReason,
               @JsonProperty("infoLot") List<InfoLot> infoLot,
               @JsonProperty("applicants") List<Applicant> applicants) {
        this.id = id;
        this.radNumber = radNumber;
        this.dateReport = dateReport;
        this.lotResult = lotResult;
        this.successDate = successDate;
        this.faultDate = faultDate;
        this.lotCancelReason = lotCancelReason;
        this.infoLot = infoLot;
        this.applicants = applicants;
    }

    public Long getId() {
        return id;
    }

    public String getRadNumber() {
        return radNumber;
    }

    public String getDateReport() {
        return dateReport;
    }

    public Integer getLotResult() {
        return lotResult;
    }

    public String getSuccessDate() {
        return successDate;
    }

    public String getFaultDate() {
        return faultDate;
    }

    public String getLotCancelReason() {
        return lotCancelReason;
    }

    public List<InfoLot> getInfoLot() {
        return infoLot;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }
}
