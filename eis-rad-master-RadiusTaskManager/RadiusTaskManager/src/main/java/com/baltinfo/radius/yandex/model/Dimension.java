
package com.baltinfo.radius.yandex.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "icon_id",
    "icon_type",
    "name",
    "id",
    "favicon"
})
public class Dimension {

    @JsonProperty("icon_id")
    private String iconId;
    @JsonProperty("icon_type")
    private String iconType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("favicon")
    private String favicon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("icon_id")
    public String getIconId() {
        return iconId;
    }

    @JsonProperty("icon_id")
    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    @JsonProperty("icon_type")
    public String getIconType() {
        return iconType;
    }

    @JsonProperty("icon_type")
    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("favicon")
    public String getFavicon() {
        return favicon;
    }

    @JsonProperty("favicon")
    public void setFavicon(String favicon) {
        this.favicon = favicon;
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
