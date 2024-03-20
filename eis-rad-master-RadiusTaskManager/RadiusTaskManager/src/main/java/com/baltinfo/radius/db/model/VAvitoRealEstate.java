package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "v_avito_real_estate", schema = "web")
@NamedQueries({
        @NamedQuery(name = "VAvitoRealEstate.getObjectsByCategoryFeedName", query = "select v from VAvitoRealEstate v where v.categoryFeedCode = :categoryFeedCode"),
        @NamedQuery(name = "VAvitoRealEstate.getObjUnidListByFcUnid", query = "select v.objUnid from VAvitoRealEstate v where v.fcUnid = :fcUnid")
})
public class VAvitoRealEstate implements FeedObject {

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
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "square")
    private Double square;
    @Column(name = "land_area")
    private Double landArea;
    @Column(name = "floor")
    private String floor;
    @Column(name = "floors")
    private String floors;
    @Column(name = "market_type")
    private String marketType;
    @Column(name = "rooms")
    private String rooms;
    @Column(name = "house_type")
    private String houseType;
    @Column(name = "decoration")
    private String decoration;
    @Column(name = "price")
    private Double price;
    @Column(name = "address")
    private String address;
    @Column(name = "walls_type")
    private String wallsType;
    @Column(name = "material_of_garage")
    private String materialOfGarage;
    @Column(name = "secured")
    private String secured;
    @Column(name = "type_of_parking")
    private String typeOfParking;
    @Column(name = "type_of_garage")
    private String typeOfGarage;
    @Column(name = "status")
    private String status;
    @Column(name = "kitchen_space")
    private String kitchenSpace;
    @Column(name = "subagent_mobile_phone")
    private String subagentMobilePhone;
    @Column(name = "subagent_name_f")
    private String subagentNameF;
    @Column(name = "subagent_name_i")
    private String subagentNameI;
    @Column(name = "obj_ind_rent")
    private Boolean objIndRent;
    @Column(name = "lease_commission_size")
    private String leaseCommissionSize;
    @Column(name = "development_id")
    private Long developmentId;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "balcony_or_loggia")
    private String balconyOrLoggiaMulti;
    @Column(name = "deal_type")
    private String dealType;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "bathroom_multi")
    private String bathroomMulti;
    @Column(name = "land_status")
    private String landStatus;
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "building_type")
    private String buildingType;
    @Column(name = "renovation")
    private String renovation;
    @Column(name = "lot_etp_code")
    private String lotEtpCode;
    @Column(name = "decoration_comm")
    private String decorationComm;
    @Column(name = "parking_type")
    private String parkingType;
    @Column(name = "entrance")
    private String entrance;
    @Column(name = "layout")
    private String layout;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
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

    public String getWallsType() {
        return wallsType;
    }

    public void setWallsType(String wallsType) {
        this.wallsType = wallsType;
    }

    public String getMaterialOfGarage() {
        return materialOfGarage;
    }

    public void setMaterialOfGarage(String materialOfGarage) {
        this.materialOfGarage = materialOfGarage;
    }

    public String getSecured() {
        return secured;
    }

    public void setSecured(String secured) {
        this.secured = secured;
    }

    public String getTypeOfParking() {
        return typeOfParking;
    }

    public void setTypeOfParking(String typeOfParking) {
        this.typeOfParking = typeOfParking;
    }

    public String getTypeOfGarage() {
        return typeOfGarage;
    }

    public void setTypeOfGarage(String typeOfGarage) {
        this.typeOfGarage = typeOfGarage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getObjIndRent() {
        return objIndRent;
    }

    public void setObjIndRent(Boolean objIndRent) {
        this.objIndRent = objIndRent;
    }

    public String getLeaseCommissionSize() {
        return leaseCommissionSize;
    }

    public void setLeaseCommissionSize(String leaseCommissionSize) {
        this.leaseCommissionSize = leaseCommissionSize;
    }

    public Long getDevelopmentId() {
        return developmentId;
    }

    public void setDevelopmentId(Long developmentId) {
        this.developmentId = developmentId;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public int getPictureCount() {
        return pictureCount;
    }

    public String getKitchenSpace() {
        return kitchenSpace;
    }

    public void setKitchenSpace(String kitchenSpace) {
        this.kitchenSpace = kitchenSpace;
    }

    public String getBalconyOrLoggiaMulti() {
        return balconyOrLoggiaMulti;
    }

    public void setBalconyOrLoggiaMulti(String balconyOrLoggiaMulti) {
        this.balconyOrLoggiaMulti = balconyOrLoggiaMulti;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

    public String getBathroomMulti() {
        return bathroomMulti;
    }

    public void setBathroomMulti(String bathroomMulti) {
        this.bathroomMulti = bathroomMulti;
    }

    public String getLandStatus() {
        return landStatus;
    }

    public void setLandStatus(String landStatus) {
        this.landStatus = landStatus;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    @Override
    public String getLotEtpCode() {
        return lotEtpCode;
    }

    public void setLotEtpCode(String lotEtpCode) {
        this.lotEtpCode = lotEtpCode;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getDecorationComm() {
        return decorationComm;
    }

    public void setDecorationComm(String decorationComm) {
        this.decorationComm = decorationComm;
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
