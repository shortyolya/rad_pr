package com.baltinfo.radius.feed.avito.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

public class Images {

    @XmlElement(name = "Image")
    protected List<Image> imageList;

    public List<Image> getImageList() {
        if (imageList == null) {
            imageList = new ArrayList<Image>();
        }
        return this.imageList;
    }
}
