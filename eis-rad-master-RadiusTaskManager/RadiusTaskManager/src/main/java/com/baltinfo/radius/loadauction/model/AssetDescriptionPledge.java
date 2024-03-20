package com.baltinfo.radius.loadauction.model;

import com.baltinfo.radius.loadauction.model.assets.Asset;
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
public class AssetDescriptionPledge {

    @JsonProperty("descriptionPledge")
    private final List<Asset> pledges;

    @JsonCreator
    public AssetDescriptionPledge(@JsonProperty("descriptionPledge") List<Asset> pledges) {
        this.pledges = pledges;
    }

    public List<Asset> getPledges() {
        return pledges;
    }
}
