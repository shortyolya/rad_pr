package com.baltinfo.radius.bankruptcy.codes;

import com.baltinfo.radius.db.constants.ClAsvCodeConstant;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.Marketplaces;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.constants.SaleCategoryCodeConstant;
import com.baltinfo.radius.db.constants.TypePartAgentConstant;
import com.baltinfo.radius.db.constants.TypeStateTradeConstants;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.ExportCodesFromBkrController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ClAsv;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjRole;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 16.03.2020
 */

public class ExportCodesFromBkrToRadService {

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromBkrToRadService.class);

    private final AuctionController auctionController;
    private final ExportCodesFromBkrController exportCodesFromBkrController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;
    private final RadApiClient radApiClient;
    private final TokenService tokenService;

    private final String CANT_FIND_LOT_ON_BKR = "Не удалось найти лот на ЭТП Банкротство";

    public ExportCodesFromBkrToRadService(AuctionController auctionController, ExportCodesFromBkrController exportCodesFromBkrController,
                                          ExchangeProcController exchangeProcController, LotController lotController, RadApiClient radApiClient, TokenService tokenService) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.exportCodesFromBkrController = Objects.requireNonNull(exportCodesFromBkrController, "Can't load service codes controller");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
        this.radApiClient = Objects.requireNonNull(radApiClient, "Can't load radApiClient");
        this.tokenService = Objects.requireNonNull(tokenService, "Can't load tokenService");
    }

    public void runProcedure() {
        List<Auction> auctions = auctionController
                .findPublishedAuctionsWithoutEtpCode(Marketplaces.BANKRUPTCY.getMpUnid());
        for (Auction auction : auctions) {
            VAuctionBkr vAuctionBkr = exportCodesFromBkrController.getAuctionFromBkr(auction.getAuctionEtpId());
            if (vAuctionBkr != null) {
                if (vAuctionBkr.getAuctionRegNum() != null) {
                    ExchangeProcRun epr = buildNewExchangeProcRun(auction.getAuctionUnid(), auction.getAuctionEtpId());
                    exchangeProcController.saveExchangeProcRun(epr);
                    List<Lot> radLotList = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
                    List<VAuctionLotAll> bkrLotList = exportCodesFromBkrController.getBkrLotsByAuctionUnid(epr.getEprReceiverId());
                    List<RunningDetails> runningDetailsList = makeListRunningDetails(radLotList, bkrLotList);
                    Result<String, String> tokenResult = tokenService.getOpenPartToken();
                    if (tokenResult.isError()) {
                        logger.warn("Error getOpenPartToken: " + tokenResult.getError());
                    }
                    for (RunningDetails rd : runningDetailsList) {
                        rd.setEprUnid(epr.getEprUnid());
                        if (rd.getRdReceiveId() == null) {
                            updateRunningDetailError(rd, CANT_FIND_LOT_ON_BKR);
                            continue;
                        }
                        Lot radLot = findLotInRadByLotUnid(radLotList, rd.getRdSourceId());
                        VAuctionLotAll bkrLot = findLotInBkrByLotUnid(bkrLotList, rd.getRdReceiveId());
                        if ((radLot != null) && (bkrLot != null)) {
                            radLot.setLotEtpCode(bkrLot.getLotNoticeNum());
                            radLot.setLotEtpId(bkrLot.getLotUnid());
                            Result<Lot, String> result = lotController.updateLot(radLot);
                            if (result.isSuccess()) {
                                rd.setRdResult(RunningDetailsStatus.SENT.getCode());
                                exchangeProcController.updateRunningDetails(rd);
                            } else {
                                updateRunningDetailError(rd, result.getError());
                            }
                        } else {
                            updateRunningDetailError(rd, CANT_FIND_LOT_ON_BKR);
                        }
                        if (isNfaLot(radLot) && tokenResult.isSuccess()) {
                            logger.info("call updateLotVitrinaNfa for lotUnid = {}", bkrLot.getLotUnid());
                            radApiClient.updateLotVitrinaNfa(bkrLot.getLotUnid(), tokenResult.getResult());
                        }
                    }

                    auction.setAuctionEtpCode(vAuctionBkr.getAuctionRegNum());
                    Result<Auction, String> result = auctionController.updateAuction(auction);
                    if (result.isSuccess()) {
                        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                        exchangeProcController.updateExchangeProcRun(epr);
                    } else {
                        updateEprErrorRunning(epr, result.getError());
                    }
                }
            }
        }
    }

    private List<RunningDetails> makeListRunningDetails(List<Lot> radLotList, List<VAuctionLotAll> bkrLotList) {
        List<RunningDetails> runningDetailsList = new ArrayList<>();
        for (Lot lot : radLotList) {
            RunningDetails runningDetails = new RunningDetails();
            runningDetails.setRdSourceId(lot.getLotUnid());
            runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
            try {
                VAuctionLotAll bkrLot = bkrLotList.stream()
                        .filter(l -> Long.parseLong(l.getLotNumber()) == lot.getLotNumber())
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("lot not found on bkr by eis lotUnid = " + lot.getLotUnid()));
                runningDetails.setRdReceiveId(bkrLot.getLotUnid());
            } catch (Exception ex) {
                logger.error("Error makeListRunningDetails", ex);
            }
            runningDetailsList.add(runningDetails);
        }
        return runningDetailsList;
    }

    private boolean isNfaLot(Lot lot) {
        return departmentNfa(lot) && categoryAsvNfa(lot) && categoryDpNfa(lot);
    }

    public Boolean departmentNfa(Lot lot) {
        ObjRole orSale = lotController.getObjRoleSale(lot.getObjUnid().getObjUnid());
        if (orSale != null) {
            return orSale.getTpaUnid().equals(TypePartAgentConstant.SALE_DEPARTMENT_SPB.getTpaUnid())
                    || orSale.getTpaUnid().equals(TypePartAgentConstant.SALE_DEPARTMENT_MSC.getTpaUnid());
        }
        return false;
    }

    public Boolean categoryAsvNfa(Lot lot) {
        ClAsv clAsv = lotController.getClAsvByUnid(lot.getCaUnid());
        if (clAsv != null && clAsv.getCaCode() != null) {
            return clAsv.getCaCode().equals(ClAsvCodeConstant.CAR_LOAN.getCode()) || clAsv.getCaCode().equals(ClAsvCodeConstant.MORTGAGE_LOANS.getCode())
                    || clAsv.getCaCode().equals(ClAsvCodeConstant.OVERDRAFT.getCode()) || clAsv.getCaCode().equals(ClAsvCodeConstant.RIGHT_CLAIM_INDIVIDUAL.getCode())
                    || clAsv.getCaCode().equals(ClAsvCodeConstant.RIGHT_CLAIM_LEGAL.getCode()) || clAsv.getCaCode().equals(ClAsvCodeConstant.RIGHT_CLAIM_OTHER.getCode())
                    || clAsv.getCaCode().equals(ClAsvCodeConstant.OTHER_LOANS.getCode());
        }
        return false;
    }

    public Boolean categoryDpNfa(Lot lot) {
        ObjSaleCategory objSaleCategory = lotController.getObjSaleCategoryByObjUnid(lot.getObjUnid().getObjUnid());
        if (objSaleCategory != null) {
            String scCode = objSaleCategory.getScUnid().getScCode().trim();
            return scCode.equals(SaleCategoryCodeConstant.RIGHT_CLAIM_LEGAL_LOAN.getCode()) || scCode.equals(SaleCategoryCodeConstant.RIGHT_CLAIM_LEGAL.getCode())
                    || scCode.equals(SaleCategoryCodeConstant.RIGHT_CLAIM_INDIVIDUAL.getCode()) || scCode.equals(SaleCategoryCodeConstant.RIGHT_CLAIM_PERSON.getCode());
        }
        return false;
    }

    private void updateRunningDetailError(RunningDetails runningDetails, String error) {
        runningDetails.setRdResult(RunningDetailsStatus.ERROR.getCode());
        runningDetails.setRdErrorInfo(error);
        exchangeProcController.updateRunningDetails(runningDetails);
    }

    private void updateEprErrorRunning(ExchangeProcRun epr, String error) {
        epr.setEprErrorText(error);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private ExchangeProcRun buildNewExchangeProcRun(Long eprSourseId, Long eprReceiverId) {
        ExchangeProcRun eprToAdd = new ExchangeProcRun();
        long paUnid = 1L;
        eprToAdd.setEprPaUnid(paUnid);
        eprToAdd.setEprRunDate(new Date());
        eprToAdd.setEprSourceId(eprSourseId);
        eprToAdd.setEprReceiverId(eprReceiverId);
        eprToAdd.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        eprToAdd.setEpUnid(ExchangeProcs.RECEIVE_CODES_FROM_BANKRUPTCY.getUnid());
        return eprToAdd;
    }

    private Lot findLotInRadByLotUnid(List<Lot> lots, Long lotUnid) {
        return lots.stream()
                .filter(lot -> lotUnid.equals(lot.getLotUnid()))
                .findFirst()
                .orElse(null);
    }

    private VAuctionLotAll findLotInBkrByLotUnid(List<VAuctionLotAll> lots, Long lotUnid) {
        return lots.stream()
                .filter(lot -> lotUnid.equals(lot.getLotUnid()))
                .findFirst()
                .orElse(null);
    }
}
