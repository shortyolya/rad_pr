package com.baltinfo.radius.dadata.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * <p>
 *  POJO class для запросов к сервису dadata.ru
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 09.12.2019
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    @JsonProperty("suggestions")
    private final List<Suggestion> suggestions;

    @JsonCreator
    public AddressResponse(@JsonProperty("suggestions") List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    @JsonProperty("suggestions")
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "suggestions=" + suggestions +
                '}';
    }
}