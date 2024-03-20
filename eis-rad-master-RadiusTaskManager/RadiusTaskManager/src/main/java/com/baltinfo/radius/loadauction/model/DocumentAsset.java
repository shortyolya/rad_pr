package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.01.2020
 */
public class DocumentAsset {
    @JacksonXmlProperty(localName = "Document_asset")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<String> documents;

    public DocumentAsset(@JacksonXmlProperty(localName = "Document_asset") List<String> documents) {
        this.documents = documents;
    }

    public List<String> getDocuments() {
        return documents;
    }
}
