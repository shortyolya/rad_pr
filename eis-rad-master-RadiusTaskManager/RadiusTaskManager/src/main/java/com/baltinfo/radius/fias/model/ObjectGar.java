package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "object", schema = "fias_gar")
public class ObjectGar implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "objectguid")
    private String objectGuid;

    @Column(name = "changeid")
    private Long changeId;

    @Column(name = "name")
    private String name;

    @Column(name = "typename")
    private String typeName;

    @Column(name = "level")
    private String level;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        ObjectGar objectGar = (ObjectGar) o;
        return id.equals(objectGar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ObjectGar{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", objectGuid='" + objectGuid + '\'' +
                ", changeId=" + changeId +
                ", name='" + name + '\'' +
                ", typeName='" + typeName + '\'' +
                ", level='" + level + '\'' +
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