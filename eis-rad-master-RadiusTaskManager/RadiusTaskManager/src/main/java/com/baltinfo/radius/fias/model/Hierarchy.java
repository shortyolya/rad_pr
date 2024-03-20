package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "hierarchy", schema = "fias_gar")
public class Hierarchy implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "parentobjid")
    private Long parentObjId;

    @Column(name = "changeid")
    private Long changeId;

    @Column(name = "regioncode")
    private String regionCode;

    @Column(name = "areacode")
    private String areaCode;

    @Column(name = "citycode")
    private String cityCode;

    @Column(name = "placecode")
    private String placeCode;

    @Column(name = "plancode")
    private String planCode;

    @Column(name = "streetcode")
    private String streetCode;

    @Column(name = "previd")
    private Long prevId;

    @Column(name = "nextid")
    private Long nextId;

    @Column(name = "updatedate")
    private Date updateDate;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "isactive")
    private Integer isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getParentObjId() {
        return parentObjId;
    }

    public void setParentObjId(Long parentObjId) {
        this.parentObjId = parentObjId;
    }

    public Long getChangeId() {
        return changeId;
    }

    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public Long getPrevId() {
        return prevId;
    }

    public void setPrevId(Long prevId) {
        this.prevId = prevId;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hierarchy hierarchy = (Hierarchy) o;
        return id.equals(hierarchy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Hierarchy{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", parentObjId=" + parentObjId +
                ", changeId=" + changeId +
                ", regionCode='" + regionCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", planCode='" + planCode + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", prevId=" + prevId +
                ", nextId=" + nextId +
                ", updateDate=" + updateDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}