package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "house", schema = "fias_gar")
public class House implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "objectguid")
    private String objectGuid;

    @Column(name = "changeid")
    private Long changeId;

    @Column(name = "housenum")
    private String houseNum;

    @Column(name = "addnum1")
    private String addNum1;

    @Column(name = "addnum2")
    private String addNum2;

    @Column(name = "housetype")
    private Integer houseType;

    @Column(name = "addtype1")
    private Integer addType1;

    @Column(name = "addtype2")
    private Integer addType2;

    @Column(name = "opertypeid")
    private Integer operTypeId;

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

    @Column(name = "isactual")
    private Integer isActual;

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

    public String getObjectGuid() {
        return objectGuid;
    }

    public void setObjectGuid(String objectGuid) {
        this.objectGuid = objectGuid;
    }

    public Long getChangeId() {
        return changeId;
    }

    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getAddNum1() {
        return addNum1;
    }

    public void setAddNum1(String addNum1) {
        this.addNum1 = addNum1;
    }

    public String getAddNum2() {
        return addNum2;
    }

    public void setAddNum2(String addNum2) {
        this.addNum2 = addNum2;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public Integer getAddType1() {
        return addType1;
    }

    public void setAddType1(Integer addType1) {
        this.addType1 = addType1;
    }

    public Integer getAddType2() {
        return addType2;
    }

    public void setAddType2(Integer addType2) {
        this.addType2 = addType2;
    }

    public Integer getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(Integer operTypeId) {
        this.operTypeId = operTypeId;
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

    public Integer getIsActual() {
        return isActual;
    }

    public void setIsActual(Integer isActual) {
        this.isActual = isActual;
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
        House house = (House) o;
        return id.equals(house.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", objectGuid='" + objectGuid + '\'' +
                ", changeId=" + changeId +
                ", houseNum='" + houseNum + '\'' +
                ", addNum1='" + addNum1 + '\'' +
                ", addNum2='" + addNum2 + '\'' +
                ", houseType=" + houseType +
                ", addType1=" + addType1 +
                ", addType2=" + addType2 +
                ", operTypeId=" + operTypeId +
                ", prevId=" + prevId +
                ", nextId=" + nextId +
                ", updateDate=" + updateDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActual=" + isActual +
                ", isActive=" + isActive +
                '}';
    }
}