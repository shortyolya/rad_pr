package com.baltinfo.radius.avito.model.development;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Housing {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private final Long id;
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private final String name;
    @JacksonXmlProperty(localName = "address", isAttribute = true)
    private final String address;

    public Housing(@JacksonXmlProperty(localName = "id", isAttribute = true) Long id,
                   @JacksonXmlProperty(localName = "name", isAttribute = true) String name,
                   @JacksonXmlProperty(localName = "address", isAttribute = true) String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
}
