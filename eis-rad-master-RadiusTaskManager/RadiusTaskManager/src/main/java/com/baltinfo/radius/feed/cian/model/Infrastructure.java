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
 *         &lt;element name="HasCarWash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasBuffet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasCarService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasCanteen" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasCentralReception" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasHotel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasAtm" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasExhibitionAndWarehouseComplex" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasPharmacy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasBankDepartment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasCinema" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasCafe" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasMedicalCenter" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasBeautyShop" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasStudio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasNotaryOffice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasPool" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasClothesStudio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasWarehouse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasPark" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasRestaurant" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasFitnessCentre" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasSupermarket" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasMinimarket" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasShoppingArea" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="HasConferenceRoom" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "hasCarWash",
        "hasBuffet",
        "hasCarService",
        "hasCanteen",
        "hasCentralReception",
        "hasHotel",
        "hasAtm",
        "hasExhibitionAndWarehouseComplex",
        "hasPharmacy",
        "hasBankDepartment",
        "hasCinema",
        "hasCafe",
        "hasMedicalCenter",
        "hasBeautyShop",
        "hasStudio",
        "hasNotaryOffice",
        "hasPool",
        "hasClothesStudio",
        "hasWarehouse",
        "hasPark",
        "hasRestaurant",
        "hasFitnessCentre",
        "hasSupermarket",
        "hasMinimarket",
        "hasShoppingArea",
        "hasConferenceRoom"
})
public class Infrastructure {

    @XmlElement(name = "HasCarWash")
    protected Boolean hasCarWash;
    @XmlElement(name = "HasBuffet")
    protected Boolean hasBuffet;
    @XmlElement(name = "HasCarService")
    protected Boolean hasCarService;
    @XmlElement(name = "HasCanteen")
    protected Boolean hasCanteen;
    @XmlElement(name = "HasCentralReception")
    protected Boolean hasCentralReception;
    @XmlElement(name = "HasHotel")
    protected Boolean hasHotel;
    @XmlElement(name = "HasAtm")
    protected Boolean hasAtm;
    @XmlElement(name = "HasExhibitionAndWarehouseComplex")
    protected Boolean hasExhibitionAndWarehouseComplex;
    @XmlElement(name = "HasPharmacy")
    protected Boolean hasPharmacy;
    @XmlElement(name = "HasBankDepartment")
    protected Boolean hasBankDepartment;
    @XmlElement(name = "HasCinema")
    protected Boolean hasCinema;
    @XmlElement(name = "HasCafe")
    protected Boolean hasCafe;
    @XmlElement(name = "HasMedicalCenter")
    protected Boolean hasMedicalCenter;
    @XmlElement(name = "HasBeautyShop")
    protected Boolean hasBeautyShop;
    @XmlElement(name = "HasStudio")
    protected Boolean hasStudio;
    @XmlElement(name = "HasNotaryOffice")
    protected Boolean hasNotaryOffice;
    @XmlElement(name = "HasPool")
    protected Boolean hasPool;
    @XmlElement(name = "HasClothesStudio")
    protected Boolean hasClothesStudio;
    @XmlElement(name = "HasWarehouse")
    protected Boolean hasWarehouse;
    @XmlElement(name = "HasPark")
    protected Boolean hasPark;
    @XmlElement(name = "HasRestaurant")
    protected Boolean hasRestaurant;
    @XmlElement(name = "HasFitnessCentre")
    protected Boolean hasFitnessCentre;
    @XmlElement(name = "HasSupermarket")
    protected Boolean hasSupermarket;
    @XmlElement(name = "HasMinimarket")
    protected Boolean hasMinimarket;
    @XmlElement(name = "HasShoppingArea")
    protected Boolean hasShoppingArea;
    @XmlElement(name = "HasConferenceRoom")
    protected Boolean hasConferenceRoom;

