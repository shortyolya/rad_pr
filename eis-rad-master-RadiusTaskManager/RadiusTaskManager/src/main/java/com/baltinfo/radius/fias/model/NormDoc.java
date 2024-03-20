package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "normdoc", schema = "fias_gar")
public class NormDoc implements FiasModel {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private Integer type;

    @Column(name = "kind")
    private Integer kind;

    @Column(name = "updatedate")
    private Date updateDate;

    @Column(name = "orgname")
    private String orgName;

    @Column(name = "regnum")
    private String regNum;

    @Column(name = "regdate")
    private Date regDate;

    @Column(name = "accdate")
    private Date accDate;

    @Column(name = "comment")
    private String comment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date accDate) {
        this.accDate = accDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormDoc normDoc = (NormDoc) o;
        return Objects.equals(id, normDoc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NormDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", kind=" + kind +
                ", updateDate=" + updateDate +
                ", orgName='" + orgName + '\'' +
                ", regNum='" + regNum + '\'' +
                ", regDate=" + regDate +
                ", accDate=" + accDate +
                ", comment='" + comment + '\'' +
                '}';
    }
}