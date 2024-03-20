package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Igor Lapenok
 * @since 10.03.2021
 */
public class Error {
    @JsonProperty("code")
    private final String code;
    @JsonProperty("key")
    private final String key;
    @JsonProperty("message")
    private final String message;

    @JsonCreator
    public Error(@JsonProperty("code") String code,
                 @JsonProperty("key") String key,
                 @JsonProperty("message") String message) {
        this.code = code;
        this.key = key;
        this.message = message;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}