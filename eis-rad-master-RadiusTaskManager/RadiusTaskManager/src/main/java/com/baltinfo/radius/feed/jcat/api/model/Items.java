package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class Items {

    @JacksonXmlProperty(localName = "count", isAttribute = true)
    private final int count;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<Item> items;

    public Items(@JacksonXmlProperty(localName = "count", isAttribute = true) int count,
                 @JacksonXmlProperty(localName = "item") List<Item> items) {
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public List<Item> getItems() {
        return items;
    }
}
