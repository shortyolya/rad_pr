package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *  Право требования
 *  Аналогичный entity из Radius
 *
 *  @author Maxim Kuznetsov
 *  @since 29.01.2020
 */
@Entity
@Table(name = "right_claim", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rc_unid"})})
@SequenceGenerator(name = "seq_right_claim", sequenceName = "seq_right_claim", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.RightClaim.none", attributeNodes = {
        }),
        @NamedEntityGraph(name = "graph.RightClaim.all", attributeNodes = {
                @NamedAttributeNode("subUnid"),
                @NamedAttributeNode("subSubUnid")
        })
})
@XmlRootElement
public class RightClaim implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_right_claim")
    @Column(name = "rc_unid", nullable = false, precision = 2147483647, scale = 0)
    private long rcUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "rc_sum")
    private BigDecimal rcSum;
    @Column(name = "rc_note", length = 2147483647)
    private String rcNote;
    @Column(name = "rc_provision", length = 2147483647)
    private String rcProvision;
    @Column(name = "rc_date")
    @Temporal(TemporalType.DATE)
    private Date rcDate;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subSubUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
//    @Transient
//    private List<RightClaimFound> rcfList;


    public RightClaim() {
    }

    public RightClaim(RightClaim sourceRightClaim) {
        this.rcSum = sourceRightClaim.rcSum;
        this.rcNote = sourceRightClaim.rcNote;
        this.rcProvision = sourceRightClaim.rcProvision;
        this.rcDate = sourceRightClaim.rcDate;
        this.subUnid = sourceRightClaim.subUnid;
        this.subSubUnid = sourceRightClaim.subSubUnid;
    }

    public RightClaim(long rcUnid) {
        this.rcUnid = rcUnid;
    }

    public long getRcUnid() {
        return rcUnid;
    }

    public void setRcUnid(long rcUnid) {
        this.rcUnid = rcUnid;
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

    public BigDecimal getRcSum() {
        return rcSum;
    }

    public void setRcSum(BigDecimal rcSum) {
        this.rcSum = rcSum;
    }

    public String getRcNote() {
        return rcNote;
    }

    public void setRcNote(String rcNote) {
        this.rcNote = rcNote;
    }

    public String getRcProvision() {
        return rcProvision;
    }

    public void setRcProvision(String rcProvision) {
        this.rcProvision = rcProvision;
    }

    public Date getRcDate() {
        return rcDate;
    }

    public void setRcDate(Date rcDate) {
        this.rcDate = rcDate;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Subject getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Subject subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.RightClaim[ rcUnid=" + rcUnid + " ]";
    }

}
