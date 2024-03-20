package com.baltinfo.radius.bankruptcy;

import com.baltinfo.radius.db.constants.AuctionStepFormConstant;
import com.baltinfo.radius.db.constants.EisCurrencyUnid;
import com.baltinfo.radius.db.constants.SaleGuides;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionUnid;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.db.model.ReductionSchedule;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.exchange.ExchangeEntityParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public class BkrLotParams implements ExchangeEntityParams {
    private static final Logger logger = LoggerFactory.getLogger(BkrLotParams.class);

    private static final Long END_TIME_DP = 30L;  // 30 минут
    private static final Long TCP_START_PRICE_UNID = 3L; // Минуты(APRG.TYPE_CHANGE_PERIOD)

    private long lotUnidForUpdate;
    private long saleGuideUnid;
    private long typeAuctionUnid;
    private BigDecimal startCost;
    private BigDecimal lotStepValue;
    private BigDecimal lotStepDecreaseValue;
    private BigDecimal lotSumDeposit;
    private String singleClassifCode;
    private BigDecimal minCostValue;
    private BigDecimal lotStepProcent;
    private String lotAuctionTheme;
    private String lotPublHeader;
    private String auctionRecepDateB;
    private String auctionRecepDateE;
    private String auctionDepDateE;
    private String auctionDateB;
    private String auctionDateE;
    private String lotNumber;
    private String lotReviewDebitorOrder;
    private String lotPledgeBank;
    private int auctionIndDown;
    private int auctionEndTerm;
    private int indPublish;
    private int lotIndPledge;
    private long auctionEndTime;
    private long lotChangePriceHour;
    private long lotChangePriceMin;
    private BigDecimal typeDealObject;
    private BigDecimal srfUnid;
    private BigDecimal caCode;
    private BigDecimal lotDepositPer;
    private int lotIndRightEnsure;
    private String lotAuctionHouseLink;
    private String lotManagerFio;
    private String lotManagerEmail;
    private String lotManagerPhone;
    private String lotAsvLink;
    private String doNamePublHeader;

    private List<BkrReductionScheduleParams> reductionSchedule;
    private List<IBkrPublicationBodyParams> pictures;
    private BkrAdditionalDataParams lotDepositDocument;
    private BkrAdditionalDataParams saleDealDraftPropertyDocument;

    private String pathToFiles;
    private String logoFilePath;
    private Auction auction;
    private Lot lot;
    private ObjCost objCost;
    private ObjCost minCost;
    private List<IBkrPublicationBodyParams> otherDocs;
    private ObjectJPA object;
    private ParticipantAgent paSaleManager;
    private String lotAsvId;
    private Address address;
    private BigDecimal tcpStartPriceUnid;
    private BigDecimal lotStartPricePeriod;
    private BigDecimal lotOperatorDeposit;
    private Long curUnid;

    public BkrLotParams(Auction auction,
                        Lot lot,
                        ObjectJPA object,
                        String scCode,
                        ObjCost objCost,
                        BigDecimal srfUnid,
                        List<ReductionSchedule> redSched,
                        List<Picture> pics,
                        Document deposit,
                        Document saleDealDraft,
                        String pathToFiles,
                        ObjCost minCost,
                        Long caCode,
                        String logoFilePath,
                        List<Document> otherDocs,
                        ParticipantAgent paSaleManager,
                        Address address) throws Exception {
        this.auction = auction;
        this.lot = lot;
        this.object = object;
        this.singleClassifCode = scCode;
        this.objCost = objCost;
        this.srfUnid = srfUnid;
        this.pathToFiles = pathToFiles;
        this.logoFilePath = logoFilePath;
        this.minCost = minCost;
        this.paSaleManager = paSaleManager;
        initValues();
        initReductionSchedule(redSched);
        initPictures(pics);
        this.lotDepositDocument = createDocument(deposit, BkrTypeAdditionalData.TAD_DEPOSIT.getUnid());
        this.saleDealDraftPropertyDocument = createDocument(saleDealDraft, BkrTypeAdditionalData.TAD_SALE_DEAL_DRAFT_PROPERTY.getUnid());
        if (caCode != null) {
            this.caCode = new BigDecimal(caCode);
        }
        this.otherDocs = otherDocs != null
                ? otherDocs.stream()
                .map(doc -> doc.getDfScanUnid() != null ? doc.getDfScanUnid() : doc.getDfUnid())
                .map(df -> new BkrPublicationBodyParams(df.getDfName(),
                        formFilePath(df, pathToFiles), BkrTypePublicationBody.ANY.getUnid()))
                .collect(Collectors.toList())
                : new ArrayList<>();
        this.address = address;
        this.curUnid = getCurrency(lot.getCurUnid());
    }

    private void initValues() {
        auctionIndDown = 0;
        Long radiusTypeAuctionUnid = auction.getTypeAuctionUnid().getTypeAuctionUnid();
        if (radiusTypeAuctionUnid.equals(TypeAuctionUnid.AUCTION)) {
            if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.OPEN.getId())) {
                typeAuctionUnid = BkrAuctionType.AUCTION_OPEN.getCode();
            } else if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.CLOSE.getId())) {
                typeAuctionUnid = BkrAuctionType.AUCTION_CLOSED.getCode();
            }
            auctionIndDown = 0;
        } else if (radiusTypeAuctionUnid.equals(TypeAuctionUnid.CONTEST)
                || Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.CONTEST_ENGLISH)) {
            if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.OPEN.getId())) {
                typeAuctionUnid = BkrAuctionType.CONTEST_OPEN.getCode();
            } else if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.CLOSE.getId())) {
                typeAuctionUnid = BkrAuctionType.CONTEST_CLOSED.getCode();
            }
            auctionIndDown = 0;
        } else if (radiusTypeAuctionUnid.equals(TypeAuctionUnid.HOLLAND)) {
            if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.OPEN.getId())) {
                typeAuctionUnid = BkrAuctionType.AUCTION_OPEN.getCode();
            } else if (auction.getAuctionStepForm().equals(AuctionStepFormConstant.CLOSE.getId())) {
                typeAuctionUnid = BkrAuctionType.AUCTION_CLOSED.getCode();
            }
            auctionIndDown = 1;
        } else if (radiusTypeAuctionUnid.equals(TypeAuctionUnid.PUBLIC)) {
            typeAuctionUnid = BkrAuctionType.PUBLIC_SALE_OF_PROPERTY.getCode();
            auctionIndDown = 0;
            lotChangePriceHour = 0L;
            lotChangePriceMin = 0L;
        } else if (radiusTypeAuctionUnid.equals(TypeAuctionUnid.PUBLIC_OFFER)) {
            typeAuctionUnid = BkrAuctionType.PUBLIC_OFFER.getCode();
            auctionIndDown = 0;
            lotChangePriceHour = 0L;
            lotChangePriceMin = 0L;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.PROPOSAL_REQUEST)) {
            typeAuctionUnid = BkrAuctionType.PROPOSAL_REQUEST.getCode();
            auctionIndDown = 0;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.PUBLIC_OFFER_SERIES)) {
            typeAuctionUnid = BkrAuctionType.PUBLIC_OFFER_SERIES.getCode();
            auctionIndDown = 0;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.COMPETITIVE_SALE)) {
            typeAuctionUnid = BkrAuctionType.COMPETITIVE_SALE.getCode();
            auctionIndDown = 0;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.PRICELESS)) {
            typeAuctionUnid = BkrAuctionType.SALE_WITHOUT_PRICE.getCode();
            auctionIndDown = 0;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.COLLECTION_OF_OFFER)) {
            typeAuctionUnid = BkrAuctionType.COLLECTION_OF_OFFER.getCode();
            auctionIndDown = 0;
        } else if (Objects.equals(radiusTypeAuctionUnid, TypeAuctionUnid.TRADE_SESSION)) {
            typeAuctionUnid = BkrAuctionType.TRADE_SESSION.getCode();
            auctionIndDown = 0;
        }

        auctionEndTerm = 0;
        auctionEndTime = 0L;
        if (Objects.equals(auction.getSgUnid(), SaleGuides.DEBITOR_PROPERTY)) {
            saleGuideUnid = BkrSaleGuides.DEBITOR_PROPERTY.getSgUnid();
            typeDealObject = new BigDecimal(BkrSaleGuides.DEBITOR_PROPERTY.getTdoUnid());
        } else if (Objects.equals(auction.getSgUnid(), SaleGuides.PRIVATE_PROPERTY) ||
                Objects.equals(auction.getSgUnid(), SaleGuides.BANK_ASSETS) ||
                Objects.equals(auction.getSgUnid(), SaleGuides.MORTGAGE_PROPERTY) ||
                Objects.equals(auction.getSgUnid(), SaleGuides.DISTRESSED_PROPERTY) ||
                Objects.equals(auction.getSgUnid(), SaleGuides.LEASING_BANK_ASSETS)) {
            saleGuideUnid = BkrSaleGuides.PRIVATE_PROPERTY.getSgUnid();
            typeDealObject = new BigDecimal(BkrSaleGuides.PRIVATE_PROPERTY.getTdoUnid());
        }

        if (Objects.equals(typeAuctionUnid, BkrAuctionType.AUCTION_OPEN.getCode())) {
            auctionEndTerm = BkrEndTerms.LAST_APPLICATION.getCode();
            auctionEndTime = END_TIME_DP;
        } else if (Objects.equals(typeAuctionUnid, BkrAuctionType.AUCTION_CLOSED.getCode())) {
            auctionEndTerm = BkrEndTerms.AUCTION_TIME.getCode();
            auctionEndTime = 0L;
        }

        lotAuctionTheme = lot.getLotAuctionTheme();
        auctionRecepDateB = convertDateToString(auction.getAuctionRecepDateB());
        auctionRecepDateE = convertDateToString(auction.getAuctionRecepDateE());
        auctionDepDateE = convertDateToString(auction.getAuctionDepDateE());
        if (auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
            auctionDateB = convertDateToString(auction.getAuctionRecepDateB());
            auctionDateE = convertDateToString(auction.getAuctionRecepDateE());
        } else if (auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.PRICELESS.getCode())) {
            auctionDateB = convertDateToString(auction.getAuctionResultDate());
        } else {
            auctionDateB = convertDateToString(auction.getAuctionDateB());
            auctionDateE = convertDateToString(auction.getAuctionDateE());
        }
        lotStepValue = lot.getLotStepValue();
        lotStepDecreaseValue = lot.getLotStepDecreaseValue();
        lotSumDeposit = lot.getLotSumDeposit();
        startCost = objCost == null ? null : objCost.getCostValue();
        indPublish = 0;
        lotPublHeader = (lot.getLotPublHeader() == null || lot.getLotPublHeader().isEmpty())
                ? cutString(lot.getLotAuctionTheme(), 2000)
                : cutString(lot.getLotPublHeader(), 2000);

        doNamePublHeader = lot.getLotPublHeader();

        lotAuctionHouseLink = lot.getLotRadSite();
        if (paSaleManager != null) {
            Subject subject = paSaleManager.getSubSubUnid();
            lotManagerFio = subject.getSubName();
            lotManagerEmail = subject.getSubEMail();
            lotManagerPhone = subject.getSubMobilePhone();
        }
        lotAsvLink = lot.getLotAsvLink();

        lotNumber = lot.getLotNumber() == null ? "1" : lot.getLotNumber() + "";
        lotReviewDebitorOrder = lot.getLotReviewDebitorOrder();
        minCostValue = minCost == null ? null : minCost.getCostValue();
        lotStepProcent = lot.getLotStepProcent();
        lotIndPledge = 0;
        lotPledgeBank = null;
        lotUnidForUpdate = 0L;
        lotDepositPer = lot.getLotDepositPer();
        lotIndRightEnsure = lot.getLotIndRightEnsure() == null ? 0 : lot.getLotIndRightEnsure().intValue();
        lotAsvId = lot.getLotAsvId();
        lotStartPricePeriod = lot.getLotStartPriceTime() == null ? null : BigDecimal.valueOf(lot.getLotStartPriceTime());
        tcpStartPriceUnid = lotStartPricePeriod == null ? null : BigDecimal.valueOf(TCP_START_PRICE_UNID);
        lotOperatorDeposit = lot.getLotOperatorDeposit();
    }

    private void initReductionSchedule(List<ReductionSchedule> redSched) throws Exception {
        if (redSched == null || redSched.isEmpty()) {
            return;
        }
        reductionSchedule = redSched.stream()
                .map(rs -> new BkrReductionScheduleParams(rs))
                .collect(Collectors.toList());
    }

    private void initPictures(List<Picture> pics) {
        pictures = new ArrayList<>();
        if (pics == null || pics.isEmpty()) {
            return;
        }
        Picture firstPicture = pics.get(0);

        BkrPictureParams bkrPict = new BkrPictureParams(firstPicture.getDfUnid().getDfName(),
                formFilePath(firstPicture.getDfUnid(), pathToFiles), logoFilePath, BkrTypePublicationBody.PICTURE.getUnid(), null);
        if (bkrPict.getFileBody() != null) {
            pictures.add(bkrPict);
        }
        int pictureNum = 1;
        for (Picture picture : pics) {
            BkrPictureParams bkrPicture = new BkrPictureParams(picture.getDfUnid().getDfName(),
                    formFilePath(picture.getDfUnid(), pathToFiles), logoFilePath, BkrTypePublicationBody.GALLERY.getUnid(), pictureNum);
            if (bkrPicture.getFileBody() != null) {
                pictures.add(bkrPicture);
                pictureNum++;
            }
        }
    }

    private BkrAdditionalDataParams createDocument(Document document, Long tadUnid) {
        if (document == null) {
            return null;
        }
        DocFile docFile = document.getDfScanUnid() != null
                ? document.getDfScanUnid()
                : document.getDfUnid();
        return new BkrAdditionalDataParams(docFile.getDfName(), tadUnid, formFilePath(docFile, pathToFiles));
    }

    private String formFilePath(DocFile df, String pathToFiles) {
        String fileName = df.getDfFilePath();
        if (File.separator.equals("\\")) {
            fileName = fileName.replace("/", File.separator);
        } else {
            fileName = fileName.replace("\\", File.separator);
        }
        return pathToFiles + File.separator + fileName;
    }

    private String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return date == null ? null : sdf.format(date);
    }

    private String cutString(String str, int len) {
        if (str == null) {
            return null;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    private Long getCurrency(Long eisBkrUnid) {
        if (eisBkrUnid == null) return BkrCurrency.RUB.getUnid();
        if (eisBkrUnid.equals(EisCurrencyUnid.USD.getUnid())) return BkrCurrency.USD.getUnid();
        if (eisBkrUnid.equals(EisCurrencyUnid.EURO.getUnid())) return BkrCurrency.EURO.getUnid();
        return BkrCurrency.RUB.getUnid();
    }

    public long getSaleGuideUnid() {
        return saleGuideUnid;
    }

    public long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public BigDecimal getStartCost() {
        return startCost;
    }

    public String getLotAuctionTheme() {
        return lotAuctionTheme;
    }

    public String getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public String getAuctionRecepDateE() {
        return auctionRecepDateE;
    }

    public String getAuctionDepDateE() {
        return auctionDepDateE;
    }

    public String getAuctionDateB() {
        return auctionDateB;
    }

    public long getLotUnidForUpdate() {
        return lotUnidForUpdate;
    }

    public BigDecimal getLotStepDecreaseValue() {
        return lotStepDecreaseValue;
    }

    public BigDecimal getLotSumDeposit() {
        return lotSumDeposit;
    }

    public String getSingleClassifCode() {
        return singleClassifCode;
    }

    public BigDecimal getMinCostValue() {
        return minCostValue;
    }

    public BigDecimal getLotStepProcent() {
        return lotStepProcent;
    }

    public String getAuctionDateE() {
        return auctionDateE;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getLotReviewDebitorOrder() {
        return lotReviewDebitorOrder;
    }

    public String getLotPledgeBank() {
        return lotPledgeBank;
    }

    public int getAuctionIndDown() {
        return auctionIndDown;
    }

    public int getAuctionEndTerm() {
        return auctionEndTerm;
    }

    public int getIndPublish() {
        return indPublish;
    }

    public int getLotIndPledge() {
        return lotIndPledge;
    }

    public long getAuctionEndTime() {
        return auctionEndTime;
    }

    public long getLotChangePriceHour() {
        return lotChangePriceHour;
    }

    public long getLotChangePriceMin() {
        return lotChangePriceMin;
    }

    public BigDecimal getTypeDealObject() {
        return typeDealObject;
    }

    public BigDecimal getSrfUnid() {
        return srfUnid;
    }

    public BigDecimal getLotStepValue() {
        return lotStepValue;
    }

    public String getLotPublHeader() {
        return lotPublHeader;
    }

    public List<BkrReductionScheduleParams> getReductionSchedule() {
        return reductionSchedule;
    }

    public List<IBkrPublicationBodyParams> getPictures() {
        return pictures;
    }

    public BkrAdditionalDataParams getLotDepositDocument() {
        return lotDepositDocument;
    }

    public BkrAdditionalDataParams getSaleDealDraftPropertyDocument() {
        return saleDealDraftPropertyDocument;
    }

    public BigDecimal getCaCode() {
        return caCode;
    }

    public BigDecimal getLotDepositPer() {
        return lotDepositPer;
    }

    public int getLotIndRightEnsure() {
        return lotIndRightEnsure;
    }

    public List<IBkrPublicationBodyParams> getOtherDocs() {
        return otherDocs;
    }

    public String getDoNamePublHeader() {
        return doNamePublHeader;
    }

    public String getLotAuctionHouseLink() {
        return lotAuctionHouseLink;
    }

    public String getLotManagerFio() {
        return lotManagerFio;
    }

    public String getLotManagerEmail() {
        return lotManagerEmail;
    }

    public String getLotManagerPhone() {
        return lotManagerPhone;
    }

    public String getLotAsvLink() {
        return lotAsvLink;
    }

    public ObjectJPA getObject() {
        return object;
    }

    public String getLotAsvId() {
        return lotAsvId;
    }

    public Address getAddress() {
        return address;
    }

    public BigDecimal getTcpStartPriceUnid() {
        return tcpStartPriceUnid;
    }

    public BigDecimal getLotStartPricePeriod() {
        return lotStartPricePeriod;
    }

    public BigDecimal getLotOperatorDeposit() {
        return lotOperatorDeposit;
    }

    public Long getCurUnid() {
        return curUnid;
    }
}
