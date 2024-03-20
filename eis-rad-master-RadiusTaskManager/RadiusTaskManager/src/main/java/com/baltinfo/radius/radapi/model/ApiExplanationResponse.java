package com.baltinfo.radius.radapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 21.01.2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiExplanationResponse {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "questionText")
    private String questionText;
    @JsonProperty(value = "dateExplanationReceipt")
    private Instant dateExplanationReceipt;
    @JsonProperty(value = "lotId")
    private Long lotId;
    @JsonProperty(value = "tenderId")
    private Long tenderId;
    @JsonProperty(value = "lotNumber")
    private Integer lotNumber;

    @JsonCreator
    public ApiExplanationResponse(@JsonProperty(value = "id") Long id,
                                  @JsonProperty(value = "questionText") String questionText,
                                  @JsonProperty(value = "dateExplanationReceipt") Instant dateExplanationReceipt,
                                  @JsonProperty(value = "lotId") Long lotId,
                                  @JsonProperty(value = "tenderId") Long tenderId,
                                  @JsonProperty(value = "lotNumber") Integer lotNumber) {
        this.id = id;
        this.questionText = questionText;
        this.dateExplanationReceipt = dateExplanationReceipt;
        this.lotId = lotId;
        this.tenderId = tenderId;
        this.lotNumber = lotNumber;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Instant getDateExplanationReceipt() {
        return dateExplanationReceipt;
    }

    public Long getLotId() {
        return lotId;
    }

    public Long getTenderId() {
        return tenderId;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }
}
