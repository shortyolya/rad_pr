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
 *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AreaUnitType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="hectare"/>
 *               &lt;enumeration value="sotka"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="owned"/>
 *               &lt;enumeration value="rent"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "area",
        "areaUnitType",
        "status"
})
public class Land {

    @XmlElement(name = "Area")
    protected double area;
    @XmlElement(name = "AreaUnitType")
    protected String areaUnitType;
    @XmlElement(name = "Status")
    protected String status;

    /**
     * Gets the value of the area property.
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     */
    public void setArea(double value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaUnitType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAreaUnitType() {
        return areaUnitType;
    }

    /**
     * Sets the value of the areaUnitType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAreaUnitType(String value) {
        this.areaUnitType = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
