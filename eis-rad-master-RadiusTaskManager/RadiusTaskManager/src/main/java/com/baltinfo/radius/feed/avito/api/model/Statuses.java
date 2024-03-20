
package com.baltinfo.radius.feed.avito.api.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Statuses {

    @JsonProperty("avito")
    private Avito avito;
    @JsonProperty("general")
    private General general;
    @JsonProperty("processing")
    private Processing processing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("avito")
    public Avito getAvito() {
        return avito;
    }

    @JsonProperty("avito")
    public void setAvito(Avito avito) {
        this.avito = avito;
    }

    @JsonProperty("general")
    public General getGeneral() {
        return general;
    }

    @JsonProperty("general")
    public void setGeneral(General general) {
        this.general = general;
    }

    @JsonProperty("processing")
    public Processing getProcessing() {
        return processing;
    }

    @JsonProperty("processing")
    public void setProcessing(Processing processing) {
        this.processing = processing;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
