/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.lotonline;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "user_document", catalog = "", schema = "")
@XmlRootElement
public class UserDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "seq_user_document_id")
    @GenericGenerator(name = "seq_user_document_id",
            strategy = "com.baltinfo.radius.db.model.lotonline.LotOnlineShard3SequnceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "seq_user_document_id"))
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String state;
    @Column(name = "file_name", length = 255)
    private String fileName;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic(optional = false)
    @Column(name = "use_crypting", nullable = false)
    private boolean useCrypting;
    @Basic(optional = false)
    @Column(name = "can_be_decrypted", nullable = false)
    private boolean canBeDecrypted;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "document_name", length = 250)
    private String documentName;
    @Column(name = "inter_document_name", length = 250)
    private String interDocumentName;
    @Column(name = "documentdescription_id")
    private BigInteger documentdescriptionId;
    private int size;
    @Column(name = "hash_sum", length = 32)
    private String hashSum;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "documentlist_id")
    private Long documentlistId;
    @Lob
    @Column(name = "content")
    private byte[] content;

    public UserDocument() {
    }

    public UserDocument(Long id) {
        this.id = id;
    }

    public UserDocument(Long id, String state, long userId, boolean useCrypting, boolean canBeDecrypted) {
        this.id = id;
        this.state = state;
        this.userId = userId;
        this.useCrypting = useCrypting;
        this.canBeDecrypted = canBeDecrypted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getUseCrypting() {
        return useCrypting;
    }

    public void setUseCrypting(boolean useCrypting) {
        this.useCrypting = useCrypting;
    }

    public boolean getCanBeDecrypted() {
        return canBeDecrypted;
    }

    public void setCanBeDecrypted(boolean canBeDecrypted) {
        this.canBeDecrypted = canBeDecrypted;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getInterDocumentName() {
        return interDocumentName;
    }

    public void setInterDocumentName(String interDocumentName) {
        this.interDocumentName = interDocumentName;
    }

    public BigInteger getDocumentdescriptionId() {
        return documentdescriptionId;
    }

    public void setDocumentdescriptionId(BigInteger documentdescriptionId) {
        this.documentdescriptionId = documentdescriptionId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getHashSum() {
        return hashSum;
    }

    public void setHashSum(String hashSum) {
        this.hashSum = hashSum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getDocumentlistId() {
        return documentlistId;
    }

    public void setDocumentlistId(Long documentlistId) {
        this.documentlistId = documentlistId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
        if (!(object instanceof UserDocument)) {
            return false;
        }
        UserDocument other = (UserDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.UserDocument[ id=" + id + " ]";
    }
    
}
