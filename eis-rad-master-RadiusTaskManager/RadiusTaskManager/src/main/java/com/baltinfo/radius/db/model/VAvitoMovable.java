package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
@Entity
@Table(name = "v_avito_movable", schema = "web")
public class VAvitoMovable implements FeedObject {
    @Id
    @Column(name = "ome_unid")
    private Long omeUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "obj_code")
    private String objCode;
    @Column(name = "ls_unid")
    private Long lsUnid;
    @Column(name = "sc_unid")
    private Long scUnid;
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "category_feed_code")
    private String categoryFeedCode;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "technical_passport")
    private String technicalPassport;
    @Column(name = "year")
    private String year;
    @Column(name = "price")
    private Double price;
    @Column(name = "address")
    private String address;
    @Column(name = "subagent_mobile_phone")
    private String subagentMobilePhone;
    @Column(name = "subagent_name_f")
    private String subagentNameF;
    @Column(name = "subagent_name_i")
    private String subagentNameI;
    @Column(name = "listing_fee")
    private String listingFee;
    @Column(name = "obj_ind_rent")
    private Boolean objIndRent;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "lot_etp_code")
    private String lotEtpCode;
    @Column(name = "etp_link")
    private String etpLink;
    @Column(name = "org_sub_unid")
    private Long orgSubUnid;
    @Column(name = "vin")
    private String vin;
    @Column(name = "kilometrage")
    private Double kilometrage;
    @Column(name = "typeOfVehicle")
    private String typeOfVehicle;
    @Column(name = "typeOfTrailer")
    private String typeOfTrailer;
    @Column(name = "goodsType")
    private String goodsType;
    @Column(name = "goodsSubType")
    private String goodsSubType;
    @Column(name = "goodsSubTypeNew")
    private String goodsSubTypeNew;
    @Column(name = "goodsSubSubType")
    private String goodsSubSubType;
    @Transient
    private int pictureCount;

    public Long getOmeUnid() {
        return omeUnid;
    }

    public void setOmeUnid(Long omeUnid) {
        this.omeUnid = omeUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(Long lsUnid) {
        this.lsUnid = lsUnid;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public void setScUnid(Long scUnid) {
        this.scUnid = scUnid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryFeedCode() {
        return categoryFeedCode;
    }

    public void setCategoryFeedCode(String categoryFeedCode) {
        this.categoryFeedCode = categoryFeedCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getTechnicalPassport() {
        return technicalPassport;
    }

    public void setTechnicalPassport(String technicalPassport) {
        this.technicalPassport = technicalPassport;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubagentMobilePhone() {
        return subagentMobilePhone;
    }

    public void setSubagentMobilePhone(String subagentMobilePhone) {
        this.subagentMobilePhone = subagentMobilePhone;
    }

    public String getSubagentNameF() {
        return subagentNameF;
    }

    public void setSubagentNameF(String subagentNameF) {
        this.subagentNameF = subagentNameF;
    }

    public String getSubagentNameI() {
        return subagentNameI;
    }

    public void setSubagentNameI(String subagentNameI) {
        this.subagentNameI = subagentNameI;
    }

    public String getListingFee() {
        return listingFee;
    }

    public void setListingFee(String listingFee) {
        this.listingFee = listingFee;
    }

    public Boolean getObjIndRent() {
        return objIndRent;
    }

    public void setObjIndRent(Boolean objIndRent) {
        this.objIndRent = objIndRent;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    @Override
    public int getPictureCount() {
        return pictureCount;
    }

    @Override
    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

    @Override
    public String getLotEtpCode() {
        return lotEtpCode;
    }

    public void setLotEtpCode(String lotEtpCode) {
        this.lotEtpCode = lotEtpCode;
    }

    @Override
    public String getEtpLink() {
        return etpLink;
    }

    @Override
    public Long getOrgSubUnid() {
        return orgSubUnid;
    }

    public void setOrgSubUnid(Long orgSubUnid) {
        this.orgSubUnid = orgSubUnid;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getTypeOfTrailer() {
        return typeOfTrailer;
    }

    public void setTypeOfTrailer(String typeOfTrailer) {
        this.typeOfTrailer = typeOfTrailer;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsSubType() {
        return goodsSubType;
    }

    public void setGoodsSubType(String goodsSubType) {
        this.goodsSubType = goodsSubType;
    }

    public String getGoodsSubTypeNew() {
        return goodsSubTypeNew;
    }

    public void setGoodsSubTypeNew(String goodsSubTypeNew) {
        this.goodsSubTypeNew = goodsSubTypeNew;
    }

    public String getGoodsSubSubType() {
        return goodsSubSubType;
    }

    public void setGoodsSubSubType(String goodsSubSubType) {
        this.goodsSubSubType = goodsSubSubType;
    }
}
