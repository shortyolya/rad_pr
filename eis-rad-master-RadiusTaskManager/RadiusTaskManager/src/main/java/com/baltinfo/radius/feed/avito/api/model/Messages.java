
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
public class Messages {
    @JsonProperty("element_name")
    private String elementName;
    @JsonProperty("help_url")
    private String helpUrl;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("element_name")
    public String getElementName() {
        return elementName;
    }

    @JsonProperty("element_name")
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @JsonProperty("help_url")
    public String getHelpUrl() {
        return helpUrl;
    }

    @JsonProperty("help_url")
    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
