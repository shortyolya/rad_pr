package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "V_BA_RESULTS", schema = "WEB")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VBaResults.findAll",
                query = "SELECT v FROM VBaResults v order by v.baUnid desc")})
public class VBaResults implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BA_UNID")
    private Long baUnid;
    @Column(name = "BA_ASV_ID")
    private String baAsvId;
    @Column(name = "BA_IND_NON_BLOCK")
    private Boolean baIndNonBlock;
    @Column(name = "DEBTOR_NAME")
    private String debtorName;
    @Column(name = "DEBTOR_INN")
    private String debtorInn;
    @Column(name = "DEBTOR_OGRN")
    private String debtorOgrn;
    @Column(name = "DEBTOR_BIC")
    private String debtorBic;

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
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

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorInn() {
        return debtorInn;
    }

    public void setDebtorInn(String debtorInn) {
        this.debtorInn = debtorInn;
    }

    public String getDebtorOgrn() {
        return debtorOgrn;
    }

    public void setDebtorOgrn(String debtorOgrn) {
        this.debtorOgrn = debtorOgrn;
    }

    public String getDebtorBic() {
        return debtorBic;
    }

    public void setDebtorBic(String debtorBic) {
        this.debtorBic = debtorBic;
    }
}
