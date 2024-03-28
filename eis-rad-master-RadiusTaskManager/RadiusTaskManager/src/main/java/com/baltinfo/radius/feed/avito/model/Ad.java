package com.baltinfo.radius.feed.avito.model;

import com.baltinfo.radius.feed.utils.DoubleExponentAdapter;
import com.baltinfo.radius.feed.utils.FractionalPartDeleteAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "address",
        "description",
        "category",
        "operationType",
        "price",
        "rooms",
        "square",
        "floor",
        "floors",
        "houseType",
        "marketType",
        "propertyRights",
        "images",
        "title",
        "objectType",
        "wallsType",
        "landArea",
        "objectSubtype",
        "secured",
        "goodsType",
        "goodsSubType",
        "condition",
        "vehicleType",
        "typeId",
        "managerName",
        "contactPhone",
        "carType",
        "make",
        "model",
        "year",
        "kilometrage",
        "accident",
        "vin",
        "bodyType",
        "doors",
        "generationId",
        "modificationId",
        "color",
        "fuelType",
        "engineSize",
        "power",
        "transmission",
        "driveType",
        "wheelType",
        "owners",
        "technicalPassport",
        "status",
        "allowEmail",
        "listingFee",
        "leaseType",
        "leaseCommissionSize",
        "leaseDeposit",
        "newDevelopmentId",
        "decoration",
        "kitchenSpace",
        "balconyOrLoggiaMulti",
        "dealType",
        "dealGoal",
        "roomType",
        "bathroomMulti",
        "landStatus",
        "saleOptions",
        "buildingType",
        "renovation",
        "parkingType",
        "entrance",
        "layout",
        "transactionType",
        "rentalType",
        "availability",
        "typeOfVehicle",
        "typeOfTrailer",
        "goodsPromType",
        "furnitureType",
        "cashierType",
        "advertisingType",
        "pavilionsType",
        "entertainingType",
        "inventoryType",
        "privodType",
        "electricType",
        "stanokType",
        "izmerenieType",
        "konveierType",
        "svarkaType",
        "nasosType",
        "klimatType",
        "packType",
        "specType"
})
public class Ad {

