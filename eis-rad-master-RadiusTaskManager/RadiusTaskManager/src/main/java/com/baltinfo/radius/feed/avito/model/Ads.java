package com.baltinfo.radius.feed.avito.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ad"
})
@XmlRootElement(name = "Ads")
public class Ads {

    @XmlAttribute(name = "formatVersion")
    protected Integer formatVersion = 3;
    @XmlAttribute(name = "target")
    protected String target = "Avito.ru";
    @XmlElement(name = "Ad")
    protected List<Ad> ad;

    public Ads() {

    }

    public Ads(List<Ad> ad) {
        this.ad = ad;
    }

    public Integer getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(Integer formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Ad> getAd() {
        return this.ad;
    }

}
