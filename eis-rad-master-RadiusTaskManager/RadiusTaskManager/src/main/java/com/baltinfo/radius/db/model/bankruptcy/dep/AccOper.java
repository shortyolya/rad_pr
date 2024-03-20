package com.baltinfo.radius.db.model.bankruptcy.dep;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "ACC_OPER", catalog = "", schema = "WEB")
@XmlRootElement
public class AccOper implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ACO_UNID", nullable = false)
    private long acoUnid;
    @Basic(optional = false)
    @Column(name = "TAO_UNID", nullable = false)
    private Long taoUnid;
    @Column(name = "TAO_NAME", length = 150)
    private String taoName;
    @Column(name = "APPLICAT_UNID")
    private Long applicatUnid;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Column(name = "ACO_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acoTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACO_SUMM", precision = 15, scale = 2)
    private BigDecimal acoSumm;
    @Column(name = "ACO_NOTE", length = 2000)
    private String acoNote;
    @Column(name = "ACO_IND_RET")
    private Short acoIndRet;
    @Column(name = "ACO_RECIP_NAME", length = 2000)
    private String acoRecipName;
    @Column(name = "ACO_RECIP_INN", length = 20)
    private String acoRecipInn;
    @Column(name = "ACO_RECIP_KPP", length = 20)
    private String acoRecipKpp;
    @Column(name = "ACO_RECIP_ACCOUNT", length = 50)
    private String acoRecipAccount;
    @Column(name = "ACO_RECIP_BANK_NAME", length = 2000)
    private String acoRecipBankName;
    @Column(name = "ACO_RECIP_BANK_BIK", length = 20)
    private String acoRecipBankBik;
    @Column(name = "ACO_RECIP_BANK_ACCOUNT", length = 50)
    private String acoRecipBankAccount;
    @Column(name = "ACO_PAY_DOC_NUM", length = 50)
    private String acoPayDocNum;
    @Column(name = "ACO_PAY_DOC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acoPayDocDate;
    @Column(name = "ACO_OPER_EIS_ID")
    private Long acoOperEisId;
    @Column(name = "ACO_PURPOSE")
    private String acoPurpose;

    public AccOper() {
    }

    public long getAcoUnid() {
        return acoUnid;
    }

    public void setAcoUnid(long acoUnid) {
        this.acoUnid = acoUnid;
    }

    public Long getTaoUnid() {
        return taoUnid;
    }

    public void setTaoUnid(Long taoUnid) {
        this.taoUnid = taoUnid;
    }

    public String getTaoName() {
        return taoName;
    }

    public void setTaoName(String taoName) {
        this.taoName = taoName;
    }

    public Long getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(Long applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public Date getAcoTime() {
        return acoTime;
    }

    public void setAcoTime(Date acoTime) {
        this.acoTime = acoTime;
    }

    public BigDecimal getAcoSumm() {
        return acoSumm;
    }

    public void setAcoSumm(BigDecimal acoSumm) {
        this.acoSumm = acoSumm;
    }

    public String getAcoNote() {
        return acoNote;
    }

    public void setAcoNote(String acoNote) {
        this.acoNote = acoNote;
    }

    public Short getAcoIndRet() {
        return acoIndRet;
    }

    public void setAcoIndRet(Short acoIndRet) {
        this.acoIndRet = acoIndRet;
    }

    public String getAcoRecipName() {
        return acoRecipName;
    }

    public void setAcoRecipName(String acoRecipName) {
        this.acoRecipName = acoRecipName;
    }

    public String getAcoRecipInn() {
        return acoRecipInn;
    }

    public void setAcoRecipInn(String acoRecipInn) {
        this.acoRecipInn = acoRecipInn;
    }

    public String getAcoRecipKpp() {
        return acoRecipKpp;
    }

    public void setAcoRecipKpp(String acoRecipKpp) {
        this.acoRecipKpp = acoRecipKpp;
    }

    public String getAcoRecipAccount() {
        return acoRecipAccount;
    }

    public void setAcoRecipAccount(String acoRecipAccount) {
        this.acoRecipAccount = acoRecipAccount;
    }

    public String getAcoRecipBankName() {
        return acoRecipBankName;
    }

    public void setAcoRecipBankName(String acoRecipBankName) {
        this.acoRecipBankName = acoRecipBankName;
    }

    public String getAcoRecipBankBik() {
        return acoRecipBankBik;
    }

    public void setAcoRecipBankBik(String acoRecipBankBik) {
        this.acoRecipBankBik = acoRecipBankBik;
    }

    public String getAcoRecipBankAccount() {
        return acoRecipBankAccount;
    }

    public void setAcoRecipBankAccount(String acoRecipBankAccount) {
        this.acoRecipBankAccount = acoRecipBankAccount;
    }

    public String getAcoPayDocNum() {
        return acoPayDocNum;
    }

    public void setAcoPayDocNum(String acoPayDocNum) {
        this.acoPayDocNum = acoPayDocNum;
    }

    public Date getAcoPayDocDate() {
        return acoPayDocDate;
    }

    public void setAcoPayDocDate(Date acoPayDocDate) {
        this.acoPayDocDate = acoPayDocDate;
    }

    public Long getAcoOperEisId() {
        return acoOperEisId;
    }

    public void setAcoOperEisId(Long acoOperEisId) {
        this.acoOperEisId = acoOperEisId;
    }

    public String getAcoPurpose() {
        return acoPurpose;
    }

    public void setAcoPurpose(String acoPurpose) {
        this.acoPurpose = acoPurpose;
    }
}
