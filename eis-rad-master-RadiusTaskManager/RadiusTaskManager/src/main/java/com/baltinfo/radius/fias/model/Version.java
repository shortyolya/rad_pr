package com.baltinfo.radius.fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "version", schema = "fias_gar")
public class Version {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "verdate")
    private Date date;

    @Column(name = "loadsign")
    private Integer loadSign;

    @Column(name = "loaderror")
    private String error;

    public Version() {
    }

    public Version(Long id, Date date, Integer loadSign) {
        this.id = id;
        this.date = date;
        this.loadSign = loadSign;
    }

    public Version(Long id, Date date, Integer loadSign, String error) {
        this.id = id;
        this.date = date;
        this.loadSign = loadSign;
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLoadSign() {
        return loadSign;
    }

    public void setLoadSign(Integer loadSign) {
        this.loadSign = loadSign;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version carPlace = (Version) o;
        return Objects.equals(id, carPlace.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", date=" + date +
                ", loadSign=" + loadSign +
                ", error='" + error + '\'' +
                '}';
    }
}