package com.baltinfo.radius.db.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kulikov Semyon
 * @since 30.01.2020
 */

@Entity
@Table(name = "v_cian_real_estate", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VCianRealEstate.getObjectsByCategoryName", query = "SELECT c FROM VCianRealEstate c WHERE c.category = :category and c.orgSubUnid = :subUnid"),
        @NamedQuery(name = "VCianRealEstate.getObjUnidListByCategory", query = "SELECT c.objUnid FROM VCianRealEstate c WHERE c.category = :category and c.orgSubUnid = :subUnid"),
        @NamedQuery(name = "VCianRealEstate.getObjUnidListByFcUnid", query = "SELECT c.objUnid FROM VCianRealEstate c WHERE c.fcUnid = :fcUnid and c.orgSubUnid = :subUnid"),
        @NamedQuery(name = "VCianRealEstate.getAllCommercialRealEstateForExport", query = "SELECT c FROM VCianRealEstate c WHERE c.orgSubUnid = :subUnid")
})
public class VCianRealEstate implements FeedObject {
    @Id
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "obj_code")
    private String objCode;
    @Column(name = "ls_unid")
    private Long lsUnid;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "sc_unid")
    private Long scUnid;
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "category")
    private String category;
    @Column(name = "category_feed_name")
    private String categoryFeedName;
    @Column(name = "total_area")
    private Double totalArea;
    @Column(name = "floor_number")
    private String floorNumber;
    @Column(name = "is_occupied")
    private Boolean isOccupied;
    @Column(name = "layout")
    private String layout;
    @Column(name = "furniture_presence")
    private String furniturePresence;
    @Column(name = "available_from")
    private String availableFrom;
    @Column(name = "is_legal_address_provided")
    private Boolean isLegalAddressProvided;
    @Column(name = "water_pipes_count")
    private Integer waterPipesCount;
    @Column(name = "tax_number")
    private Integer taxNumber;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "floors_count")
    private String floorsCount;
    @Column(name = "build_year")
    private String buildYear;
    @Column(name = "material_type")
    private String materialType;
    @Column(name = "heating_type")
    private String heatingType;
    @Column(name = "ceiling_height")
    private Double ceilingHeight;
    @Column(name = "parking_type")
    private String parkingType;
    @Column(name = "places_count")
    private Integer placesCount;
    @Column(name = "building_type")
    private String buildingType;
    @Column(name = "class_type")
    private String classType;
    @Column(name = "ventilation_type")
    private String ventilationType;
    @Column(name = "conditioning_type")
    private String conditioningType;
    @Column(name = "extinguishing_system_type")
    private String extinguishingSystemType;
    @Column(name = "status_type")
    private String statusType;
    @Column(name = "access_type")
    private String accessType;
    @Column(name = "has_car_wash")
    private Boolean hasCarWash;
    @Column(name = "has_buffet")
    private Boolean hasBuffet;
    @Column(name = "has_car_service")
    private Boolean hasCarService;
    @Column(name = "has_canteen")
    private Boolean hasCanteen;
    @Column(name = "has_central_reception")
    private Boolean hasCentralReception;
    @Column(name = "has_hotel")
    private Boolean hasHotel;
    @Column(name = "has_atm")
    private Boolean hasAtm;
    @Column(name = "has_exhibition_and_warehouse_complex")
    private Boolean hasExhibitionAndWarehouseComplex;
    @Column(name = "has_pharmacy")
    private Boolean hasPharmacy;
    @Column(name = "has_bank_department")
    private Boolean hasBankDepartment;
    @Column(name = "has_cinema")
    private Boolean hasCinema;
    @Column(name = "has_cafe")
    private Boolean hasCafe;
    @Column(name = "has_medical_center")
    private Boolean hasMedicalCenter;
    @Column(name = "has_beauty_shop")
    private Boolean hasBeautyShop;
    @Column(name = "has_studio")
    private Boolean hasStudio;
    @Column(name = "has_notary_office")
    private Boolean hasNotaryOffice;
    @Column(name = "has_pool")
    private Boolean hasPool;
    @Column(name = "has_clothes_studio")
    private Boolean hasClothesStudio;
    @Column(name = "has_warehouse")
    private Boolean hasWarehouse;
    @Column(name = "has_park")
    private Boolean hasPark;
    @Column(name = "has_restaurant")
    private Boolean hasRestaurant;
    @Column(name = "has_fitness_centre")
    private Boolean hasFitnessCentre;
    @Column(name = "has_supermarket")
    private Boolean hasSupermarket;
    @Column(name = "has_minimarket")
    private Boolean hasMinimarket;
    @Column(name = "has_shopping_area")
    private Boolean hasShoppingArea;
    @Column(name = "has_conference_room")
    private Boolean hasConferenceRoom;
    @Column(name = "garage_type")
    private String garageType;
    @Column(name = "land_status")
    private String landStatus;
    @Column(name = "client_fee")
    private Integer clientFee;
    @Column(name = "lease_term_type")
    private String leaseTermType;
    @Column(name = "land_sqr")
    private Double landSqr;
    @Column(name = "land_area")
    private String landArea;
    @Column(name = "price")
    private Double price;
    @Column(name = "cost_ind_tax")
    private Integer costIndTax;
    @Column(name = "subagent_mobile_phone")
    private String subAgentMobilePhone;
    @Column(name = "subagent_email")
    private String subAgentEmail;
    @Column(name = "subagent_name_f")
    private String subAgentNameF;
    @Column(name = "subagent_name_i")
    private String subAgentNameI;
    @Column(name = "subagent_name_o")
    private String subAgentNameO;
    @Column(name = "address")
    private String address;
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
    @Column(name = "flat_rooms_count")
    private String flatRoomsCount;
    @Column(name = "share_amount")
    private String shareAmount;
    @Column(name = "room_area")
    private String roomArea;
    @Column(name = "market_type")
    private String marketType;
    @Transient
    private int pictureCount;

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
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

    public Long getScUnid() {
        return scUnid;
    }

    public void setScUnid(Long scUnid) {
        this.scUnid = scUnid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getFurniturePresence() {
        return furniturePresence;
    }

    public void setFurniturePresence(String furniturePresence) {
        this.furniturePresence = furniturePresence;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Boolean getLegalAddressProvided() {
        return isLegalAddressProvided;
    }

    public void setLegalAddressProvided(Boolean legalAddressProvided) {
        isLegalAddressProvided = legalAddressProvided;
    }

    public Integer getWaterPipesCount() {
        return waterPipesCount;
    }

    public void setWaterPipesCount(Integer waterPipesCount) {
        this.waterPipesCount = waterPipesCount;
    }

    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(String floorsCount) {
        this.floorsCount = floorsCount;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public Double getCeilingHeight() {
        return ceilingHeight;
    }

    public void setCeilingHeight(Double ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public Integer getPlacesCount() {
        return placesCount;
    }

    public void setPlacesCount(Integer placesCount) {
        this.placesCount = placesCount;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getVentilationType() {
        return ventilationType;
    }

    public void setVentilationType(String ventilationType) {
        this.ventilationType = ventilationType;
    }

    public String getConditioningType() {
        return conditioningType;
    }

    public void setConditioningType(String conditioningType) {
        this.conditioningType = conditioningType;
    }

    public String getExtinguishingSystemType() {
        return extinguishingSystemType;
    }

    public void setExtinguishingSystemType(String extinguishingSystemType) {
        this.extinguishingSystemType = extinguishingSystemType;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Boolean getHasCarWash() {
        return hasCarWash;
    }

    public void setHasCarWash(Boolean hasCarWash) {
        this.hasCarWash = hasCarWash;
    }

    public Boolean getHasBuffet() {
        return hasBuffet;
    }

    public void setHasBuffet(Boolean hasBuffet) {
        this.hasBuffet = hasBuffet;
    }

    public Boolean getHasCarService() {
        return hasCarService;
    }

    public void setHasCarService(Boolean hasCarService) {
        this.hasCarService = hasCarService;
    }

    public Boolean getHasCanteen() {
        return hasCanteen;
    }

    public void setHasCanteen(Boolean hasCanteen) {
        this.hasCanteen = hasCanteen;
    }

    public Boolean getHasCentralReception() {
        return hasCentralReception;
    }

    public void setHasCentralReception(Boolean hasCentralReception) {
        this.hasCentralReception = hasCentralReception;
    }

    public Boolean getHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(Boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    public Boolean getHasAtm() {
        return hasAtm;
    }

    public void setHasAtm(Boolean hasAtm) {
        this.hasAtm = hasAtm;
    }

    public Boolean getHasExhibitionAndWarehouseComplex() {
        return hasExhibitionAndWarehouseComplex;
    }

    public void setHasExhibitionAndWarehouseComplex(Boolean hasExhibitionAndWarehouseComplex) {
        this.hasExhibitionAndWarehouseComplex = hasExhibitionAndWarehouseComplex;
    }

    public Boolean getHasPharmacy() {
        return hasPharmacy;
    }

    public void setHasPharmacy(Boolean hasPharmacy) {
        this.hasPharmacy = hasPharmacy;
    }

    public Boolean getHasBankDepartment() {
        return hasBankDepartment;
    }

    public void setHasBankDepartment(Boolean hasBankDepartment) {
        this.hasBankDepartment = hasBankDepartment;
    }

    public Boolean getHasCinema() {
        return hasCinema;
    }

    public void setHasCinema(Boolean hasCinema) {
        this.hasCinema = hasCinema;
    }

    public Boolean getHasCafe() {
        return hasCafe;
    }

    public void setHasCafe(Boolean hasCafe) {
        this.hasCafe = hasCafe;
    }

    public Boolean getHasMedicalCenter() {
        return hasMedicalCenter;
    }

    public void setHasMedicalCenter(Boolean hasMedicalCenter) {
        this.hasMedicalCenter = hasMedicalCenter;
    }

    public Boolean getHasBeautyShop() {
        return hasBeautyShop;
    }

    public void setHasBeautyShop(Boolean hasBeautyShop) {
        this.hasBeautyShop = hasBeautyShop;
    }

    public Boolean getHasStudio() {
        return hasStudio;
    }

    public void setHasStudio(Boolean hasStudio) {
        this.hasStudio = hasStudio;
    }

    public Boolean getHasNotaryOffice() {
        return hasNotaryOffice;
    }

    public void setHasNotaryOffice(Boolean hasNotaryOffice) {
        this.hasNotaryOffice = hasNotaryOffice;
    }

    public Boolean getHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public Boolean getHasClothesStudio() {
        return hasClothesStudio;
    }

    public void setHasClothesStudio(Boolean hasClothesStudio) {
        this.hasClothesStudio = hasClothesStudio;
    }

    public Boolean getHasWarehouse() {
        return hasWarehouse;
    }

    public void setHasWarehouse(Boolean hasWarehouse) {
        this.hasWarehouse = hasWarehouse;
    }

    public Boolean getHasPark() {
        return hasPark;
    }

    public void setHasPark(Boolean hasPark) {
        this.hasPark = hasPark;
    }

    public Boolean getHasRestaurant() {
        return hasRestaurant;
    }

    public void setHasRestaurant(Boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
    }

    public Boolean getHasFitnessCentre() {
        return hasFitnessCentre;
    }

    public void setHasFitnessCentre(Boolean hasFitnessCentre) {
        this.hasFitnessCentre = hasFitnessCentre;
    }

    public Boolean getHasSupermarket() {
        return hasSupermarket;
    }

    public void setHasSupermarket(Boolean hasSupermarket) {
        this.hasSupermarket = hasSupermarket;
    }

    public Boolean getHasMinimarket() {
        return hasMinimarket;
    }

    public void setHasMinimarket(Boolean hasMinimarket) {
        this.hasMinimarket = hasMinimarket;
    }

    public Boolean getHasShoppingArea() {
        return hasShoppingArea;
    }

    public void setHasShoppingArea(Boolean hasShoppingArea) {
        this.hasShoppingArea = hasShoppingArea;
    }

    public Boolean getHasConferenceRoom() {
        return hasConferenceRoom;
    }

    public void setHasConferenceRoom(Boolean hasConferenceRoom) {
        this.hasConferenceRoom = hasConferenceRoom;
    }

    public String getGarageType() {
        return garageType;
    }

    public void setGarageType(String garageType) {
        this.garageType = garageType;
    }

    public String getLandStatus() {
        return landStatus;
    }

    public void setLandStatus(String landStatus) {
        this.landStatus = landStatus;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCostIndTax() {
        return costIndTax;
    }

    public void setCostIndTax(Integer costIndTax) {
        this.costIndTax = costIndTax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubAgentMobilePhone() {
        return subAgentMobilePhone;
    }

    public void setSubAgentMobilePhone(String subAgentMobilePhone) {
        this.subAgentMobilePhone = subAgentMobilePhone;
    }

    public String getSubAgentEmail() {
        return subAgentEmail;
    }

    public void setSubAgentEmail(String subAgentEmail) {
        this.subAgentEmail = subAgentEmail;
    }

    public String getSubAgentNameF() {
        return subAgentNameF;
    }

    public void setSubAgentNameF(String subAgentNameF) {
        this.subAgentNameF = subAgentNameF;
    }

    public String getSubAgentNameI() {
        return subAgentNameI;
    }

    public void setSubAgentNameI(String subAgentNameI) {
        this.subAgentNameI = subAgentNameI;
    }

    public String getSubAgentNameO() {
        return subAgentNameO;
    }

    public void setSubAgentNameO(String subAgentNameO) {
        this.subAgentNameO = subAgentNameO;
    }

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public String getCategoryFeedName() {
        return categoryFeedName;
    }

    public void setCategoryFeedName(String categoryFeedName) {
        this.categoryFeedName = categoryFeedName;
    }

    public Double getLandSqr() {
        return landSqr;
    }

    public void setLandSqr(Double landSqr) {
        this.landSqr = landSqr;
    }

    public Boolean getObjIndRent() {
        return objIndRent;
    }

    public void setObjIndRent(Boolean objIndRent) {
        this.objIndRent = objIndRent;
    }

    public Integer getClientFee() {
        return clientFee;
    }

    public void setClientFee(Integer clientFee) {
        this.clientFee = clientFee;
    }

    public String getLeaseTermType() {
        return leaseTermType;
    }

    public void setLeaseTermType(String leaseTermType) {
        this.leaseTermType = leaseTermType;
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

    public String getFlatRoomsCount() {
        return flatRoomsCount;
    }

    public void setFlatRoomsCount(String flatRoomsCount) {
        this.flatRoomsCount = flatRoomsCount;
    }

    public String getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(String shareAmount) {
        this.shareAmount = shareAmount;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }
}
