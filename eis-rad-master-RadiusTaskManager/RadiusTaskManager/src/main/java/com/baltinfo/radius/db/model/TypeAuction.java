package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "type_auction", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type_auction_unid"})})
@SequenceGenerator(name = "seq_type_auction", sequenceName = "seq_type_auction", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TypeAuction.findAll", query = "SELECT t FROM TypeAuction t WHERE t.indActual = 1"),
        @NamedQuery(name = "TypeAuction.findByTypeAuctionUnid", query = "SELECT t FROM TypeAuction t WHERE t.typeAuctionUnid = :typeAuctionUnid"),
        @NamedQuery(name = "TypeAuction.findByTypeAuctionCode", query = "SELECT t FROM TypeAuction t WHERE t.typeAuctionCode = :typeAuctionCode")})
public class TypeAuction implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_auction")
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
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
    @Column(name = "type_auction_name")
    private String typeAuctionName;
    @Column(name = "type_auction_sname")
    private String typeAuctionSname;
    @Basic(optional = false)
    @Column(name = "type_auction_code")
    private Long typeAuctionCode;
    @Column(name = "type_auction_style")
    private String typeAuctionStyle;

    public TypeAuction() {
    }

    public Long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(Long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    @Override
    public Integer getIndActual() {
        return indActual;
    }

    @Override
    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    @Override
    public Date getDateB() {
        return dateB;
    }

    @Override
    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    @Override
    public String getFoundB() {
        return foundB;
    }

    @Override
    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    @Override
    public Long getPersCodeB() {
        return persCodeB;
    }

    @Override
    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    @Override
    public Date getDateBRec() {
        return dateBRec;
    }

    @Override
    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    @Override
    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    @Override
    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getTypeAuctionName() {
        return typeAuctionName;
    }

    public void setTypeAuctionName(String typeAuctionName) {
        this.typeAuctionName = typeAuctionName;
    }

    public String getTypeAuctionSname() {
        return typeAuctionSname;
    }

    public void setTypeAuctionSname(String typeAuctionSname) {
        this.typeAuctionSname = typeAuctionSname;
    }

    public Long getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(Long typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
    }

    public String getTypeAuctionStyle() {
        return typeAuctionStyle;
    }

    public void setTypeAuctionStyle(String typeAuctionStyle) {
        this.typeAuctionStyle = typeAuctionStyle;
    }
}
