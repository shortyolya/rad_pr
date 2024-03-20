package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class Item {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private final String id;

    @JacksonXmlProperty(localName = "jcat_id")
    private final String jcatId;

    @JacksonXmlProperty(localName = "error")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<String> errors;

    public Item(@JacksonXmlProperty(localName = "id", isAttribute = true) String id,
                @JacksonXmlProperty(localName = "jcat_id") String jcatId,
                @JacksonXmlProperty(localName = "error") List<String> errors) {
        this.id = id;
        this.jcatId = jcatId;
        this.errors = errors;
    }

    public String getId() {
        return id;
    }

    public String getJcatId() {
        return jcatId;
    }

    public List<String> getErrors() {
        return errors;
    }
}
