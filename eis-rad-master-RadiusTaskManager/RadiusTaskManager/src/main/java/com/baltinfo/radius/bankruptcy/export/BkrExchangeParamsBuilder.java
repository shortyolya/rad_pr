package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.bankruptcy.BkrLotParams;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ParticipantAgentController;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ClAsv;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.db.model.ReductionSchedule;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.exchange.ExchangeParamsBuilder;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public class BkrExchangeParamsBuilder implements ExchangeParamsBuilder<BkrLotParams> {

    private static final Logger logger = LoggerFactory.getLogger(BkrExchangeParamsBuilder.class);

    private final LotController lotController;
    private final ParticipantAgentController participantAgentController;
    private final String pathToFiles;
    private final String logoFilePath;

    public BkrExchangeParamsBuilder(LotController lotController, ParticipantAgentController participantAgentController, String pathToFiles, String logoFilePath) {
        this.lotController = lotController;
        this.participantAgentController = participantAgentController;
        this.pathToFiles = pathToFiles;
        this.logoFilePath = logoFilePath;
    }

    @Override
    public Result<BkrLotParams, String> buildParams(Long lotUnid) {
        try {
            Lot lot = lotController.getLot(lotUnid);
            Auction auction = lotController.getAuction(lot.getAuctionUnid());
            ObjectJPA object = lot.getObjUnid();
            ObjCost startCost = lotController.getStartObjCost(lot.getLotUnid());
            ObjSaleCategory objSaleCategory = lotController.getObjSaleCategoryByObjUnid(object.getObjUnid());
            SaleCategory saleCategory = objSaleCategory.getScUnid();
            BigDecimal srfUnid = lotController.getBankruptcySubRfUnidByObjUnid(lot.getObjUnid().getObjUnid());
            List<ReductionSchedule> reductionSchedules = lotController.getRedSchedsByLotUnid(lot.getLotUnid());
            List<Picture> pictures = lotController.getPicturesForExportToEtp(lot.getObjUnid().getObjUnid());

            List<Document> documents = lotController.getDocumentsForExportToEtpByLotAndObject(lot.getLotUnid(), lot.getObjUnid().getObjUnid());
            documents = documents.stream()
                    .filter(document -> document.getDfScanUnid() != null || document.getDfUnid() != null)
                    .collect(Collectors.toList());

            Document lotDepositDocument = documents.stream()
                    .filter(document -> document.getTdUnid().getTdUnid().equals(TypeDocConstant.RECEIPT_CONTRACT.getUnid()))
                    .findFirst()
                    .orElse(null);

            Document saleDealDraft = documents.stream()
                    .filter(document -> document.getTdUnid().getTdUnid().equals(TypeDocConstant.SALE_DEAL_DRAFT_PROPERTY.getUnid()))
                    .findFirst()
                    .orElse(null);

            List<Document> otherDocs = documents.stream()
                    .filter(document -> !document.getTdUnid().getTdUnid().equals(TypeDocConstant.RECEIPT_CONTRACT.getUnid())
                            && !document.getTdUnid().getTdUnid().equals(TypeDocConstant.SALE_DEAL_DRAFT_PROPERTY.getUnid()))
                    .collect(Collectors.toList());

            ObjCost minCost = lotController.getMinObjCost(lot.getLotUnid());
            Long caCode = null;
            if (lot.getCaUnid() != null) {
                ClAsv clAsv = lotController.getClAsvByUnid(lot.getCaUnid());
                caCode = clAsv.getCaCode();
            }

            ParticipantAgent paSaleManager = null;
            if (object.getPaManagerUnid() != null) {
                paSaleManager = participantAgentController.getParticipantAgent(object.getPaManagerUnid());
            }

            Address address = lotController.getAddress(lot.getObjUnid());

            BkrLotParams params = new BkrLotParams(auction, lot, object, saleCategory.getScCode(), startCost, srfUnid,
                    reductionSchedules, pictures, lotDepositDocument, saleDealDraft, pathToFiles,
                    minCost, caCode, logoFilePath, otherDocs, paSaleManager, address);
            return Result.ok(params);
        } catch (Exception ex) {
            logger.error("Error buildParams for bkr exchange by lotUnid = {}", lotUnid, ex);
        }
        return Result.error("Ошибка при формировании параметров запуска процедуры обмена.");
    }
}
