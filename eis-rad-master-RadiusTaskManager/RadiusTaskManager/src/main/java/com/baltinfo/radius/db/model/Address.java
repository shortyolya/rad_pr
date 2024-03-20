package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "address", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"addr_unid"})})
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address", allocationSize = 1)
@XmlRootElement
public class Address implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    @Column(name = "addr_unid", nullable = false, precision = 2147483647, scale = 0)
    private long addrUnid;
    @Column(name = "okcm_unid")
    private Long okcmUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b")
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "addr_address")
    private String addrAddress;
    @Column(name = "addr_fias_id")
    private String addrFiasId;
    @Column(name = "addr_house")
    private String addrHouse;
    @Column(name = "addr_index")
    private String addrIndex;
    @Column(name = "addr_corp")
    private String addrCorp;
    @Column(name = "addr_build")
    private String addrBuild;
    @Column(name = "addr_ind_build")
    private BigInteger addrIndBuild;
    @Column(name = "addr_flat")
    private String addrFlat;
    @Column(name = "addr_lat")
    private String addrLat;
    @Column(name = "addr_long")
    private String addrLong;
    @Column(name = "addr_note")
    private String addrNote;
    @Column(name = "addr_input_mode")
    private Long addrInputMode;
    @Column(name = "addr_region_id")
    private String addrRegionId;
    @Column(name = "addr_auto_id")
    private String addrAutoId;
    @Column(name = "addr_area_id")
    private String addrAreaId;
    @Column(name = "addr_city_id")
    private String addrCityId;
    @Column(name = "addr_ctar_id")
    private String addrCtarId;
    @Column(name = "addr_place_id")
    private String addrPlaceId;
    @Column(name = "addr_street_id")
    private String addrStreetId;
    @Column(name = "addr_region_name")
    private String addrRegionName;
    @Column(name = "addr_region_code")
    private String addrRegionCode;
    @Column(name = "addr_district")
    private String addrDistrict;

    @JoinColumn(name = "obj_unid", referencedColumnName = "obj_unid")
    @ManyToOne
    private ObjectJPA objUnid;

    public Address() {
    }

    public Address(long addrUnid) {
        this.addrUnid = addrUnid;
    }

    public long getAddrUnid() {
        return addrUnid;
    }

    public void setAddrUnid(long addrUnid) {
        this.addrUnid = addrUnid;
    }

    public Long getOkcmUnid() {
        return okcmUnid;
    }

    public void setOkcmUnid(Long okcmUnid) {
        this.okcmUnid = okcmUnid;
    }

    public Integer getIndActual() {
        return indActual;
    }

    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public String getFoundB() {
        return foundB;
    }

    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    public Long getPersCodeB() {
        return persCodeB;
    }

    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    public Date getDateBRec() {
        return dateBRec;
    }

    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getAddrAddress() {
        return addrAddress;
    }

    public void setAddrAddress(String addrAddress) {
        this.addrAddress = addrAddress;
    }

    public String getAddrFiasId() {
        return addrFiasId;
    }

    public void setAddrFiasId(String addrFiasId) {
        this.addrFiasId = addrFiasId;
    }

    public String getAddrHouse() {
        return addrHouse;
    }

    public void setAddrHouse(String addrHouse) {
        this.addrHouse = addrHouse;
    }

    public String getAddrIndex() {
        return addrIndex;
    }

    public void setAddrIndex(String addrIndex) {
        this.addrIndex = addrIndex;
    }

    public String getAddrCorp() {
        return addrCorp;
    }

    public void setAddrCorp(String addrCorp) {
        this.addrCorp = addrCorp;
    }

    public String getAddrBuild() {
        return addrBuild;
    }

    public void setAddrBuild(String addrBuild) {
        this.addrBuild = addrBuild;
    }

    public BigInteger getAddrIndBuild() {
        return addrIndBuild;
    }

    public void setAddrIndBuild(BigInteger addrIndBuild) {
        this.addrIndBuild = addrIndBuild;
    }

    public String getAddrFlat() {
        return addrFlat;
    }

    public void setAddrFlat(String addrFlat) {
        this.addrFlat = addrFlat;
    }

    public String getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(String addrLat) {
        this.addrLat = addrLat;
    }

    public String getAddrLong() {
        return addrLong;
    }

    public void setAddrLong(String addrLong) {
        this.addrLong = addrLong;
    }

    public ObjectJPA getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(ObjectJPA objUnid) {
        this.objUnid = objUnid;
    }

    public String getAddrRegionId() {
        return addrRegionId;
    }

    public void setAddrRegionId(String addrRegionId) {
        this.addrRegionId = addrRegionId;
    }


    public String getAddrNote() {
        return addrNote;
    }

    public void setAddrNote(String addrNote) {
        this.addrNote = addrNote;
    }

    public Long getAddrInputMode() {
        return addrInputMode;
    }

    public void setAddrInputMode(Long addrInputMode) {
        this.addrInputMode = addrInputMode;
    }

    public String getAddrAutoId() {
        return addrAutoId;
    }

    public void setAddrAutoId(String addrAutoId) {
        this.addrAutoId = addrAutoId;
    }

    public String getAddrAreaId() {
        return addrAreaId;
    }

    public void setAddrAreaId(String addrAreaId) {
        this.addrAreaId = addrAreaId;
    }

    public String getAddrCityId() {
        return addrCityId;
    }

    public void setAddrCityId(String addrCityId) {
        this.addrCityId = addrCityId;
    }

    public String getAddrCtarId() {
        return addrCtarId;
    }

    public void setAddrCtarId(String addrCtarId) {
        this.addrCtarId = addrCtarId;
    }

    public String getAddrPlaceId() {
        return addrPlaceId;
    }

    public void setAddrPlaceId(String addrPlaceId) {
        this.addrPlaceId = addrPlaceId;
    }

    public String getAddrStreetId() {
        return addrStreetId;
    }

    public void setAddrStreetId(String addrStreetId) {
        this.addrStreetId = addrStreetId;
    }

    public String getAddrRegionName() { return addrRegionName;  }

    public void setAddrRegionName(String addrRegionName) {
        this.addrRegionName = addrRegionName;
    }

    public String getAddrRegionCode() {
        return addrRegionCode;
    }

    public void setAddrRegionCode(String addrRegionCode) {
        this.addrRegionCode = addrRegionCode;
    }

    public String getAddrDistrict() {
        return addrDistrict;
    }

    public void setAddrDistrict(String addrDistrict) {
        this.addrDistrict = addrDistrict;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Address[ addrUnid=" + addrUnid + " ]";
    }

}
