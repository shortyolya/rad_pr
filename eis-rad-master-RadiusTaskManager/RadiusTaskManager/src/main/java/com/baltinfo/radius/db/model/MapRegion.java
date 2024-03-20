package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "map_region", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mr_unid"})})
@SequenceGenerator(name = "seq_map_region", sequenceName = "seq_map_region", allocationSize = 1)
@XmlRootElement
public class MapRegion {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_map_region")
    @Column(name = "mr_unid", nullable = false, precision = 2147483647, scale = 0)
    private long mrUnid;
    @Column(name = "mr_name", length = 500)
    private String mrName;
    @Column(name = "mr_code", length = 50)
    private String mrCode;
    @Column(name = "mr_lat", length = 20)
    private String mrLat;
    @Column(name = "mr_long", length = 20)
    private String mrLong;
    @Column(name = "mr_delta", length = 50)
    private String mrDelta;
    @Column(name = "mr_path")
    private String mrPath;

    public long getMrUnid() {
        return mrUnid;
    }

    public void setMrUnid(long mrUnid) {
        this.mrUnid = mrUnid;
    }

    public String getMrName() {
        return mrName;
    }

    public void setMrName(String mrName) {
        this.mrName = mrName;
    }

    public String getMrCode() {
        return mrCode;
    }

    public void setMrCode(String mrCode) {
        this.mrCode = mrCode;
    }

    public String getMrLat() {
        return mrLat;
    }

    public void setMrLat(String mrLat) {
        this.mrLat = mrLat;
    }

    public String getMrLong() {
        return mrLong;
    }

    public void setMrLong(String mrLong) {
        this.mrLong = mrLong;
    }

    public String getMrDelta() {
        return mrDelta;
    }

    public void setMrDelta(String mrDelta) {
        this.mrDelta = mrDelta;
    }

    public String getMrPath() {
        return mrPath;
    }

    public void setMrPath(String mrPath) {
        this.mrPath = mrPath;
    }
}
