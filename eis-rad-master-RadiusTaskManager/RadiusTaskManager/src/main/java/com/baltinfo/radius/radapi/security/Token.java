package com.baltinfo.radius.radapi.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Токен пользователя
 *
 * @author Igor Lapenok
 * @since 13.12.2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "lotId",
        "bkrId"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    private static final String EMPTY_USER_ID = "0";

    // Идентификатор пользователя витрины
    @JsonProperty("id")
    private final String id;
    // Идентификатор пользователя на ЭТП (Банкротство)
    @JsonProperty("bkrId")
    private final String bkrId;
    // Идентификатор пользователя на ЭТП (Lot-online)
    @JsonProperty("lotId")
    private final String lotId;

    @JsonCreator
    public Token(@JsonProperty("id") String id, @JsonProperty("bkrId") String bkrId, @JsonProperty("lotId") String lotId) {
        this.id = id;
        this.bkrId = bkrId;
        this.lotId = lotId;
    }

    public Token() {
        this.id = EMPTY_USER_ID;
        this.bkrId = EMPTY_USER_ID;
        this.lotId = EMPTY_USER_ID;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("bkrId")
    public String getBkrId() {
        return bkrId;
    }

    @JsonProperty("lotId")
    public String getLotId() {
        return lotId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", bkrId='" + bkrId + '\'' +
                ", lotId='" + lotId + '\'' +
                '}';
    }
}

