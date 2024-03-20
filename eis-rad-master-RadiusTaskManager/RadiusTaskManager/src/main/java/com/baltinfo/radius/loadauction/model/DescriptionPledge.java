package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 16.02.2022
 */
@JsonRootName(value = "descriptionPledges")
public class DescriptionPledge {

    @JacksonXmlProperty(localName = "Description_pledge")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("descriptionPledge")
    private final List<AssetFull> pledges;

    @JsonCreator
    public DescriptionPledge(@JacksonXmlProperty(localName = "Description_pledge")
                             @JsonProperty("descriptionPledge") List<AssetFull> pledges) {
        this.pledges = pledges;
    }

    public List<AssetFull> getPledges() {
        return pledges;
    }
}
