package com.baltinfo.radius.avito.model.development;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class City {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private final String name;
    @JacksonXmlProperty(localName = "Object")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Object> objectList;

    public City(@JacksonXmlProperty(localName = "name", isAttribute = true) String name,
                @JacksonXmlProperty(localName = "Object") List<Object> objectList) {
        this.name = name;
        this.objectList = objectList;
    }

    public String getName() {
        return name;
    }

    public List<Object> getObjectList() {
        return objectList;
    }
}
