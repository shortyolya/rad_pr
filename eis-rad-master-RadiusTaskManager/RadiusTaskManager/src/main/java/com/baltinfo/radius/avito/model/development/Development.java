package com.baltinfo.radius.avito.model.development;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Development {

    @JacksonXmlProperty(localName = "Region")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Region> regionList;

    public Development(@JacksonXmlProperty(localName = "Region") List<Region> regionList) {
        this.regionList = regionList;
    }

    public List<Region> getRegionList() {
        return regionList;
    }
}
