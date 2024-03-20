package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ParticipantAgentController;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.exchange.ExchangeParamsBuilder;
import com.baltinfo.radius.utils.EisFileService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 23.08.2019
 */
public class LotOnlineExchangeParamsBuilder implements ExchangeParamsBuilder<LotOnlineLotParams> {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineExchangeParamsBuilder.class);

    private final LotController lotController;
    private final Integer maxPictureCount;
    private final ParticipantAgentController participantAgentController;
    private final EisFileService eisFileService;

    public LotOnlineExchangeParamsBuilder(LotController lotController, Integer maxPictureCount,
                                          ParticipantAgentController participantAgentController, EisFileService eisFileService) {
        this.lotController = lotController;
        this.maxPictureCount = maxPictureCount;
        this.participantAgentController = participantAgentController;
        this.eisFileService = eisFileService;
    }

    @Override
    public Result<LotOnlineLotParams, String> buildParams(Long lotUnid) {
        try {
            Lot lot = lotController.getLot(lotUnid);
            ObjectJPA object = lot.getObjUnid();
            Auction auction = lotController.getAuction(lot.getAuctionUnid());
            ObjCost startCost = lotController.getStartObjCost(lot.getLotUnid());
            Address address = lotController.getAddress(object);
            List<Picture> pictures = lotController.getPicturesForExportToEtp(object.getObjUnid());
            List<LotOnlinePictureParams> pictureParams = initPictures(pictures);
            List<Document> documents = lotController.getAllDocumentsForExportToEtp(lot.getAuctionUnid(),
                    lot.getLotUnid(), object.getObjUnid());
            List<LotOnlineDocParams> docParams = initDocs(documents);
            ObjCost minCost = lotController.getMinObjCost(lot.getLotUnid());
            ObjSaleCategory objSaleCategory = lotController.getObjSaleCategoryByObjUnid(object.getObjUnid());
            ParticipantAgent paSaleManager = null;
            if (object.getPaManagerUnid() != null) {
                paSaleManager = participantAgentController.getParticipantAgent(object.getPaManagerUnid());
            }
            LotOnlineLotParams params = new LotOnlineLotParams(lot, object, startCost, address, minCost, auction,
                    objSaleCategory.getScUnid(), pictureParams, docParams, paSaleManager);
            return Result.ok(params);
        } catch (Exception ex) {
            logger.error("Error buildParams for lotonline exchange by lotUnid = {}", lotUnid, ex);
        }
        return Result.error("Ошибка при формировании параметров запуска процедуры обмена.");
    }

    private List<LotOnlinePictureParams> initPictures(List<Picture> pictures) {
        List<LotOnlinePictureParams> pictureParams = new ArrayList<>();
        pictures = pictures.stream()
                .filter(p -> p.getDfUnid() != null)
                .limit(maxPictureCount)
                .collect(Collectors.toList());
        for (Picture picture : pictures) {
            DocFile df = picture.getDfUnid();
            String name = "df" + df.getDfUnid();
            String eisPath = eisFileService.getPathToFiles() + File.separator + df.getDfFilePath();
            Result<byte[], String> result = eisFileService.getFileData(df.getDfFilePath());
            if (result.isError()) {
                continue;
            }
            pictureParams.add(new LotOnlinePictureParams(eisPath, name, df.getDfExt(), result.getResult()));
        }
        return pictureParams;
    }

    private List<LotOnlineDocParams> initDocs(List<Document> docs) {
        List<LotOnlineDocParams> params = new ArrayList<>();
        for (Document doc : docs) {
            DocFile df = doc.getDfScanUnid() == null
                    ? doc.getDfUnid()
                    : doc.getDfScanUnid();
            if (df == null) {
                continue;
            }
            Result<byte[], String> result = eisFileService.getFileData(df.getDfFilePath());
            if (result.isError()) {
                continue;
            }
            String documentName = (doc.getTdUnid() != null && !doc.getTdUnid().getTdUnid().equals(TypeDocConstant.NO_INFO.getUnid()))
                    ? doc.getTdUnid().getTdName()
                    : doc.getDocumName();

            LotOnlineDocParams param = new LotOnlineDocParams(df.getDfName(), documentName, df.getDfExt(), result.getResult());
            params.add(param);
        }
        return params;
    }


}

