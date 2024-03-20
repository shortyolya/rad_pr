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
public class Images {
    @JacksonXmlProperty(localName = "Image")
    @JsonProperty("Image")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<String> images;

    public Images(@JacksonXmlProperty(localName = "Image")
                  @JsonProperty("Image") List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }
}
