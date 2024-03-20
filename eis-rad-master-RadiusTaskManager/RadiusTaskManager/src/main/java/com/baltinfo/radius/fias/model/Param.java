package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "param", schema = "fias_gar")
public class Param implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "objectid")
    private Integer objectId;

    @Column(name = "changeid")
    private Integer changeId;

    @Column(name = "typeid")
    private Integer typeId;

    @Column(name = "value")
    private String value;

    @Column(name = "updatedate")
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param param = (Param) o;
        return id.equals(param.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Param{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", changeId=" + changeId +
                ", typeId=" + typeId +
                ", value='" + value + '\'' +
                ", updateDate=" + updateDate +
                '}';
    }
}