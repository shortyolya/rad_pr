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
 * @author css
 */
@Entity
@Table(name = "participant_agent", schema = "web", catalog = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pa_unid"})})
@SequenceGenerator(name = "seq_participant_agent", sequenceName = "seq_participant_agent", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ParticipantAgent.findBySubUnid", query = "SELECT p FROM ParticipantAgent p WHERE p.indActual = 1 and p.subUnid.subUnid = :subUnid order by p.subSubUnid.subName"),
        @NamedQuery(name = "ParticipantAgent.findByTpaUnid", query = "SELECT p FROM ParticipantAgent p WHERE p.indActual = 1 and p.tpaUnid.tpaUnid = :tpaUnid")})
public class ParticipantAgent implements IHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_participant_agent")
    @Column(name = "pa_unid", nullable = false, precision = 2147483647, scale = 0)
    private long paUnid;
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
    @Column(name = "pa_doc_name", length = 50)
    private String paDocName;
    @Column(name = "pa_doc_number", length = 50)
    private String paDocNumber;
    @Column(name = "pa_doc_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDocDate;
    @Column(name = "pa_ds_code", length = 50)
    private String paDsCode;
    @Column(name = "pa_ds_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDsDateB;
    @Column(name = "pa_ds_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDsDateE;
    @Column(name = "pa_ind_man")
    private Long paIndMan;
    @Column(name = "pa_sys_name", length = 50)
    private String paSysName;
    @Column(name = "pa_sys_psw", length = 50)
    private String paSysPsw;
    @Column(name = "pa_key_word", length = 150)
    private String paKeyWord;
    @Column(name = "pa_ind_block")
    private Long paIndBlock;
    @Column(name = "pa_registration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paRegistrationTime;
    @Column(name = "pa_activation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paActivationTime;
    @Column(name = "pa_registration_ip", length = 25)
    private String paRegistrationIp;
    @Column(name = "pa_mail_copy")
    private Long paMailCopy;
    @Column(name = "pa_ind_show_wellcome")
    private Long paIndShowWellcome;
    @Column(name = "pa_doc_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDocDateB;
    @Column(name = "pa_doc_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDocDateE;
    @Column(name = "pa_ind_send_notification")
    private Long paIndSendNotification;
    @Column(name = "pa_ds_number", length = 500)
    private String paDsNumber;
    @Column(name = "pa_dla_name", length = 500)
    private String paDlaName;
    @Column(name = "pa_dla_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDlaDateB;
    @Column(name = "pa_dla_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDlaDateE;
    @Column(name = "pa_dla_date_found", length = 2000)
    private String paDlaDateFound;
    @Column(name = "pa_dla_date_found_e", length = 2000)
    private String paDlaDateFoundE;
    @Column(name = "pa_appl_user_code", length = 100)
    private String paApplUserCode;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subSubUnid;
    @JoinColumn(name = "par_pa_unid", referencedColumnName = "pa_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private ParticipantAgent parPaUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "dla_unid")
    private Long dlaUnid;
    @Column(name = "pa_ind_contact")
    private Integer paIndContact;
    @Column(name = "pa_dep_user_code", length = 100)
    private String paDepUserCode;

    @Column(name = "pa_ind_sent_event_notice")
    private Integer paIndSentEventNotice;
    @Column(name = "pa_lo_user_id")
    private Long paLoUserId;
    @Column(name = "pa_lo_profile_id")
    private Long paLoProfileId;

    public ParticipantAgent() {
    }

    public ParticipantAgent(long paUnid) {
        this.paUnid = paUnid;
    }

    public ParticipantAgent(long paUnid, String paSysName) {
        this.paUnid = paUnid;
        this.paSysName = paSysName;
    }

    public long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(long paUnid) {
        this.paUnid = paUnid;
    }

    @Override
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

    public String getPaDocName() {
        return paDocName;
    }

    public void setPaDocName(String paDocName) {
        this.paDocName = paDocName;
    }

    public String getPaDocNumber() {
        return paDocNumber;
    }

    public void setPaDocNumber(String paDocNumber) {
        this.paDocNumber = paDocNumber;
    }

    public Date getPaDocDate() {
        return paDocDate;
    }

    public void setPaDocDate(Date paDocDate) {
        this.paDocDate = paDocDate;
    }

    public String getPaDsCode() {
        return paDsCode;
    }

    public void setPaDsCode(String paDsCode) {
        this.paDsCode = paDsCode;
    }

    public Date getPaDsDateB() {
        return paDsDateB;
    }

    public void setPaDsDateB(Date paDsDateB) {
        this.paDsDateB = paDsDateB;
    }

    public Date getPaDsDateE() {
        return paDsDateE;
    }

    public void setPaDsDateE(Date paDsDateE) {
        this.paDsDateE = paDsDateE;
    }

    public boolean getPaIndMan() {
        return paIndMan != null && paIndMan == 1;
    }

    public void setPaIndMan(boolean paIndMan) {
        this.paIndMan = paIndMan ? 1L : 0;
    }

    public String getPaSysName() {
        return paSysName;
    }

    public void setPaSysName(String paSysName) {
        this.paSysName = paSysName;
    }

    public String getPaSysPsw() {
        return paSysPsw;
    }

    public void setPaSysPsw(String paSysPsw) {
        this.paSysPsw = paSysPsw;
    }

    public String getPaKeyWord() {
        return paKeyWord;
    }

    public void setPaKeyWord(String paKeyWord) {
        this.paKeyWord = paKeyWord;
    }

    public Long getPaIndBlock() {
        return paIndBlock;
    }

    public void setPaIndBlock(Long paIndBlock) {
        this.paIndBlock = paIndBlock;
    }

    public Date getPaRegistrationTime() {
        return paRegistrationTime;
    }

    public void setPaRegistrationTime(Date paRegistrationTime) {
        this.paRegistrationTime = paRegistrationTime;
    }

    public Date getPaActivationTime() {
        return paActivationTime;
    }

    public void setPaActivationTime(Date paActivationTime) {
        this.paActivationTime = paActivationTime;
    }

    public String getPaRegistrationIp() {
        return paRegistrationIp;
    }

    public void setPaRegistrationIp(String paRegistrationIp) {
        this.paRegistrationIp = paRegistrationIp;
    }

    public Long getPaMailCopy() {
        return paMailCopy;
    }

    public void setPaMailCopy(Long paMailCopy) {
        this.paMailCopy = paMailCopy;
    }

    public Long getPaIndShowWellcome() {
        return paIndShowWellcome;
    }

    public void setPaIndShowWellcome(Long paIndShowWellcome) {
        this.paIndShowWellcome = paIndShowWellcome;
    }

    public Date getPaDocDateB() {
        return paDocDateB;
    }

    public void setPaDocDateB(Date paDocDateB) {
        this.paDocDateB = paDocDateB;
    }

    public Date getPaDocDateE() {
        return paDocDateE;
    }

    public void setPaDocDateE(Date paDocDateE) {
        this.paDocDateE = paDocDateE;
    }

    public boolean getPaIndSendNotification() {
        return paIndSendNotification != null && paIndSendNotification == 1;
    }

    public void setPaIndSendNotification(boolean paIndSendNotification) {
        this.paIndSendNotification = paIndSendNotification ? 1L : 0L;
    }

    public String getPaDsNumber() {
        return paDsNumber;
    }

    public void setPaDsNumber(String paDsNumber) {
        this.paDsNumber = paDsNumber;
    }

    public String getPaDlaName() {
        return paDlaName;
    }

    public void setPaDlaName(String paDlaName) {
        this.paDlaName = paDlaName;
    }

    public Date getPaDlaDateB() {
        return paDlaDateB;
    }

    public void setPaDlaDateB(Date paDlaDateB) {
        this.paDlaDateB = paDlaDateB;
    }

    public Date getPaDlaDateE() {
        return paDlaDateE;
    }

    public void setPaDlaDateE(Date paDlaDateE) {
        this.paDlaDateE = paDlaDateE;
    }

    public String getPaDlaDateFound() {
        return paDlaDateFound;
    }

    public void setPaDlaDateFound(String paDlaDateFound) {
        this.paDlaDateFound = paDlaDateFound;
    }

    public String getPaDlaDateFoundE() {
        return paDlaDateFoundE;
    }

    public void setPaDlaDateFoundE(String paDlaDateFoundE) {
        this.paDlaDateFoundE = paDlaDateFoundE;
    }

    public String getApplPcUserCode() {
        return paApplUserCode;
    }

    public void setApplPcUserCode(String paPcUserCode) {
        this.paApplUserCode = paPcUserCode;
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

    public ParticipantAgent getParPaUnid() {
        return parPaUnid;
    }

    public void setParPaUnid(ParticipantAgent parPaUnid) {
        this.parPaUnid = parPaUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Long getDlaUnid() {
        return dlaUnid;
    }

    public void setDlaUnid(Long dlaUnid) {
        this.dlaUnid = dlaUnid;
    }

    public boolean getPaIndContact() {
        return paIndContact != null && paIndContact == 1;
    }

    public void setPaIndContact(boolean paIndContact) {
        this.paIndContact = paIndContact ? 1 : 0;
    }

    public String getPaDepUserCode() {
        return paDepUserCode;
    }

    public void setPaDepUserCode(String paDepUserCode) {
        this.paDepUserCode = paDepUserCode;
    }

    public Integer getPaIndSentEventNotice() {
        return paIndSentEventNotice;
    }

    public void setPaIndSentEventNotice(Integer paIndSentEventNotice) {
        this.paIndSentEventNotice = paIndSentEventNotice;
    }

    public Long getPaLoUserId() {
        return paLoUserId;
    }

    public void setPaLoUserId(Long paLoUserId) {
        this.paLoUserId = paLoUserId;
    }

    public Long getPaLoProfileId() {
        return paLoProfileId;
    }

    public void setPaLoProfileId(Long paLoProfileId) {
        this.paLoProfileId = paLoProfileId;
    }


    @Override
    public String toString() {
        return "com.baltinfo.model.model.ParticipantAgent[ paUnid=" + paUnid + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.paUnid ^ (this.paUnid >>> 32));
        return hash;
    }


}
