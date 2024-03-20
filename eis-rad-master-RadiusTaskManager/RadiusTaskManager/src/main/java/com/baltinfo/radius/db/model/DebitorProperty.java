package com.baltinfo.radius.db.model;

import org.apache.commons.lang3.StringUtils;

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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "debitor_property", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dp_unid"})})
@SequenceGenerator(name = "seq_debitor_property", sequenceName = "seq_debitor_property", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DebitorProperty.findAll", query = "SELECT d FROM DebitorProperty d")})
public class DebitorProperty implements Serializable, IHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_debitor_property")
    @Column(name = "dp_unid")
    private Long dpUnid;
    @Column(name = "dp_crash_file_num")
    private String dpCrashFileNum;
    @Column(name = "dp_arbitrage")
    private String dpArbitrage;
    @Column(name = "dp_decree_num")
    private String dpDecreeNum;
    @Column(name = "dp_decree_date")
    @Temporal(TemporalType.DATE)
    private Date dpDecreeDate;
    @Column(name = "dp_arbit_manager")
    private String dpArbitManager;
    @Column(name = "dp_arbit_manager_name_f")
    private String dpArbitManagerNameF;
    @Column(name = "dp_arbit_manager_name_i")
    private String dpArbitManagerNameI;
    @Column(name = "dp_arbit_manager_name_o")
    private String dpArbitManagerNameO;
    @Column(name = "dp_arbit_manager_cro")
    private String dpArbitManagerCro;
    @Column(name = "dp_arbit_manager_reg_num")
    private String dpArbitManagerRegNum;
    @Column(name = "dp_arbit_manager_inn")
    private String dpArbitManagerInn;
    @Column(name = "dp_deposit_order")
    private String dpDepositOrder;
    @Column(name = "dp_accounts_details")
    private String dpAccountsDetails;
    @Column(name = "dp_auction_reg_order")
    private String dpAuctionRegOrder;
    @Column(name = "dp_auction_winner_order")
    private String dpAuctionWinnerOrder;
    @Column(name = "dp_auction_result_place")
    private String dpAuctionResultPlace;
    @Column(name = "dp_deal_order")
    private String dpDealOrder;
    @Column(name = "dp_payments_dates")
    private String dpPaymentsDates;
    @Column(name = "dp_date_publ_journal")
    @Temporal(TemporalType.DATE)
    private Date dpDatePublJournal;
    @Column(name = "dp_date_publ_efir")
    @Temporal(TemporalType.DATE)
    private Date dpDatePublEfir;
    @Column(name = "dp_tender_term")
    private String dpTenderTerm;
    @Column(name = "dp_ind_tend_manager")
    private Short dpIndTendManager;
    @Column(name = "dp_tend_manager_name")
    private String dpTendManagerName;
    @Column(name = "dp_tend_manager_sname")
    private String dpTendManagerSname;
    @Column(name = "dp_tend_manager_ogrn")
    private String dpTendManagerOgrn;
    @Column(name = "dp_participant_requirements")
    private String dpParticipantRequirements;
    @Column(name = "dp_interfax_mess_num")
    private String dpInterfaxMessNum;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "dp_ind_send_oper")
    private Short dpIndSendOper;
    @Column(name = "dp_payee_name")
    private String dpPayeeName;
    @Column(name = "dp_payee_inn")
    private String dpPayeeInn;
    @Column(name = "dp_payee_kpp")
    private String dpPayeeKpp;
    @Column(name = "dp_payee_account")
    private String dpPayeeAccount;
    @Column(name = "dp_payee_bank_name")
    private String dpPayeeBankName;
    @Column(name = "dp_payee_bank_bik")
    private String dpPayeeBankBik;
    @Column(name = "dp_payee_bank_account")
    private String dpPayeeBankAccount;
    @Column(name = "dp_contact_email")
    private String dpContactEmail;
    @Column(name = "dp_contact_asv")
    private String dpContactAsv;
    @Column(name = "dp_deal_asv")
    private String dpDealAsv; // Номер и дата договора АСВ
    @Column(name = "dp_tend_manager_proxy")
    private String dpTendManagerProxy; // Реквизиты доверенности представителя КУ
    @Column(name = "dp_sro_payment_value")
    private BigDecimal dpSroPaymentValue;
    @Column(name = "dp_sro_payment_sum")
    private BigDecimal dpSroPaymentSum;
    @Column(name = "dp_sro_payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dpSroPaymentDate;
    @Column(name = "dp_sro_payment_note")
    private String dpSroPaymentNote;
    @JoinColumn(name = "sro_id", referencedColumnName = "sro_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SRO sroId;

    public DebitorProperty() {
    }

    //<editor-fold defaultstate="collapsed" desc="get-set"> 
    public String getDpPayeeName() {
        return dpPayeeName;
    }

    public void setDpPayeeName(String dpPayeeName) {
        this.dpPayeeName = dpPayeeName;
    }

    public String getDpPayeeInn() {
        return dpPayeeInn;
    }

    public void setDpPayeeInn(String dpPayeeInn) {
        this.dpPayeeInn = dpPayeeInn.replaceAll(" ", "");
    }

    public String getDpPayeeKpp() {
        return dpPayeeKpp;
    }

    public void setDpPayeeKpp(String dpPayeeKpp) {
        this.dpPayeeKpp = dpPayeeKpp.replaceAll(" ", "");
    }

    public String getDpPayeeAccount() {
        return dpPayeeAccount;
    }

    public void setDpPayeeAccount(String dpPayeeAccount) {
        this.dpPayeeAccount = dpPayeeAccount.replaceAll(" ", "");
    }

    public String getDpPayeeBankName() {
        return dpPayeeBankName;
    }

    public void setDpPayeeBankName(String dpPayeeBankName) {
        this.dpPayeeBankName = dpPayeeBankName;
    }

    public String getDpPayeeBankBik() {
        return dpPayeeBankBik;
    }

    public void setDpPayeeBankBik(String dpPayeeBankBik) {
        this.dpPayeeBankBik = dpPayeeBankBik.replaceAll(" ", "");
    }

    public String getDpPayeeBankAccount() {
        return dpPayeeBankAccount;
    }

    public void setDpPayeeBankAccount(String dpPayeeBankAccount) {
        if (dpPayeeBankAccount != null) {
            this.dpPayeeBankAccount = dpPayeeBankAccount.replaceAll(" ", "");
        }
    }

    public DebitorProperty(Long dpUnid) {
        this.dpUnid = dpUnid;
    }

    public Long getDpUnid() {
        return dpUnid;
    }

    public void setDpUnid(Long dpUnid) {
        this.dpUnid = dpUnid;
    }

    public String getDpCrashFileNum() {
        return dpCrashFileNum;
    }

    public void setDpCrashFileNum(String dpCrashFileNum) {
        this.dpCrashFileNum = dpCrashFileNum;
    }

    public String getDpArbitrage() {
        return dpArbitrage;
    }

    public void setDpArbitrage(String dpArbitrage) {
        this.dpArbitrage = dpArbitrage;
    }

    public String getDpDecreeNum() {
        return dpDecreeNum;
    }

    public void setDpDecreeNum(String dpDecreeNum) {
        this.dpDecreeNum = dpDecreeNum;
    }

    public Date getDpDecreeDate() {
        return dpDecreeDate;
    }

    public void setDpDecreeDate(Date dpDecreeDate) {
        this.dpDecreeDate = dpDecreeDate;
    }

    public String getDpArbitManager() {
        return dpArbitManager;
    }

    public void setDpArbitManager(String dpArbitManager) {
        this.dpArbitManager = dpArbitManager;
    }

    public String getDpArbitManagerNameF() {
        return dpArbitManagerNameF;
    }

    public void setDpArbitManagerNameF(String dpArbitManagerNameF) {
        this.dpArbitManagerNameF = dpArbitManagerNameF;
    }

    public String getDpArbitManagerNameI() {
        return dpArbitManagerNameI;
    }

    public void setDpArbitManagerNameI(String dpArbitManagerNameI) {
        this.dpArbitManagerNameI = dpArbitManagerNameI;
    }

    public String getDpArbitManagerNameO() {
        return dpArbitManagerNameO;
    }

    public void setDpArbitManagerNameO(String dpArbitManagerNameO) {
        this.dpArbitManagerNameO = dpArbitManagerNameO;
    }

    public String getDpArbitManagerCro() {
        return dpArbitManagerCro;
    }

    public void setDpArbitManagerCro(String dpArbitManagerCro) {
        this.dpArbitManagerCro = dpArbitManagerCro;
    }

    public String getDpArbitManagerRegNum() {
        return dpArbitManagerRegNum;
    }

    public void setDpArbitManagerRegNum(String dpArbitManagerRegNum) {
        this.dpArbitManagerRegNum = dpArbitManagerRegNum;
    }

    public String getDpArbitManagerInn() {
        return dpArbitManagerInn;
    }

    public void setDpArbitManagerInn(String dpArbitManagerInn) {
        this.dpArbitManagerInn = dpArbitManagerInn;
    }

    public String getDpDepositOrder() {
        return dpDepositOrder;
    }

    public void setDpDepositOrder(String dpDepositOrder) {
        this.dpDepositOrder = dpDepositOrder;
    }

    public String getDpAccountsDetails() {
        return dpAccountsDetails;
    }

    public void setDpAccountsDetails(String dpAccountsDetails) {
        this.dpAccountsDetails = dpAccountsDetails;
    }

    public String getDpAuctionRegOrder() {
        return dpAuctionRegOrder;
    }

    public void setDpAuctionRegOrder(String dpAuctionRegOrder) {
        this.dpAuctionRegOrder = dpAuctionRegOrder;
    }

    public String getDpAuctionWinnerOrder() {
        return dpAuctionWinnerOrder;
    }

    public void setDpAuctionWinnerOrder(String dpAuctionWinnerOrder) {
        this.dpAuctionWinnerOrder = dpAuctionWinnerOrder;
    }

    public String getDpAuctionResultPlace() {
        return dpAuctionResultPlace;
    }

    public void setDpAuctionResultPlace(String dpAuctionResultPlace) {
        this.dpAuctionResultPlace = dpAuctionResultPlace;
    }

    public String getDpDealOrder() {
        return dpDealOrder;
    }

    public void setDpDealOrder(String dpDealOrder) {
        this.dpDealOrder = dpDealOrder;
    }

    public String getDpPaymentsDates() {
        return dpPaymentsDates;
    }

    public void setDpPaymentsDates(String dpPaymentsDates) {
        this.dpPaymentsDates = dpPaymentsDates;
    }

    public Date getDpDatePublJournal() {
        return dpDatePublJournal;
    }

    public void setDpDatePublJournal(Date dpDatePublJournal) {
        this.dpDatePublJournal = dpDatePublJournal;
    }

    public Date getDpDatePublEfir() {
        return dpDatePublEfir;
    }

    public void setDpDatePublEfir(Date dpDatePublEfir) {
        this.dpDatePublEfir = dpDatePublEfir;
    }

    public String getDpTenderTerm() {
        return dpTenderTerm;
    }

    public void setDpTenderTerm(String dpTenderTerm) {
        this.dpTenderTerm = dpTenderTerm;
    }

    public Short getDpIndTendManager() {
        return dpIndTendManager;
    }

    public void setDpIndTendManager(Short dpIndTendManager) {
        this.dpIndTendManager = dpIndTendManager;
    }

    public String getDpTendManagerName() {
        return dpTendManagerName;
    }

    public void setDpTendManagerName(String dpTendManagerName) {
        this.dpTendManagerName = dpTendManagerName;
    }

    public String getDpTendManagerSname() {
        return dpTendManagerSname;
    }

    public void setDpTendManagerSname(String dpTendManagerSname) {
        this.dpTendManagerSname = dpTendManagerSname;
    }

    public String getDpTendManagerOgrn() {
        return dpTendManagerOgrn;
    }

    public void setDpTendManagerOgrn(String dpTendManagerOgrn) {
        this.dpTendManagerOgrn = dpTendManagerOgrn;
    }

    public String getDpParticipantRequirements() {
        return dpParticipantRequirements;
    }

    public void setDpParticipantRequirements(String dpParticipantRequirements) {
        this.dpParticipantRequirements = dpParticipantRequirements;
    }

    public String getDpInterfaxMessNum() {
        return StringUtils.trim(dpInterfaxMessNum);
    }

    public void setDpInterfaxMessNum(String dpInterfaxMessNum) {
        this.dpInterfaxMessNum = StringUtils.trim(dpInterfaxMessNum);
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public boolean getDpIndSendOper() {
        return dpIndSendOper != null && dpIndSendOper == 1;
    }

    public void setDpIndSendOper(boolean dpIndSendOper) {
        this.dpIndSendOper = dpIndSendOper ? Short.valueOf("1") : Short.valueOf("0");
    }

    public String getDpContactEmail() {
        return dpContactEmail;
    }

    public void setDpContactEmail(String dpContactEmail) {
        this.dpContactEmail = dpContactEmail;
    }

    public String getDpContactAsv() {
        return dpContactAsv;
    }

    public void setDpContactAsv(String dpContactAsv) {
        this.dpContactAsv = dpContactAsv;
    }

    public String getDpTendManagerProxy() {
        return dpTendManagerProxy;
    }

    public void setDpTendManagerProxy(String dpTendManagerProxy) {
        this.dpTendManagerProxy = dpTendManagerProxy;
    }

    public String getDpDealAsv() {
        return dpDealAsv;
    }

    public void setDpDealAsv(String dpDealAsv) {
        this.dpDealAsv = dpDealAsv;
    }

    public BigDecimal getDpSroPaymentValue() {
        return dpSroPaymentValue;
    }

    public void setDpSroPaymentValue(BigDecimal dpSroPaymentValue) {
        this.dpSroPaymentValue = dpSroPaymentValue;
    }

    public BigDecimal getDpSroPaymentSum() {
        return dpSroPaymentSum;
    }

    public void setDpSroPaymentSum(BigDecimal dpSroPaymentSum) {
        this.dpSroPaymentSum = dpSroPaymentSum;
    }

    public Date getDpSroPaymentDate() {
        return dpSroPaymentDate;
    }

    public void setDpSroPaymentDate(Date dpSroPaymentDate) {
        this.dpSroPaymentDate = dpSroPaymentDate;
    }

    public String getDpSroPaymentNote() {
        return dpSroPaymentNote;
    }

    public void setDpSroPaymentNote(String dpSroPaymentNote) {
        this.dpSroPaymentNote = dpSroPaymentNote;
    }

    public SRO getSroId() {
        return sroId;
    }

    public void setSroId(SRO sroId) {
        this.sroId = sroId;
    }

    //</editor-fold>

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dpUnid != null ? dpUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DebitorProperty)) {
            return false;
        }
        DebitorProperty other = (DebitorProperty) object;
        return (this.dpUnid != null || other.dpUnid == null) && (this.dpUnid == null || this.dpUnid.equals(other.dpUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DebitorProperty[ dpUnid=" + dpUnid + " ]";
    }

    public DebitorProperty clone() {
        DebitorProperty debitorProperty = new DebitorProperty();
        debitorProperty.dpCrashFileNum = this.dpCrashFileNum;
        debitorProperty.dpArbitrage = this.dpArbitrage;
        debitorProperty.dpDecreeNum = this.dpDecreeNum;
        debitorProperty.dpDecreeDate = this.dpDecreeDate;
        debitorProperty.dpArbitManager = this.dpArbitManager;
        debitorProperty.dpArbitManagerNameF = this.dpArbitManagerNameF;
        debitorProperty.dpArbitManagerNameI = this.dpArbitManagerNameI;
        debitorProperty.dpArbitManagerNameO = this.dpArbitManagerNameO;
        debitorProperty.dpArbitManagerCro = this.dpArbitManagerCro;
        debitorProperty.dpArbitManagerRegNum = this.dpArbitManagerRegNum;
        debitorProperty.dpDatePublJournal = this.dpDatePublJournal;
        debitorProperty.dpDatePublEfir = this.dpDatePublEfir;
        debitorProperty.dpTenderTerm = this.dpTenderTerm;
        debitorProperty.dpParticipantRequirements = this.dpParticipantRequirements;
        debitorProperty.dpInterfaxMessNum = this.dpInterfaxMessNum;
        debitorProperty.dpPayeeBankAccount = this.dpPayeeBankAccount;
        debitorProperty.dpContactEmail = this.dpContactEmail;
        debitorProperty.dpContactAsv = this.dpContactAsv;
        debitorProperty.dpDealAsv = this.dpDealAsv;
        debitorProperty.dpTendManagerProxy = this.dpTendManagerProxy;
        debitorProperty.dpIndTendManager = this.dpIndTendManager;
        debitorProperty.dpTendManagerName = this.dpTendManagerName;
        debitorProperty.dpTendManagerSname = this.dpTendManagerSname;
        debitorProperty.dpTendManagerOgrn = this.dpTendManagerOgrn;
        debitorProperty.dpArbitManagerInn = this.dpArbitManagerInn;
        debitorProperty.dpIndSendOper = this.dpIndSendOper;
        debitorProperty.dpDepositOrder = this.dpDepositOrder;
        debitorProperty.dpAccountsDetails = this.dpAccountsDetails;
        debitorProperty.dpAuctionRegOrder = this.dpAuctionRegOrder;
        debitorProperty.dpAuctionWinnerOrder = this.dpAuctionWinnerOrder;
        debitorProperty.dpAuctionResultPlace = this.dpAuctionResultPlace;
        debitorProperty.dpDealOrder = this.dpDealOrder;
        debitorProperty.dpPaymentsDates = this.dpPaymentsDates;
        debitorProperty.dpPayeeName = this.dpPayeeName;
        debitorProperty.dpPayeeInn = this.dpPayeeInn;
        debitorProperty.dpPayeeKpp = this.dpPayeeKpp;
        debitorProperty.dpPayeeAccount = this.dpPayeeAccount;
        debitorProperty.dpPayeeBankName = this.dpPayeeBankName;
        debitorProperty.dpPayeeBankBik = this.dpPayeeBankBik;
        debitorProperty.dpSroPaymentDate = this.dpSroPaymentDate;
        debitorProperty.dpSroPaymentNote = this.dpSroPaymentNote;
        debitorProperty.dpSroPaymentValue = this.dpSroPaymentValue;
        debitorProperty.dpSroPaymentSum = this.dpSroPaymentSum;
        return debitorProperty;
    }
}
