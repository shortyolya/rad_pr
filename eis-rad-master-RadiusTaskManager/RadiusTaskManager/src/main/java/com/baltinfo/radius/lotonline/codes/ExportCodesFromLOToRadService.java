package com.baltinfo.radius.lotonline.codes;

import com.baltinfo.radius.db.constants.*;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.ExportCodesFromLOController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.*;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
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
 * @since 07.08.2020
 */

public class ExportCodesFromLOToRadService {

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromLOToRadService.class);

    private final AuctionController auctionController;
    private final ExportCodesFromLOController exportCodesFromLOController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;
    private final RadApiClient radApiClient;
    private final TokenService tokenService;

    private final String CANT_FIND_LOT_ON_LO = "Не удалось найти лот на ЭТП lot-online";

    public ExportCodesFromLOToRadService(AuctionController auctionController, ExportCodesFromLOController exportCodesFromLOController,
                                         ExchangeProcController exchangeProcController, LotController lotController,  RadApiClient radApiClient, TokenService tokenService) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.exportCodesFromLOController = Objects.requireNonNull(exportCodesFromLOController, "Can't load exportCodesFromLO controller!");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
        this.radApiClient = Objects.requireNonNull(radApiClient, "Can't load radApiClient");
        this.tokenService = Objects.requireNonNull(tokenService, "Can't load tokenService");
    }

    public void runProcedure() {
        List<Auction> auctions = auctionController
                .findPublishedAuctionsWithoutEtpCode(Marketplaces.LOT_ONLINE.getMpUnid());
        for (Auction auction : auctions) {
            Tender tender = exportCodesFromLOController.getTenderById(auction.getAuctionEtpId());
            if (tender != null) {
                if (tender.getTenderCode() != null && tender.getIsPublished()) {
                    ExchangeProcRun epr = buildNewExchangeProcRun(auction.getAuctionUnid(), auction.getAuctionEtpId());
                    exchangeProcController.saveExchangeProcRun(epr);
                    List<Lot> radLotList = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
                    List<LotInfo> loLotList = exportCodesFromLOController.getLOLotsByTenderId(epr.getEprReceiverId());
                    List<RunningDetails> runningDetailsList = makeListRunningDetails(radLotList, loLotList);
                    Result<String, String> tokenResult = tokenService.getOpenPartToken();

                    for (RunningDetails rd : runningDetailsList) {
                        rd.setEprUnid(epr.getEprUnid());
                        if (rd.getRdReceiveId() == null) {
                            updateRunningDetailError(rd, CANT_FIND_LOT_ON_LO);
                            continue;
                        }
                        Lot radLot = radLotList.stream()
                                .filter(t -> t.getLotUnid().equals(rd.getRdSourceId()))
                                .findFirst()
                                .orElse(null);
                        LotInfo loLot = loLotList.stream()
                                .filter(t -> t.getId().equals(rd.getRdReceiveId()))
                                .findFirst()
                                .orElse(null);
                        if ((radLot != null) && (loLot != null)) {
                            radLot.setLotEtpCode(loLot.getLotCode());
                            radLot.setLotEtpId(loLot.getId());
                            Result<Lot, String> result = lotController.updateLot(radLot);
                            if (result.isSuccess()) {
                                rd.setRdResult(RunningDetailsStatus.SENT.getCode());
                                exchangeProcController.updateRunningDetails(rd);
                            } else {
                                updateRunningDetailError(rd, result.getError());
                            }
                        } else {
                            updateRunningDetailError(rd, CANT_FIND_LOT_ON_LO);
                        }
                        if (isNfaLot(radLot) && tokenResult.isSuccess()) {
                            logger.info("call updateLotVitrinaNfa for lotUnid = {}", loLot.getId());
                            radApiClient.updateLotVitrinaNfaLO(loLot.getId(), tokenResult.getResult());
                        }
                    }

                    auction.setAuctionEtpCode(tender.getTenderCode());
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

    private ExchangeProcRun buildNewExchangeProcRun(Long sourceId, Long receiverId) {
        ExchangeProcRun eprToAdd = new ExchangeProcRun();
        long paUnid = 1L;
        eprToAdd.setEprPaUnid(paUnid);
        eprToAdd.setEprRunDate(new Date());
        eprToAdd.setEprSourceId(sourceId);
        eprToAdd.setEprReceiverId(receiverId);
        eprToAdd.setEpUnid(ExchangeProcs.RECEIVE_CODES_FROM_LOT_ONLINE.getUnid());
        eprToAdd.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        return eprToAdd;
    }

    private List<RunningDetails> makeListRunningDetails(List<Lot> radLotList, List<LotInfo> loLotList) {
        List<RunningDetails> runningDetailsList = new ArrayList<>();
        for (Lot lot : radLotList) {
            RunningDetails runningDetails = new RunningDetails();
            runningDetails.setRdSourceId(lot.getLotUnid());
            runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
            try {
                LotInfo lotInfo = loLotList.stream()
                        .filter(l -> lot.getLotNumber().equals(l.getLotNumber().longValue()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("lot not found on lot-online by eis lotUnid = " + lot.getLotUnid()));
                runningDetails.setRdReceiveId(lotInfo.getId());
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

}
