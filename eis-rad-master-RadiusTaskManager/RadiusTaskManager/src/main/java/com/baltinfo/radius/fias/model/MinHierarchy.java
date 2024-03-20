package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "min_hierarchy", schema = "fias_gar")
public class MinHierarchy implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "parentobjid")
    private Long parentObjId;

    @Column(name = "changeid")
    private Long changeId;

    @Column(name = "oktmo")
    private String oktmo;

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

    public String getOktmo() {
        return oktmo;
    }

    public void setOktmo(String oktmo) {
        this.oktmo = oktmo;
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
        MinHierarchy that = (MinHierarchy) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MinHierarchy{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", parentObjId=" + parentObjId +
                ", changeId=" + changeId +
                ", oktmo='" + oktmo + '\'' +
                ", prevId=" + prevId +
                ", nextId=" + nextId +
                ", updateDate=" + updateDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}