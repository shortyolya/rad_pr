package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class Documents {
    @JacksonXmlProperty(localName = "Document")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("document")
    private final List<String> documents;

    public Documents(@JacksonXmlProperty(localName = "Document")
                     @JsonProperty("document") List<String> documents) {
        this.documents = documents;
    }

    public List<String> getDocuments() {
        return documents;
    }
}