    /**
     * Gets the value of the hasCarWash property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCarWash() {
        return hasCarWash;
    }

    /**
     * Sets the value of the hasCarWash property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCarWash(Boolean value) {
        this.hasCarWash = value;
    }

    /**
     * Gets the value of the hasBuffet property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasBuffet() {
        return hasBuffet;
    }

    /**
     * Sets the value of the hasBuffet property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasBuffet(Boolean value) {
        this.hasBuffet = value;
    }

    /**
     * Gets the value of the hasCarService property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCarService() {
        return hasCarService;
    }

    /**
     * Sets the value of the hasCarService property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCarService(Boolean value) {
        this.hasCarService = value;
    }

    /**
     * Gets the value of the hasCanteen property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCanteen() {
        return hasCanteen;
    }

    /**
     * Sets the value of the hasCanteen property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCanteen(Boolean value) {
        this.hasCanteen = value;
    }

    /**
     * Gets the value of the hasCentralReception property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCentralReception() {
        return hasCentralReception;
    }

    /**
     * Sets the value of the hasCentralReception property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCentralReception(Boolean value) {
        this.hasCentralReception = value;
    }

    /**
     * Gets the value of the hasHotel property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasHotel() {
        return hasHotel;
    }

    /**
     * Sets the value of the hasHotel property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasHotel(Boolean value) {
        this.hasHotel = value;
    }

    /**
     * Gets the value of the hasAtm property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasAtm() {
        return hasAtm;
    }

    /**
     * Sets the value of the hasAtm property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasAtm(Boolean value) {
        this.hasAtm = value;
    }

    /**
     * Gets the value of the hasExhibitionAndWarehouseComplex property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasExhibitionAndWarehouseComplex() {
        return hasExhibitionAndWarehouseComplex;
    }

    /**
     * Sets the value of the hasExhibitionAndWarehouseComplex property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasExhibitionAndWarehouseComplex(Boolean value) {
        this.hasExhibitionAndWarehouseComplex = value;
    }

    /**
     * Gets the value of the hasPharmacy property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasPharmacy() {
        return hasPharmacy;
    }

    /**
     * Sets the value of the hasPharmacy property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasPharmacy(Boolean value) {
        this.hasPharmacy = value;
    }

    /**
     * Gets the value of the hasBankDepartment property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasBankDepartment() {
        return hasBankDepartment;
    }

    /**
     * Sets the value of the hasBankDepartment property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasBankDepartment(Boolean value) {
        this.hasBankDepartment = value;
    }

    /**
     * Gets the value of the hasCinema property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCinema() {
        return hasCinema;
    }

    /**
     * Sets the value of the hasCinema property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCinema(Boolean value) {
        this.hasCinema = value;
    }

    /**
     * Gets the value of the hasCafe property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasCafe() {
        return hasCafe;
    }

    /**
     * Sets the value of the hasCafe property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasCafe(Boolean value) {
        this.hasCafe = value;
    }

    /**
     * Gets the value of the hasMedicalCenter property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasMedicalCenter() {
        return hasMedicalCenter;
    }

    /**
     * Sets the value of the hasMedicalCenter property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasMedicalCenter(Boolean value) {
        this.hasMedicalCenter = value;
    }

    /**
     * Gets the value of the hasBeautyShop property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasBeautyShop() {
        return hasBeautyShop;
    }

    /**
     * Sets the value of the hasBeautyShop property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasBeautyShop(Boolean value) {
        this.hasBeautyShop = value;
    }

    /**
     * Gets the value of the hasStudio property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasStudio() {
        return hasStudio;
    }

    /**
     * Sets the value of the hasStudio property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasStudio(Boolean value) {
        this.hasStudio = value;
    }

    /**
     * Gets the value of the hasNotaryOffice property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasNotaryOffice() {
        return hasNotaryOffice;
    }

    /**
     * Sets the value of the hasNotaryOffice property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasNotaryOffice(Boolean value) {
        this.hasNotaryOffice = value;
    }

    /**
     * Gets the value of the hasPool property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasPool() {
        return hasPool;
    }

    /**
     * Sets the value of the hasPool property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasPool(Boolean value) {
        this.hasPool = value;
    }

    /**
     * Gets the value of the hasClothesStudio property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasClothesStudio() {
        return hasClothesStudio;
    }

    /**
     * Sets the value of the hasClothesStudio property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasClothesStudio(Boolean value) {
        this.hasClothesStudio = value;
    }

    /**
     * Gets the value of the hasWarehouse property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasWarehouse() {
        return hasWarehouse;
    }

    /**
     * Sets the value of the hasWarehouse property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasWarehouse(Boolean value) {
        this.hasWarehouse = value;
    }

    /**
     * Gets the value of the hasPark property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasPark() {
        return hasPark;
    }

    /**
     * Sets the value of the hasPark property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasPark(Boolean value) {
        this.hasPark = value;
    }

    /**
     * Gets the value of the hasRestaurant property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasRestaurant() {
        return hasRestaurant;
    }

    /**
     * Sets the value of the hasRestaurant property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasRestaurant(Boolean value) {
        this.hasRestaurant = value;
    }

    /**
     * Gets the value of the hasFitnessCentre property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasFitnessCentre() {
        return hasFitnessCentre;
    }

    /**
     * Sets the value of the hasFitnessCentre property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasFitnessCentre(Boolean value) {
        this.hasFitnessCentre = value;
    }

    /**
     * Gets the value of the hasSupermarket property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasSupermarket() {
        return hasSupermarket;
    }

    /**
     * Sets the value of the hasSupermarket property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasSupermarket(Boolean value) {
        this.hasSupermarket = value;
    }

    /**
     * Gets the value of the hasMinimarket property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasMinimarket() {
        return hasMinimarket;
    }

    /**
     * Sets the value of the hasMinimarket property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasMinimarket(Boolean value) {
        this.hasMinimarket = value;
    }

    /**
     * Gets the value of the hasShoppingArea property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasShoppingArea() {
        return hasShoppingArea;
    }

    /**
     * Sets the value of the hasShoppingArea property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasShoppingArea(Boolean value) {
        this.hasShoppingArea = value;
    }

    /**
     * Gets the value of the hasConferenceRoom property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isHasConferenceRoom() {
        return hasConferenceRoom;
    }

    /**
     * Sets the value of the hasConferenceRoom property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setHasConferenceRoom(Boolean value) {
        this.hasConferenceRoom = value;
    }

}
