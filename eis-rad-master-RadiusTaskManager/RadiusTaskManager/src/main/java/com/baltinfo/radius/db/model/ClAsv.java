package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 30.10.2018
 */
@Entity
@Table(name = "cl_asv", catalog = "", schema = "web")
@NamedQueries({
        @NamedQuery(name = "ClAsv.findByCaName", query = "SELECT t FROM ClAsv t " +
                "WHERE trim(lower(t.caName)) = trim(lower(:caName))"),
        @NamedQuery(name = "ClAsv.findByCaUnid", query = "SELECT t FROM ClAsv t WHERE t.caUnid = :caUnid"),
        @NamedQuery(name = "ClAsv.findByCaCode", query = "SELECT t FROM ClAsv t WHERE t.caCode = :caCode"),
        @NamedQuery(name = "ClAsv.getAll", query = "SELECT t FROM ClAsv t")
})
@XmlRootElement
public class ClAsv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ca_unid", nullable = false)
    private Long caUnid;
    @Column(name = "ca_name")
    private String caName;
    @Column(name = "ca_code")
    private Long caCode;
    @Column(name = "ca_type")
    private short caType;
    @JoinColumn(name = "sc_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private SaleCategory scUnid;
    @JoinColumn(name = "to_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeObject toUnid;

    public Long getCaUnid() {
        return caUnid;
    }

    public void setCaUnid(Long caUnid) {
        this.caUnid = caUnid;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public Long getCaCode() {
        return caCode;
    }

    public void setCaCode(Long caCode) {
        this.caCode = caCode;
    }

    public short getCaType() {
        return caType;
    }

    public void setCaType(short caType) {
        this.caType = caType;
    }

    public SaleCategory getScUnid() {
        return scUnid;
    }

    public void setScUnid(SaleCategory scUnid) {
        this.scUnid = scUnid;
    }

    public TypeObject getToUnid() {
        return toUnid;
    }

    public void setToUnid(TypeObject toUnid) {
        this.toUnid = toUnid;
    }
}