    @XmlElement(name = "Id")
    protected String id;
    @XmlElement(name = "Address")
    protected String address;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "OperationType", defaultValue = "Продам")
    protected String operationType;
    @XmlElement(name = "Price")
    @XmlJavaTypeAdapter(type = Double.class, value = FractionalPartDeleteAdapter.class)
    protected Double price;
    @XmlElement(name = "Rooms")
    protected String rooms;
    @XmlElement(name = "Square")
    @XmlJavaTypeAdapter(type = Double.class, value = DoubleExponentAdapter.class)
    protected Double square;
    @XmlElement(name = "Floor")
    protected String floor;
    @XmlElement(name = "Floors")
    protected Integer floors;
    @XmlElement(name = "HouseType")
    protected String houseType;
    @XmlElement(name = "MarketType")
    protected String marketType;
    @XmlElement(name = "PropertyRights", defaultValue = "Посредник")
    protected String propertyRights;
    @XmlElement(name = "Images")
    protected Images images;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "ObjectType")
    protected String objectType;
    @XmlElement(name = "WallsType")
    protected String wallsType;
    @XmlElement(name = "LandArea")
    @XmlJavaTypeAdapter(type = Double.class, value = DoubleExponentAdapter.class)
    protected Double landArea;
    @XmlElement(name = "SaleOptions")
    protected String saleOptions;
    @XmlElement(name = "ObjectSubtype")
    protected String objectSubtype;
    @XmlElement(name = "Secured")
    protected String secured;
    @XmlElement(name = "GoodsType")
    protected String goodsType;
    @XmlElement(name = "GoodsSubType")
    protected String goodsSubType;
    @XmlElement(name = "Condition")
    protected String condition;
    @XmlElement(name = "VehicleType")
    protected String vehicleType;
    @XmlElement(name = "TypeId")
    protected String typeId;
    @XmlElement(name = "ManagerName")
    protected String managerName;
    @XmlElement(name = "ContactPhone")
    protected String contactPhone;
    @XmlElement(name = "CarType")
    protected String carType;
    @XmlElement(name = "Make")
    protected String make;
    @XmlElement(name = "Model")
    protected String model;
    @XmlElement(name = "Year")
    @XmlJavaTypeAdapter(type = Double.class, value = FractionalPartDeleteAdapter.class)
    protected Double year;
    @XmlElement(name = "Kilometrage")
    @XmlJavaTypeAdapter(type = Double.class, value = FractionalPartDeleteAdapter.class)
    protected Double kilometrage;
    @XmlElement(name = "Accident")
    protected String accident;
    @XmlElement(name = "VIN")
    protected String vin;
    @XmlElement(name = "BodyType")
    protected String bodyType;
    @XmlElement(name = "Doors")
    protected Long doors;
    @XmlElement(name = "GenerationId")
    protected Long generationId;
    @XmlElement(name = "ModificationId")
    protected Long modificationId;
    @XmlElement(name = "Color")
    protected String color;
    @XmlElement(name = "FuelType")
    protected String fuelType;
    @XmlElement(name = "EngineSize")
    protected Double engineSize;
    @XmlElement(name = "Power")
    protected Long power;
    @XmlElement(name = "Transmission")
    protected String transmission;
    @XmlElement(name = "DriveType")
    protected String driveType;
    @XmlElement(name = "WheelType")
    protected String wheelType;
    @XmlElement(name = "Owners")
    @XmlJavaTypeAdapter(type = Double.class, value = FractionalPartDeleteAdapter.class)
    protected Double owners;
    @XmlElement(name = "TechnicalPassport")
    protected String technicalPassport;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "AllowEmail")
    protected String allowEmail;
    @XmlElement(name = "ListingFee")
    protected String listingFee;
    @XmlElement(name = "LeaseType")
    protected String leaseType;
    @XmlElement(name = "LeaseCommissionSize")
    protected Integer leaseCommissionSize;
    @XmlElement(name = "LeaseDeposit")
    protected String leaseDeposit;
    @XmlElement(name = "NewDevelopmentId")
    protected Long newDevelopmentId;
    @XmlElement(name = "Decoration")
    protected String decoration;
    @XmlJavaTypeAdapter(type = Double.class, value = DoubleExponentAdapter.class)
    @XmlElement(name = "KitchenSpace")
    protected Double kitchenSpace;
    @XmlElement(name = "BalconyOrLoggiaMulti")
    protected String balconyOrLoggiaMulti;
    @XmlElement(name = "DealType")
    protected String dealType;
    @XmlElement(name = "DealGoal")
    protected String dealGoal;
    @XmlElement(name = "RoomType")
    protected String roomType;
    @XmlElement(name = "BathroomMulti")
    protected String bathroomMulti;
    @XmlElement(name = "LandStatus")
    protected String landStatus;
    @XmlElement(name = "BuildingType")
    protected String buildingType;
    @XmlElement(name = "Renovation")
    protected String renovation;
    @XmlElement(name = "ParkingType")
    protected String parkingType;
    @XmlElement(name = "Entrance")
    protected String entrance;
    @XmlElement(name = "Layout")
    protected String layout;
    @XmlElement(name = "TransactionType")
    protected String transactionType;
    @XmlElement(name = "RentalType")
    protected String rentalType;
    @XmlElement(name = "Availability")
    protected String availability;
    @XmlElement(name = "TypeOfVehicle")
    protected String typeOfVehicle;
    @XmlElement(name = "TypeOfTrailer")
    protected String typeOfTrailer;
    @XmlElement(name = "GoodsPromType")
    protected String goodsPromType;
    @XmlElement(name = "FurnitureType")
    protected String furnitureType;
    @XmlElement(name = "CashierType")
    protected String cashierType;
    @XmlElement(name = "AdvertisingType")
    protected String advertisingType;
    @XmlElement(name = "PavilionsType")
    protected String pavilionsType;
    @XmlElement(name = "EntertainingType")
    protected String entertainingType;
    @XmlElement(name = "InventoryType")
    protected String inventoryType;
    @XmlElement(name = "PrivodType")
    protected String privodType;
    @XmlElement(name = "ElectricType")
    protected String electricType;
    @XmlElement(name = "StanokType")
    protected String stanokType;
    @XmlElement(name = "IzmerenieType")
    protected String izmerenieType;
    @XmlElement(name = "KonveierType")
    protected String konveierType;
    @XmlElement(name = "SvarkaType")
    protected String svarkaType;
    @XmlElement(name = "NasosType")
    protected String nasosType;
    @XmlElement(name = "KlimatType")
    protected String klimatType;
    @XmlElement(name = "PackType")
    protected String packType;
    @XmlElement(name = "SpecType")
    protected String specType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getPropertyRights() {
        return propertyRights;
    }

    public void setPropertyRights(String propertyRights) {
        this.propertyRights = propertyRights;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getWallsType() {
        return wallsType;
    }

    public void setWallsType(String wallsType) {
        this.wallsType = wallsType;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public String getSaleOptions() {
        return saleOptions;
    }

    public void setSaleOptions(String saleOptions) {
        this.saleOptions = saleOptions;
    }

    public String getObjectSubtype() {
        return objectSubtype;
    }

    public void setObjectSubtype(String objectSubtype) {
        this.objectSubtype = objectSubtype;
    }

    public String getSecured() {
        return secured;
    }

    public void setSecured(String secured) {
        this.secured = secured;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
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

    public Long getDoors() {
        return doors;
    }

    public void setDoors(Long doors) {
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

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
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

    public Double getOwners() {
        return owners;
    }

    public void setOwners(Double owners) {
        this.owners = owners;
    }

    public String getTechnicalPassport() {
        return technicalPassport;
    }

    public void setTechnicalPassport(String technicalPassport) {
        this.technicalPassport = technicalPassport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAllowEmail() {
        return allowEmail;
    }

    public void setAllowEmail(String allowEmail) {
        this.allowEmail = allowEmail;
    }

    public String getListingFee() {
        return listingFee;
    }

    public void setListingFee(String listingFee) {
        this.listingFee = listingFee;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public Integer getLeaseCommissionSize() {
        return leaseCommissionSize;
    }

    public void setLeaseCommissionSize(Integer leaseCommissionSize) {
        this.leaseCommissionSize = leaseCommissionSize;
    }

    public String getLeaseDeposit() {
        return leaseDeposit;
    }

    public void setLeaseDeposit(String leaseDeposit) {
        this.leaseDeposit = leaseDeposit;
    }

    public Long getNewDevelopmentId() {
        return newDevelopmentId;
    }

    public void setNewDevelopmentId(Long newDevelopmentId) {
        this.newDevelopmentId = newDevelopmentId;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public Double getKitchenSpace() {
        return kitchenSpace;
    }

    public void setKitchenSpace(Double kitchenSpace) {
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

    public String getDealGoal() {
        return dealGoal;
    }

    public void setDealGoal(String dealGoal) {
        this.dealGoal = dealGoal;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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

    public String getGoodsPromType() {
        return goodsPromType;
    }

    public void setGoodsPromType(String goodsPromType) {
        this.goodsPromType = goodsPromType;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getCashierType() {
        return cashierType;
    }

    public void setCashierType(String cashierType) {
        this.cashierType = cashierType;
    }

    public String getAdvertisingType() {
        return advertisingType;
    }

    public void setAdvertisingType(String advertisingType) {
        this.advertisingType = advertisingType;
    }

    public String getPavilionsType() {
        return pavilionsType;
    }

    public void setPavilionsType(String pavilionsType) {
        this.pavilionsType = pavilionsType;
    }

    public String getEntertainingType() {
        return entertainingType;
    }

    public void setEntertainingType(String entertainingType) {
        this.entertainingType = entertainingType;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getPrivodType() {
        return privodType;
    }

    public void setPrivodType(String privodType) {
        this.privodType = privodType;
    }

    public String getElectricType() {
        return electricType;
    }

    public void setElectricType(String electricType) {
        this.electricType = electricType;
    }

    public String getStanokType() {
        return stanokType;
    }

    public void setStanokType(String stanokType) {
        this.stanokType = stanokType;
    }

    public String getIzmerenieType() {
        return izmerenieType;
    }

    public void setIzmerenieType(String izmerenieType) {
        this.izmerenieType = izmerenieType;
    }

    public String getKonveierType() {
        return konveierType;
    }

    public void setKonveierType(String konveierType) {
        this.konveierType = konveierType;
    }

    public String getSvarkaType() {
        return svarkaType;
    }

    public void setSvarkaType(String svarkaType) {
        this.svarkaType = svarkaType;
    }

    public String getNasosType() {
        return nasosType;
    }

    public void setNasosType(String nasosType) {
        this.nasosType = nasosType;
    }

    public String getKlimatType() {
        return klimatType;
    }

    public void setKlimatType(String klimatType) {
        this.klimatType = klimatType;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }
}
