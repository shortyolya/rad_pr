package com.baltinfo.radius.db.model.dep;


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
import javax.persistence.NamedSubgraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "pay_doc_notice", catalog = "", schema = "dep", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pdn_unid"})})
@SequenceGenerator(name = "seq_pay_doc_notice", sequenceName = "seq_pay_doc_notice", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.PayDocNotice.payDoc", attributeNodes = {
                @NamedAttributeNode("payDocUnid")
        })
})
@XmlRootElement
public class PayDocNotice {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pay_doc_notice")
    @Column(name = "pdn_unid")
    private Long pdnUnid;
    @JoinColumn(name = "pay_doc_unid", referencedColumnName = "pay_doc_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private PayDoc payDocUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "tpdm_unid")
    private Long tpdmUnid;
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
    @Column(name = "pdn_status")
    private Integer pdnStatus;
    @Column(name = "pdn_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdnDate;
    @Column(name = "pdn_error")
    private String pdnError;

    public Long getPdnUnid() {
        return pdnUnid;
    }

    public void setPdnUnid(Long pdnUnid) {
        this.pdnUnid = pdnUnid;
    }

    public PayDoc getPayDocUnid() {
        return payDocUnid;
    }

    public void setPayDocUnid(PayDoc payDocUnid) {
        this.payDocUnid = payDocUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getTpdmUnid() {
        return tpdmUnid;
    }

    public void setTpdmUnid(Long tpdmUnid) {
        this.tpdmUnid = tpdmUnid;
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

    public Integer getPdnStatus() {
        return pdnStatus;
    }

    public void setPdnStatus(Integer pdnStatus) {
        this.pdnStatus = pdnStatus;
    }

    public Date getPdnDate() {
        return pdnDate;
    }

    public void setPdnDate(Date pdn_date) {
        this.pdnDate = pdn_date;
    }

    public String getPdnError() {
        return pdnError;
    }

    public void setPdnError(String pdnError) {
        this.pdnError = pdnError;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdnUnid != null ? pdnUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not sett set
        if (!(object instanceof PayDocNotice)) {
            return false;
        }
        PayDocNotice other = (PayDocNotice) object;
        if ((this.pdnUnid == null && other.pdnUnid != null) || (this.pdnUnid != null && !this.pdnUnid.equals(other.pdnUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.dep.model.PayDocNotice[ pdnUnid=" + pdnUnid + " ]";
    }
}
