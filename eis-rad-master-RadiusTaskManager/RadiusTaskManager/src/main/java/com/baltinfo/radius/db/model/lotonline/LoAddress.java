package com.baltinfo.radius.db.model.lotonline;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Suvorina Aleksandra
 * @since 16.12.2020
 */
@Entity
@Table(name = "address", catalog = "", schema = "")
@XmlRootElement
public class LoAddress {

    @Id
    @GeneratedValue(generator = "seq_address_id")
    @GenericGenerator(name = "seq_address_id",
            strategy = "com.baltinfo.radius.db.model.lotonline.LotOnlineShard3SequnceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "seq_address_id"))
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id")
    private LotInfo lotInfo;

    @Column(name = "address")
    private String address;

    @Column(name = "fias_id")
    private String fiasId;

    @Column(name = "house")
    private String house;

    @Column(name = "index")
    private String index;

    @Column(name = "corp")
    private String corp;

    @Column(name = "build")
    private String build;

    @Column(name = "ind_build")
    private Boolean indBuild;

    @Column(name = "flat")
    private String flat;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "note")
    private String note;

    @Column(name = "region_id")
    private String regionId;

    @Column(name = "auto_id")
    private String autoId;

    @Column(name = "area_id")
    private String areaId;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "ctar_id")
    private String ctarId;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "street_id")
    private String streetId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFiasId() {
        return fiasId;
    }

    public void setFiasId(String fiasId) {
        this.fiasId = fiasId;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Boolean getIndBuild() {
        return indBuild;
    }

    public void setIndBuild(Boolean indBuild) {
        this.indBuild = indBuild;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCtarId() {
        return ctarId;
    }

    public void setCtarId(String ctarId) {
        this.ctarId = ctarId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LotInfo getLotInfo() {
        return lotInfo;
    }

    public void setLotInfo(LotInfo lotInfo) {
        this.lotInfo = lotInfo;
    }
}
