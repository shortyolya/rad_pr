package com.baltinfo.radius.avito.model.development;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Region {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private final String name;
    @JacksonXmlProperty(localName = "City")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<City> cityList;

    public Region(@JacksonXmlProperty(localName = "name", isAttribute = true) String name,
                @JacksonXmlProperty(localName = "City") List<City> cityList) {
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public List<City> getCityList() {
        return cityList;
    }
}
