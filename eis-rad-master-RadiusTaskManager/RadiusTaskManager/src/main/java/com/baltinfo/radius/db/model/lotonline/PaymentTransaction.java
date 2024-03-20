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
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "payment_transaction", catalog = "", schema = "")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.PaymentTransaction.paymentAccount", attributeNodes = {
        @NamedAttributeNode(value = "paymentAccount", subgraph = "subgraph.PaymentTransaction.paymentAccount")
    },
            subgraphs = {
                @NamedSubgraph(
                        name = "subgraph.PaymentTransaction.paymentAccount",
                        attributeNodes = {
                            @NamedAttributeNode("profileFk")
                        }
                )
            }),
    @NamedEntityGraph(name = "graph.PaymentTransaction.paymentAccountAndPaymentGuaranty", attributeNodes = {
        @NamedAttributeNode(value = "paymentAccount", subgraph = "subgraph.PaymentTransaction.paymentAccount"),
        @NamedAttributeNode(value = "depositId")
    },
            subgraphs = {
                @NamedSubgraph(
                        name = "subgraph.PaymentTransaction.paymentAccount",
                        attributeNodes = {
                            @NamedAttributeNode("profileFk")
                        }
                )
            })
})
@XmlRootElement
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Column(name = "transaction_type", length = 20)
    private String transactionType;
    @Column(name = "order_number", length = 16)
    private String orderNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "actor_id", nullable = false)
    private long actorId;
    @Column(name = "process_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processDate;
    @Column(length = 200)
    private String comment;
    @Column(name = "current_amount", precision = 19, scale = 4)
    private BigDecimal currentAmount;
    @Column(name = "blocked_amount", precision = 19, scale = 4)
    private BigDecimal blockedAmount;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "recip_name")
    private String recipName;
    @Column(name = "recip_inn")
    private String recipInn;
    @Column(name = "recip_kpp")
    private String recipKpp;
    @Column(name = "recip_account")
    private String recipAccount;
    @Column(name = "recip_bank_name")
    private String recipBankName;
    @Column(name = "recip_bank_bik")
    private String recipBankBik;
    @Column(name = "recip_bank_account")
    private String recipBankAccount;
    @Column(name = "eis_oper_id")
    private Long eisOperId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_account_id")
    private PaymentAccount paymentAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_id", referencedColumnName = "id")
    private PaymentGuaranty depositId;
    @Column(name = "recip_kbk")
    private String recipKbk;
    @Column(name = "recip_oktmo")
    private String recipOktmo;
    @Column(name = "payment_purpose")
    private String paymentPurpose;

    public PaymentTransaction() {
    }

    public PaymentTransaction(Long id) {
        this.id = id;
    }

    public PaymentTransaction(Long id, BigDecimal amount, long actorId) {
        this.id = id;
        this.amount = amount;
        this.actorId = actorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRecipName() {
        return recipName;
    }

    public void setRecipName(String recipName) {
        this.recipName = recipName;
    }

    public String getRecipInn() {
        return recipInn;
    }

    public void setRecipInn(String recipInn) {
        this.recipInn = recipInn;
    }

    public String getRecipKpp() {
        return recipKpp;
    }

    public void setRecipKpp(String recipKpp) {
        this.recipKpp = recipKpp;
    }

    public String getRecipAccount() {
        return recipAccount;
    }

    public void setRecipAccount(String recipAccount) {
        this.recipAccount = recipAccount;
    }

    public String getRecipBankName() {
        return recipBankName;
    }

    public void setRecipBankName(String recipBankName) {
        this.recipBankName = recipBankName;
    }

    public String getRecipBankBik() {
        return recipBankBik;
    }

    public void setRecipBankBik(String recipBankBik) {
        this.recipBankBik = recipBankBik;
    }

    public String getRecipBankAccount() {
        return recipBankAccount;
    }

    public void setRecipBankAccount(String recipBankAccount) {
        this.recipBankAccount = recipBankAccount;
    }

    public Long getEisOperId() {
        return eisOperId;
    }

    public void setEisOperId(Long eisOperId) {
        this.eisOperId = eisOperId;
    }    
    
    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
    
    public PaymentGuaranty getDepositId() {
        return depositId;
    }

    public void setDepositId(PaymentGuaranty depositId) {
        this.depositId = depositId;
    }

    public String getRecipKbk() {
        return recipKbk;
    }

    public void setRecipKbk(String recipKbk) {
        this.recipKbk = recipKbk;
    }

    public String getRecipOktmo() {
        return recipOktmo;
    }

    public void setRecipOktmo(String recipOktmo) {
        this.recipOktmo = recipOktmo;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
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
        if (!(object instanceof PaymentTransaction)) {
            return false;
        }
        PaymentTransaction other = (PaymentTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.PaymentTransaction[ id=" + id + " ]";
    }
    
}
