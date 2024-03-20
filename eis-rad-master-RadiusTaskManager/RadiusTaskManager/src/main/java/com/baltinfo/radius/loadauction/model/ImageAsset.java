package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageAsset {
    @JacksonXmlProperty(localName = "Image_asset")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<String> images;

    public ImageAsset(@JacksonXmlProperty(localName = "Image_asset") List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }
}
