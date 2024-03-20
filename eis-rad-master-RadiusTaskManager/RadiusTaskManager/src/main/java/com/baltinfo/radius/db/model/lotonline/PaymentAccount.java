package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author css
 */
@Entity
@Table(name = "payment_account", catalog = "", schema = "")
@XmlRootElement
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.PaymentAccount.profileFk", attributeNodes = {
        @NamedAttributeNode(value="profileFk")
    })
})
public class PaymentAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "current_amount", precision = 19, scale = 4)
    private BigDecimal currentAmount;
    @Column(name = "blocked_amount", precision = 19, scale = 4)
    private BigDecimal blockedAmount;
    @Column(name = "account_number", length = 7)
    private String accountNumber;
    @Column(name = "eis_account_unid")
    private Long eisAccountUnid;   
    @JoinColumn(name = "profile_fk", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false,fetch=FetchType.LAZY)
    private UserProfile profileFk; 

    public PaymentAccount() {
    }

    public PaymentAccount(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public BigDecimal getBlockedAmount() {
        return blockedAmount;
    }

    public void setBlockedAmount(BigDecimal blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getEisAccountUnid() {
        return eisAccountUnid;
    }

    public void setEisAccountUnid(Long eisAccountUnid) {
        this.eisAccountUnid = eisAccountUnid;
    }

    public UserProfile getProfileFk() {
        return profileFk;
    }

    public void setProfileFk(UserProfile profileFk) {
        this.profileFk = profileFk;
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
        if (!(object instanceof PaymentAccount)) {
            return false;
        }
        PaymentAccount other = (PaymentAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.PaymentAccount[ id=" + id + " ]";
    }
    
}
