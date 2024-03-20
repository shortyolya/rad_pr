package com.baltinfo.radius.avito.model.development;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Object {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private final Long id;
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private final String name;
    @JacksonXmlProperty(localName = "address", isAttribute = true)
    private final String address;
    @JacksonXmlProperty(localName = "developer", isAttribute = true)
    private final String developer;
    @JacksonXmlProperty(localName = "Housing")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Housing> houseList;

    public Object(@JacksonXmlProperty(localName = "id", isAttribute = true) Long id,
                  @JacksonXmlProperty(localName = "name", isAttribute = true) String name,
                  @JacksonXmlProperty(localName = "address", isAttribute = true) String address,
                  @JacksonXmlProperty(localName = "developer", isAttribute = true) String developer,
                  @JacksonXmlProperty(localName = "Housing") List<Housing> houseList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.developer = developer;
        this.houseList = houseList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDeveloper() {
        return developer;
    }

    public List<Housing> getHouseList() {
        return houseList;
    }
}
