//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.24 at 03:03:52 PM MSK 
//


package com.baltinfo.radius.feed.cian.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FullUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fullUrl",
        "isDefault"
})
public class LayoutPhoto {

    @XmlElement(name = "FullUrl")
    protected String fullUrl;
    @XmlElement(name = "IsDefault")
    protected Boolean isDefault;

    /**
     * Gets the value of the fullUrl property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFullUrl() {
        return fullUrl;
    }

    /**
     * Sets the value of the fullUrl property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFullUrl(String value) {
        this.fullUrl = value;
    }

    /**
     * Gets the value of the isDefault property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

}