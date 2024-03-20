package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "obj_division", schema = "fias_gar")
public class ObjDivision implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "parentid")
    private Long parentId;

    @Column(name = "childid")
    private Long childId;

    @Column(name = "changeid")
    private Integer changeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjDivision that = (ObjDivision) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ObjDivision{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", childId=" + childId +
                ", changeId=" + changeId +
                '}';
    }
}