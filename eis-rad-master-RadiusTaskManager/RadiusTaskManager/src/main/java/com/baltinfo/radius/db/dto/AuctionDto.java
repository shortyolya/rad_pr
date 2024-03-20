package com.baltinfo.radius.db.dto;

import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.constants.TypeSubjectConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * DTO для работы с торгами и имуществом дожника
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public class AuctionDto {
    private final TypeAuctionConstant typeAuction;
    private final Integer auctionParticipantLimitation;
    private final DateDto auctionDatePlan;
    private final DateDto auctionDateE;
    private final DateDto auctionRecepDateB;
    private final DateDto auctionRecepDateE;
    private final DateDto auctionDepDateE;
    private final String auctionAsvOrderNum;
    private final Integer auctionStageNum;
    private final String auctionNumberLots;
    private final String auctionAsvId;
    private final String auctionNote;
    private final Integer auctionDealTypeCost;

    private final String dpCrashFileNum;
    private final String dpArbitrage;
    private final String dpDecreeNum;
    private final DateDto dpDecreeDate;
    private final String dpArbitManagerNameF;
    private final String dpArbitManagerNameI;
    private final String dpArbitManagerNameO;
    private final String dpArbitManagerCro;
    private final String dpArbitManagerRegNum;
    private final String dpArbitManagerInn;
    private final String dpDepositOrder;
    private final String dpAccountsDetails;
    private final String dpAuctionRegOrder;
    private final String dpAuctionWinnerOrder;
    private final String dpAuctionResultPlace;
    private final String dpDealOrder;
    private final String dpPaymentsDates;
    private final DateDto dpDatePublJournal;
    private final DateDto dpDatePublEfir;
    private final Short dpIndTendManager;
    private final String dpTendManagerName;
    private final String dpTendManagerSname;
    private final String dpTendManagerOgrn;
    private final String dpParticipantRequirements;
    private final Short dpIndSendOper;
    private final String dpPayeeName;
    private final String dpPayeeInn;
    private final String dpPayeeKpp;
    private final String dpPayeeAccount;
    private final String dpPayeeBankName;
    private final String dpPayeeBankBik;
    private final String dpPayeeBankAccount;
    private final String dpContactEmail;
    private final String dpContactAsv;
    private final String dpDealAsv;
    private final String dpTendManagerProxy;
    private final String dpObjectLinks;
    private final String dpContacts;
    private final String dpRewardRadius;


    private final TypeSubjectConstant typeSubject;
    private final String debitorSubName;
    private final String debitorSubSName;
    private final String debitorPersonNameF;
    private final String debitorPersonNameI;
    private final String debitorPersonNameO;
    private final String debitorPersonSnils;
    private final String debitorInn;
    private final String debitorOgrn;
    private final String debitorAddrFact;
    private final String debitorAddrLegal;
    private final String debitorBik;
    private final Integer changePricePeriodMin;
    private final Integer startPriceTime;
    private final String debitorKpp;
    private final BigDecimal dpSroPaymentValue;
    private final BigDecimal dpSroPaymentSum;
    private final DateDto dpSroPaymentDate;
    private final String dpSroPaymentNote;

    private final List<LoadLotDto> lotList;
    private final List<LoadRsDto> rsList;

    private AuctionDto(TypeAuctionConstant typeAuction, Integer auctionParticipantLimitation, DateDto auctionDatePlan,
                       DateDto auctionDateE, DateDto auctionRecepDateB, DateDto auctionRecepDateE, DateDto auctionDepDateE,
                       String auctionAsvOrderNum, Integer auctionStageNum, String auctionAsvId,
                       String dpCrashFileNum, String dpArbitrage, String dpDecreeNum,
                       DateDto dpDecreeDate, String dpArbitManagerNameF, String dpArbitManagerNameI,
                       String dpArbitManagerNameO, String dpArbitManagerCro, String dpArbitManagerRegNum,
                       String dpArbitManagerInn, String dpDepositOrder, String dpAccountsDetails,
                       String dpAuctionRegOrder, String dpAuctionWinnerOrder, String dpAuctionResultPlace,
                       String dpDealOrder, String dpPaymentsDates, DateDto dpDatePublJournal, DateDto dpDatePublEfir,
                       Short dpIndTendManager, String dpTendManagerName, String dpTendManagerSname,
                       String dpTendManagerOgrn, String dpParticipantRequirements, Short dpIndSendOper,
                       String dpPayeeName, String dpPayeeInn, String dpPayeeKpp, String dpPayeeAccount,
                       String dpPayeeBankName, String dpPayeeBankBik, String dpPayeeBankAccount, String dpContactEmail,
                       String dpContactAsv, TypeSubjectConstant typeSubject, String debitorSubName,
                       String debitorSubSName, String debitorPersonNameF, String debitorPersonNameI,
                       String debitorPersonNameO, String debitorPersonSnils, String debitorInn, String debitorOgrn,
                       String debitorAddrFact, String debitorAddrLegal, List<LoadLotDto> lotList,
                       List<LoadRsDto> rsList, String dpDealAsv, String dpTendManagerProxy,
                       String dpObjectLinks, String dpContacts, String dpRewardRadius, String auctionNumberLots, String debitorBik,
                       Integer changePricePeriodMin, Integer startPriceTime, String auctionNote, String debitorKpp, Integer auctionDealTypeCost,
                       BigDecimal dpSroPaymentValue, BigDecimal dpSroPaymentSum, DateDto dpSroPaymentDate, String dpSroPaymentNote) {
        this.typeAuction = typeAuction;
        this.auctionParticipantLimitation = auctionParticipantLimitation;
        this.auctionDatePlan = auctionDatePlan;
        this.auctionDateE = auctionDateE;
        this.auctionRecepDateB = auctionRecepDateB;
        this.auctionRecepDateE = auctionRecepDateE;
        this.auctionDepDateE = auctionDepDateE;
        this.auctionAsvOrderNum = auctionAsvOrderNum;
        this.auctionStageNum = auctionStageNum;
        this.auctionAsvId = auctionAsvId;
        this.dpCrashFileNum = dpCrashFileNum;
        this.dpArbitrage = dpArbitrage;
        this.dpDecreeNum = dpDecreeNum;
        this.dpDecreeDate = dpDecreeDate;
        this.dpArbitManagerNameF = dpArbitManagerNameF;
        this.dpArbitManagerNameI = dpArbitManagerNameI;
        this.dpArbitManagerNameO = dpArbitManagerNameO;
        this.dpArbitManagerCro = dpArbitManagerCro;
        this.dpArbitManagerRegNum = dpArbitManagerRegNum;
        this.dpArbitManagerInn = dpArbitManagerInn;
        this.dpDepositOrder = dpDepositOrder;
        this.dpAccountsDetails = dpAccountsDetails;
        this.dpAuctionRegOrder = dpAuctionRegOrder;
        this.dpAuctionWinnerOrder = dpAuctionWinnerOrder;
        this.dpAuctionResultPlace = dpAuctionResultPlace;
        this.dpDealOrder = dpDealOrder;
        this.dpPaymentsDates = dpPaymentsDates;
        this.dpDatePublJournal = dpDatePublJournal;
        this.dpDatePublEfir = dpDatePublEfir;
        this.dpIndTendManager = dpIndTendManager;
        this.dpTendManagerName = dpTendManagerName;
        this.dpTendManagerSname = dpTendManagerSname;
        this.dpTendManagerOgrn = dpTendManagerOgrn;
        this.dpParticipantRequirements = dpParticipantRequirements;
        this.dpIndSendOper = dpIndSendOper;
        this.dpPayeeName = dpPayeeName;
        this.dpPayeeInn = dpPayeeInn;
        this.dpPayeeKpp = dpPayeeKpp;
        this.dpPayeeAccount = dpPayeeAccount;
        this.dpPayeeBankName = dpPayeeBankName;
        this.dpPayeeBankBik = dpPayeeBankBik;
        this.dpPayeeBankAccount = dpPayeeBankAccount;
        this.dpContactEmail = dpContactEmail;
        this.dpContactAsv = dpContactAsv;
        this.dpObjectLinks = dpObjectLinks;
        this.dpRewardRadius = dpRewardRadius;
        this.dpContacts = dpContacts;
        this.typeSubject = typeSubject;
        this.debitorSubName = debitorSubName;
        this.debitorSubSName = debitorSubSName;
        this.debitorPersonNameF = debitorPersonNameF;
        this.debitorPersonNameI = debitorPersonNameI;
        this.debitorPersonNameO = debitorPersonNameO;
        this.debitorPersonSnils = debitorPersonSnils;
        this.debitorInn = debitorInn;
        this.debitorOgrn = debitorOgrn;
        this.debitorAddrFact = debitorAddrFact;
        this.debitorAddrLegal = debitorAddrLegal;
        this.lotList = lotList;
        this.rsList = rsList == null ? new ArrayList<>() : rsList;
        this.dpDealAsv = dpDealAsv;
        this.dpTendManagerProxy = dpTendManagerProxy;
        this.auctionNumberLots = auctionNumberLots;
        this.debitorBik = debitorBik;
        this.changePricePeriodMin = changePricePeriodMin;
        this.startPriceTime = startPriceTime;
        this.auctionNote = auctionNote;
        this.debitorKpp = debitorKpp;
        this.auctionDealTypeCost = auctionDealTypeCost;
        this.dpSroPaymentValue = dpSroPaymentValue;
        this.dpSroPaymentSum = dpSroPaymentSum;
        this.dpSroPaymentDate = dpSroPaymentDate;
        this.dpSroPaymentNote = dpSroPaymentNote;
    }

    public static AuctionDtoBuilder builder() {
        return new AuctionDtoBuilder();
    }

    public TypeAuctionConstant getTypeAuction() {
        return typeAuction;
    }

    public Integer getAuctionParticipantLimitation() {
        return auctionParticipantLimitation;
    }

    public DateDto getAuctionDatePlan() {
        return auctionDatePlan;
    }

    public DateDto getAuctionDateE() {
        return auctionDateE;
    }

    public DateDto getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public DateDto getAuctionRecepDateE() {
        return auctionRecepDateE;
    }

    public DateDto getAuctionDepDateE() {
        return auctionDepDateE;
    }

    public String getAuctionAsvOrderNum() {
        return auctionAsvOrderNum;
    }

    public Integer getAuctionStageNum() {
        return auctionStageNum;
    }

    public String getAuctionAsvId() {
        return auctionAsvId;
    }

    public Integer getAuctionDealTypeCost() {
        return auctionDealTypeCost;
    }

    public String getDpCrashFileNum() {
        return dpCrashFileNum;
    }

    public String getDpArbitrage() {
        return dpArbitrage;
    }

    public String getDpDecreeNum() {
        return dpDecreeNum;
    }

    public DateDto getDpDecreeDate() {
        return dpDecreeDate;
    }

    public String getDpArbitManagerNameF() {
        return dpArbitManagerNameF;
    }

    public String getDpArbitManagerNameI() {
        return dpArbitManagerNameI;
    }

    public String getDpArbitManagerNameO() {
        return dpArbitManagerNameO;
    }

    public String getDpArbitManagerCro() {
        return dpArbitManagerCro;
    }

    public String getDpArbitManagerRegNum() {
        return dpArbitManagerRegNum;
    }

    public String getDpArbitManagerInn() {
        return dpArbitManagerInn;
    }

    public String getDpDepositOrder() {
        return dpDepositOrder;
    }

    public String getDpAccountsDetails() {
        return dpAccountsDetails;
    }

    public String getDpAuctionRegOrder() {
        return dpAuctionRegOrder;
    }

    public String getDpAuctionWinnerOrder() {
        return dpAuctionWinnerOrder;
    }

    public String getDpAuctionResultPlace() {
        return dpAuctionResultPlace;
    }

    public String getDpDealOrder() {
        return dpDealOrder;
    }

    public String getDpPaymentsDates() {
        return dpPaymentsDates;
    }

    public DateDto getDpDatePublJournal() {
        return dpDatePublJournal;
    }

    public DateDto getDpDatePublEfir() {
        return dpDatePublEfir;
    }

    public Short getDpIndTendManager() {
        return dpIndTendManager;
    }

    public String getDpTendManagerName() {
        return dpTendManagerName;
    }

    public String getDpTendManagerSname() {
        return dpTendManagerSname;
    }

    public String getDpTendManagerOgrn() {
        return dpTendManagerOgrn;
    }

    public String getDpParticipantRequirements() {
        return dpParticipantRequirements;
    }

    public Short getDpIndSendOper() {
        return dpIndSendOper;
    }

    public String getDpPayeeName() {
        return dpPayeeName;
    }

    public String getDpPayeeInn() {
        return dpPayeeInn;
    }

    public String getDpPayeeKpp() {
        return dpPayeeKpp;
    }

    public String getDpPayeeAccount() {
        return dpPayeeAccount;
    }

    public String getDpPayeeBankName() {
        return dpPayeeBankName;
    }

    public String getDpPayeeBankBik() {
        return dpPayeeBankBik;
    }

    public String getDpPayeeBankAccount() {
        return dpPayeeBankAccount;
    }

    public String getDpContactEmail() {
        return dpContactEmail;
    }

    public String getDpContactAsv() {
        return dpContactAsv;
    }

    public TypeSubjectConstant getTypeSubject() {
        return typeSubject;
    }

    public String getDebitorSubName() {
        return debitorSubName;
    }

    public String getDebitorSubSName() {
        return debitorSubSName;
    }

    public String getDebitorPersonNameF() {
        return debitorPersonNameF;
    }

    public String getDebitorPersonNameI() {
        return debitorPersonNameI;
    }

    public String getDebitorPersonNameO() {
        return debitorPersonNameO;
    }

    public String getDebitorPersonSnils() {
        return debitorPersonSnils;
    }

    public String getDebitorInn() {
        return debitorInn;
    }

    public String getDebitorOgrn() {
        return debitorOgrn;
    }

    public String getDebitorAddrFact() {
        return debitorAddrFact;
    }

    public String getDebitorAddrLegal() {
        return debitorAddrLegal;
    }

    public List<LoadLotDto> getLotList() {
        return lotList;
    }

    public List<LoadRsDto> getRsList() {
        return rsList;
    }

    public String getDpDealAsv() {
        return dpDealAsv;
    }

    public String getDpTendManagerProxy() {
        return dpTendManagerProxy;
    }

    public String getDpObjectLinks() {
        return dpObjectLinks;
    }

    public String getDpContacts() {
        return dpContacts;
    }

    public String getDpRewardRadius() {
        return dpRewardRadius;
    }

    public String getAuctionNumberLots() {
        return auctionNumberLots;
    }

    public String getDebitorBik() {
        return debitorBik;
    }

    public Integer getChangePricePeriodMin() {
        return changePricePeriodMin;
    }

    public Integer getStartPriceTime() {
        return startPriceTime;
    }

    public String getAuctionNote() {
        return auctionNote;
    }

    public String getDebitorKpp() {
        return debitorKpp;
    }

    public BigDecimal getDpSroPaymentValue() {
        return dpSroPaymentValue;
    }

    public BigDecimal getDpSroPaymentSum() {
        return dpSroPaymentSum;
    }

    public DateDto getDpSroPaymentDate() {
        return dpSroPaymentDate;
    }

    public String getDpSroPaymentNote() {
        return dpSroPaymentNote;
    }

    public static final class AuctionDtoBuilder {
        private TypeAuctionConstant typeAuction;
        private Integer auctionParticipantLimitation;
        private DateDto auctionDatePlan;
        private DateDto auctionDateE;
        private DateDto auctionRecepDateB;
        private DateDto auctionRecepDateE;
        private DateDto auctionDepDateE;
        private String auctionAsvOrderNum;
        private String auctionAsvId;
        private Integer auctionStageNum;
        private String auctionNote;
        private Integer auctionDealTypeCost;
        private String dpCrashFileNum;
        private String dpArbitrage;
        private String dpDecreeNum;
        private DateDto dpDecreeDate;
        private String dpArbitManagerNameF;
        private String dpArbitManagerNameI;
        private String dpArbitManagerNameO;
        private String dpArbitManagerCro;
        private String dpArbitManagerRegNum;
        private String dpArbitManagerInn;
        private String dpDepositOrder;
        private String dpAccountsDetails;
        private String dpAuctionRegOrder;
        private String dpAuctionWinnerOrder;
        private String dpAuctionResultPlace;
        private String dpDealOrder;
        private String dpPaymentsDates;
        private DateDto dpDatePublJournal;
        private DateDto dpDatePublEfir;
        private Short dpIndTendManager;
        private String dpTendManagerName;
        private String dpTendManagerSname;
        private String dpTendManagerOgrn;
        private String dpParticipantRequirements;
        private Short dpIndSendOper;
        private String dpPayeeName;
        private String dpPayeeInn;
        private String dpPayeeKpp;
        private String dpPayeeAccount;
        private String dpPayeeBankName;
        private String dpPayeeBankBik;
        private String dpPayeeBankAccount;
        private String dpContactEmail;
        private String dpContactAsv;
        private String dpObjectLinks;
        private String dpContacts;
        private String dpRewardRadius;
        private TypeSubjectConstant typeSubject;
        private String debitorSubName;
        private String debitorSubSName;
        private String debitorPersonNameF;
        private String debitorPersonNameI;
        private String debitorPersonNameO;
        private String debitorPersonSnils;
        private String debitorInn;
        private String debitorOgrn;
        private String debitorBik;
        private String debitorAddrFact;
        private String debitorAddrLegal;
        private List<LoadLotDto> lotList = new ArrayList<>();
        private List<LoadRsDto> rsList = new ArrayList<>();
        private String dpDealAsv;
        private String dpTendManagerProxy;
        private String auctionNumberLots;
        private Integer changePricePeriodMin;
        private Integer startPriceTime;
        private String debitorKpp;
        private BigDecimal dpSroPaymentValue;
        private BigDecimal dpSroPaymentSum;
        private DateDto dpSroPaymentDate;
        private String dpSroPaymentNote;

        private AuctionDtoBuilder() {
        }

        public TypeAuctionConstant getTypeAuction() {
            return typeAuction;
        }

        public DateDto getAuctionRecepDateB() {
            return auctionRecepDateB;
        }

        public DateDto getAuctionRecepDateE() {
            return auctionRecepDateE;
        }

        public DateDto getAuctionDatePlan() {
            return auctionDatePlan;
        }

        public DateDto getAuctionDateE() {
            return auctionDateE;
        }

        public AuctionDtoBuilder withTypeAuction(TypeAuctionConstant typeAuction) {
            this.typeAuction = typeAuction;
            return this;
        }

        public AuctionDtoBuilder withAuctionParticipantLimitation(Integer auctionParticipantLimitation) {
            this.auctionParticipantLimitation = auctionParticipantLimitation;
            return this;
        }

        public AuctionDtoBuilder withAuctionDatePlan(DateDto auctionDatePlan) {
            this.auctionDatePlan = auctionDatePlan;
            return this;
        }

        public AuctionDtoBuilder withAuctionDateE(DateDto auctionDateE) {
            this.auctionDateE = auctionDateE;
            return this;
        }

        public AuctionDtoBuilder withAuctionRecepDateB(DateDto auctionRecepDateB) {
            this.auctionRecepDateB = auctionRecepDateB;
            return this;
        }

        public AuctionDtoBuilder withAuctionRecepDateE(DateDto auctionRecepDateE) {
            this.auctionRecepDateE = auctionRecepDateE;
            return this;
        }

        public AuctionDtoBuilder withAuctionDepDateE(DateDto auctionDepDateE) {
            this.auctionDepDateE = auctionDepDateE;
            return this;
        }

        public AuctionDtoBuilder withAuctionAsvOrderNum(String auctionAsvOrderNum) {
            this.auctionAsvOrderNum = auctionAsvOrderNum;
            return this;
        }

        public AuctionDtoBuilder withAuctionAsvId(String auctionAsvId) {
            this.auctionAsvId = auctionAsvId;
            return this;
        }

        public AuctionDtoBuilder withAuctionStageNum(Integer auctionStageNum) {
            this.auctionStageNum = auctionStageNum;
            return this;
        }

        public AuctionDtoBuilder withAuctionDealTypeCost(Integer auctionDealTypeCost) {
            this.auctionDealTypeCost = auctionDealTypeCost;
            return this;
        }

        public AuctionDtoBuilder withDpCrashFileNum(String dpCrashFileNum) {
            this.dpCrashFileNum = dpCrashFileNum;
            return this;
        }

        public AuctionDtoBuilder withDpArbitrage(String dpArbitrage) {
            this.dpArbitrage = dpArbitrage;
            return this;
        }

        public AuctionDtoBuilder withDpDecreeNum(String dpDecreeNum) {
            this.dpDecreeNum = dpDecreeNum;
            return this;
        }

        public AuctionDtoBuilder withDpDecreeDate(DateDto dpDecreeDate) {
            this.dpDecreeDate = dpDecreeDate;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerNameF(String dpArbitManagerNameF) {
            this.dpArbitManagerNameF = dpArbitManagerNameF;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerNameI(String dpArbitManagerNameI) {
            this.dpArbitManagerNameI = dpArbitManagerNameI;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerNameO(String dpArbitManagerNameO) {
            this.dpArbitManagerNameO = dpArbitManagerNameO;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerCro(String dpArbitManagerCro) {
            this.dpArbitManagerCro = dpArbitManagerCro;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerRegNum(String dpArbitManagerRegNum) {
            this.dpArbitManagerRegNum = dpArbitManagerRegNum;
            return this;
        }

        public AuctionDtoBuilder withDpArbitManagerInn(String dpArbitManagerInn) {
            this.dpArbitManagerInn = dpArbitManagerInn;
            return this;
        }

        public AuctionDtoBuilder withDpDepositOrder(String dpDepositOrder) {
            this.dpDepositOrder = dpDepositOrder;
            return this;
        }

        public AuctionDtoBuilder withDpAccountsDetails(String dpAccountsDetails) {
            this.dpAccountsDetails = dpAccountsDetails;
            return this;
        }

        public AuctionDtoBuilder withDpAuctionRegOrder(String dpAuctionRegOrder) {
            this.dpAuctionRegOrder = dpAuctionRegOrder;
            return this;
        }

        public AuctionDtoBuilder withDpAuctionWinnerOrder(String dpAuctionWinnerOrder) {
            this.dpAuctionWinnerOrder = dpAuctionWinnerOrder;
            return this;
        }

        public AuctionDtoBuilder withDpAuctionResultPlace(String dpAuctionResultPlace) {
            this.dpAuctionResultPlace = dpAuctionResultPlace;
            return this;
        }

        public AuctionDtoBuilder withDpDealOrder(String dpDealOrder) {
            this.dpDealOrder = dpDealOrder;
            return this;
        }

        public AuctionDtoBuilder withDpPaymentsDates(String dpPaymentsDates) {
            this.dpPaymentsDates = dpPaymentsDates;
            return this;
        }

        public AuctionDtoBuilder withDpDatePublJournal(DateDto dpDatePublJournal) {
            this.dpDatePublJournal = dpDatePublJournal;
            return this;
        }

        public AuctionDtoBuilder withDpDatePublEfir(DateDto dpDatePublEfir) {
            this.dpDatePublEfir = dpDatePublEfir;
            return this;
        }

        public AuctionDtoBuilder withDpIndTendManager(Short dpIndTendManager) {
            this.dpIndTendManager = dpIndTendManager;
            return this;
        }

        public AuctionDtoBuilder withDpTendManagerName(String dpTendManagerName) {
            this.dpTendManagerName = dpTendManagerName;
            return this;
        }

        public AuctionDtoBuilder withDpTendManagerSname(String dpTendManagerSname) {
            this.dpTendManagerSname = dpTendManagerSname;
            return this;
        }

        public AuctionDtoBuilder withDpTendManagerOgrn(String dpTendManagerOgrn) {
            this.dpTendManagerOgrn = dpTendManagerOgrn;
            return this;
        }

        public AuctionDtoBuilder withDpParticipantRequirements(String dpParticipantRequirements) {
            this.dpParticipantRequirements = dpParticipantRequirements;
            return this;
        }

        public AuctionDtoBuilder withDpIndSendOper(Short dpIndSendOper) {
            this.dpIndSendOper = dpIndSendOper;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeName(String dpPayeeName) {
            this.dpPayeeName = dpPayeeName;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeInn(String dpPayeeInn) {
            this.dpPayeeInn = dpPayeeInn;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeKpp(String dpPayeeKpp) {
            this.dpPayeeKpp = dpPayeeKpp;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeAccount(String dpPayeeAccount) {
            this.dpPayeeAccount = dpPayeeAccount;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeBankName(String dpPayeeBankName) {
            this.dpPayeeBankName = dpPayeeBankName;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeBankBik(String dpPayeeBankBik) {
            this.dpPayeeBankBik = dpPayeeBankBik;
            return this;
        }

        public AuctionDtoBuilder withDpPayeeBankAccount(String dpPayeeBankAccount) {
            this.dpPayeeBankAccount = dpPayeeBankAccount;
            return this;
        }

        public AuctionDtoBuilder withDpContactEmail(String dpContactEmail) {
            this.dpContactEmail = dpContactEmail;
            return this;
        }

        public AuctionDtoBuilder withDpContactAsv(String dpContactAsv) {
            this.dpContactAsv = dpContactAsv;
            return this;
        }

        public AuctionDtoBuilder withDpDealAsv(String dpDealAsv) {
            this.dpDealAsv = dpDealAsv;
            return this;
        }

        public AuctionDtoBuilder withDpTendManagerProxy(String dpTendManagerProxy) {
            this.dpTendManagerProxy = dpTendManagerProxy;
            return this;
        }

        public AuctionDtoBuilder withTypeSubject(TypeSubjectConstant typeSubject) {
            this.typeSubject = typeSubject;
            return this;
        }

        public AuctionDtoBuilder withDebitorSubName(String debitorSubName) {
            this.debitorSubName = debitorSubName;
            return this;
        }

        public AuctionDtoBuilder withDebitorSubSName(String debitorSubSName) {
            this.debitorSubSName = debitorSubSName;
            return this;
        }

        public AuctionDtoBuilder withDebitorPersonNameF(String debitorPersonNameF) {
            this.debitorPersonNameF = debitorPersonNameF;
            return this;
        }

        public AuctionDtoBuilder withDebitorPersonNameI(String debitorPersonNameI) {
            this.debitorPersonNameI = debitorPersonNameI;
            return this;
        }

        public AuctionDtoBuilder withDebitorPersonNameO(String debitorPersonNameO) {
            this.debitorPersonNameO = debitorPersonNameO;
            return this;
        }

        public AuctionDtoBuilder withDebitorPersonSnils(String debitorPersonSnils) {
            this.debitorPersonSnils = debitorPersonSnils;
            return this;
        }

        public AuctionDtoBuilder withDebitorInn(String debitorInn) {
            this.debitorInn = debitorInn;
            return this;
        }

        public AuctionDtoBuilder withDebitorOgrn(String debitorOgrn) {
            this.debitorOgrn = debitorOgrn;
            return this;
        }

        public AuctionDtoBuilder withDebitorAddrFact(String debitorAddrFact) {
            this.debitorAddrFact = debitorAddrFact;
            return this;
        }

        public AuctionDtoBuilder withDebitorAddrLegal(String debitorAddrLegal) {
            this.debitorAddrLegal = debitorAddrLegal;
            return this;
        }

        public AuctionDtoBuilder withLotList(List<LoadLotDto> lotList) {
            this.lotList = lotList == null ? new ArrayList<>() : lotList;
            return this;
        }

        public AuctionDtoBuilder withRsList(List<LoadRsDto> rsList) {
            this.rsList = rsList == null ? new ArrayList<>() : rsList;
            return this;
        }

        public AuctionDtoBuilder withDpContacts(String dpContacts) {
            this.dpContacts = dpContacts;
            return this;
        }

        public AuctionDtoBuilder withDpObjectLinks(String dpObjectLinks) {
            this.dpObjectLinks = dpObjectLinks;
            return this;
        }

        public AuctionDtoBuilder withDpRewardRadius(String dpRewardRadius) {
            this.dpRewardRadius = dpRewardRadius;
            return this;
        }

        public AuctionDtoBuilder withAuctionNumberLots(String auctionNumberLots) {
            this.auctionNumberLots = auctionNumberLots;
            return this;
        }

        public AuctionDtoBuilder withDebitorBik(String debitorBik) {
            this.debitorBik = debitorBik;
            return this;
        }

        public AuctionDtoBuilder withChangePricePeriodMin(Integer changePricePeriodMin) {
            this.changePricePeriodMin = changePricePeriodMin;
            return this;
        }

        public AuctionDtoBuilder withStartPriceTime(Integer startPriceTime) {
            this.startPriceTime = startPriceTime;
            return this;
        }

        public AuctionDtoBuilder withAuctionNote(String auctionNote) {
            this.auctionNote = auctionNote;
            return this;
        }

        public AuctionDtoBuilder withDebitorKpp(String debitorKpp) {
            this.debitorKpp = debitorKpp;
            return this;
        }

        public AuctionDtoBuilder withDpSroPaymentValue(BigDecimal dpSroPaymentValue) {
            this.dpSroPaymentValue = dpSroPaymentValue;
            return this;
        }
        public AuctionDtoBuilder withDpSroPaymentSum(BigDecimal dpSroPaymentSum) {
            this.dpSroPaymentSum = dpSroPaymentSum;
            return this;
        }
        public AuctionDtoBuilder withDpSroPaymentDate(DateDto dpSroPaymentDate) {
            this.dpSroPaymentDate = dpSroPaymentDate;
            return this;
        }
        public AuctionDtoBuilder withDpSroPaymentNote(String dpSroPaymentNote) {
            this.dpSroPaymentNote = dpSroPaymentNote;
            return this;
        }

        public AuctionDto build() {
            return new AuctionDto(typeAuction, auctionParticipantLimitation, auctionDatePlan, auctionDateE,
                    auctionRecepDateB, auctionRecepDateE, auctionDepDateE, auctionAsvOrderNum, auctionStageNum, auctionAsvId,
                    dpCrashFileNum, dpArbitrage, dpDecreeNum, dpDecreeDate, dpArbitManagerNameF, dpArbitManagerNameI,
                    dpArbitManagerNameO, dpArbitManagerCro, dpArbitManagerRegNum, dpArbitManagerInn, dpDepositOrder,
                    dpAccountsDetails, dpAuctionRegOrder, dpAuctionWinnerOrder, dpAuctionResultPlace, dpDealOrder,
                    dpPaymentsDates, dpDatePublJournal, dpDatePublEfir, dpIndTendManager, dpTendManagerName,
                    dpTendManagerSname, dpTendManagerOgrn, dpParticipantRequirements, dpIndSendOper, dpPayeeName,
                    dpPayeeInn, dpPayeeKpp, dpPayeeAccount, dpPayeeBankName, dpPayeeBankBik, dpPayeeBankAccount,
                    dpContactEmail, dpContactAsv, typeSubject, debitorSubName, debitorSubSName, debitorPersonNameF,
                    debitorPersonNameI, debitorPersonNameO, debitorPersonSnils, debitorInn, debitorOgrn,
                    debitorAddrFact, debitorAddrLegal, lotList, rsList, dpDealAsv, dpTendManagerProxy,
                    dpObjectLinks, dpContacts, dpRewardRadius, auctionNumberLots, debitorBik, changePricePeriodMin,
                    startPriceTime, auctionNote, debitorKpp, auctionDealTypeCost, dpSroPaymentValue, dpSroPaymentSum,
                    dpSroPaymentDate, dpSroPaymentNote);
        }
    }
}
