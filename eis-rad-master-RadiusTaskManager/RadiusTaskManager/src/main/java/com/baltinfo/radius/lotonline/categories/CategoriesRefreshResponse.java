package com.baltinfo.radius.lotonline.categories;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Suvorina Aleksandra
 * @since 17.02.2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriesRefreshResponse {

    @JsonProperty("errorCode")
    private int errorCode;
    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonCreator
    public CategoriesRefreshResponse() {
    }

    @JsonProperty("errorCode")
    public int getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
