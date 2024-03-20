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
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 13.01.2020
 */
@Entity
@Table(name = "block_auction", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ba_unid"})})
@SequenceGenerator(name = "seq_block_auction", sequenceName = "seq_block_auction", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "BlockAuction.findByAuctionUnid", query = "SELECT l FROM BlockAuction l WHERE l.baUnid = (SELECT a.baUnid FROM Auction a where a.auctionUnid = :auctionUnid)")
})
public class BlockAuction implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_block_auction")
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "ba_name")
    private String baName;
    @Column(name = "ba_note")
    private String baNote;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "ba_asv_id")
    private String baAsvId;
    @Column(name = "ba_ind_non_block")
    private Boolean baIndNonBlock;
    @Column(name = "dp_unid")
    private Long dpUnid;
    @Column(name = "ba_asv_order_num")
    private String baAsvOrderNum;

    public BlockAuction() {
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
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

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
        this.foundUnid = foundUnid;
    }

    public String getBaName() {
        return baName;
    }

    public void setBaName(String baName) {
        this.baName = baName;
    }

    public String getBaNote() {
        return baNote;
    }

    public void setBaNote(String baNote) {
        this.baNote = baNote;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public String getBaAsvId() {
        return baAsvId;
    }

    public void setBaAsvId(String baAsvId) {
        this.baAsvId = baAsvId;
    }

    public Boolean getBaIndNonBlock() {
        return baIndNonBlock;
    }

    public void setBaIndNonBlock(Boolean baIndNonBlock) {
        this.baIndNonBlock = baIndNonBlock;
    }

    public Long getDpUnid() {
        return dpUnid;
    }

    public void setDpUnid(Long dpUnid) {
        this.dpUnid = dpUnid;
    }

    public String getBaAsvOrderNum() {
        return baAsvOrderNum;
    }

    public void setBaAsvOrderNum(String baAsvOrderNum) {
        this.baAsvOrderNum = baAsvOrderNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baUnid != null ? baUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockAuction)) {
            return false;
        }
        BlockAuction other = (BlockAuction) object;
        return (this.baUnid != null || other.baUnid == null) && (this.baUnid == null || this.baUnid.equals(other.baUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.BlockAuction[ baUnid=" + baUnid + " ]";
    }
}