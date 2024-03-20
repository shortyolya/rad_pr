package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "v_avito_auto", schema = "web")
public class VAvitoAuto implements FeedObject{

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
    @Column(name = "category")
    private String category;
    @Column(name = "category_feed_code")
    private String categoryFeedCode;
    @Column(name = "description")
    private String description;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private String year;
    @Column(name = "kilometrage")
    private String kilometrage;
    @Column(name = "accident")
    private String accident;
    @Column(name = "vin")
    private String vin;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "doors")
    private String doors;
    @Column(name = "generation_id")
    private Long generationId;
    @Column(name = "modification_id")
    private Long modificationId;
    @Column(name = "color")
    private String color;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "engine_size")
    private String engineSize;
    @Column(name = "power")
    private String power;
    @Column(name = "transmission")
    private String transmission;
    @Column(name = "drive_type")
    private String driveType;
    @Column(name = "wheel_type")
    private String wheelType;
    @Column(name = "owners")
    private String owners;
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
    @Transient
    private int pictureCount;

    public Long getOmeUnid() {
        return omeUnid;
    }

    public void setOmeUnid(Long omeUnid) {
        this.omeUnid = omeUnid;
    }

    @Override
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

    @Override
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

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public Long getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Long generationId) {
        this.generationId = generationId;
    }

    public Long getModificationId() {
        return modificationId;
    }

    public void setModificationId(Long modificationId) {
        this.modificationId = modificationId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
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
}
