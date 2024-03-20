package com.baltinfo.radius.db.model.lotonline;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author css
 */
@Entity
@Table(catalog = "", schema = "")
@XmlRootElement
public class Documentlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "seq_document_list_id")
    @GenericGenerator(name = "seq_document_list_id",
            strategy = "com.baltinfo.radius.db.model.lotonline.LotOnlineShard3SequnceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "seq_document_list_id"))
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Column(name = "documentdescriptions_id")
    private BigInteger documentdescriptionsId;

    public Documentlist() {
    }

    public Documentlist(Long id) {
        this.id = id;
    }

    public Documentlist(Long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigInteger getDocumentdescriptionsId() {
        return documentdescriptionsId;
    }

    public void setDocumentdescriptionsId(BigInteger documentdescriptionsId) {
        this.documentdescriptionsId = documentdescriptionsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentlist)) {
            return false;
        }
        Documentlist other = (Documentlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.Documentlist[ id=" + id + " ]";
    }
    
}
