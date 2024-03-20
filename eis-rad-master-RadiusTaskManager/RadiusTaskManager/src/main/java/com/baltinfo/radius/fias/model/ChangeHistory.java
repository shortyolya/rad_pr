package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "change_history", schema = "fias_gar")
public class ChangeHistory implements FiasModel {

    @Id
    @Column(name = "changeid")
    private Integer id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "adrobjectid")
    private String adrobjectid;

    @Column(name = "opertypeid")
    private Long operTypeId;

    @Column(name = "ndocid")
    private Long ndocId;

    @Column(name = "changedate")
    private Date changeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdrobjectid() {
        return adrobjectid;
    }

    public void setAdrobjectid(String adrobjectid) {
        this.adrobjectid = adrobjectid;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(Long operTypeId) {
        this.operTypeId = operTypeId;
    }

    public Long getNdocId() {
        return ndocId;
    }

    public void setNdocId(Long ndocId) {
        this.ndocId = ndocId;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeHistory that = (ChangeHistory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ChangeHistory{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", adrobjectid='" + adrobjectid + '\'' +
                ", operTypeId=" + operTypeId +
                ", ndocId=" + ndocId +
                ", changeDate=" + changeDate +
                '}';
    }

}