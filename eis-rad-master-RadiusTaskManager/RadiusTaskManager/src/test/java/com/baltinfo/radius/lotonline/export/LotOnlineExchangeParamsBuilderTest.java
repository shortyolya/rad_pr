package com.baltinfo.radius.lotonline.export;

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
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.utils.EisFileService;
import com.baltinfo.radius.utils.Result;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Suvorina Aleksandra
 * @since 23.03.2021
 */
@RunWith(MockitoJUnitRunner.class)
public class LotOnlineExchangeParamsBuilderTest {


    @Mock
    private LotController lotController;
    @Mock
    private ParticipantAgentController participantAgentController;
    @Mock
    private EisFileService eisFileService;

    @Test
    @Ignore
    public void buildParamsAllValues() throws Exception {

        Lot lot = new Lot();
        Auction auction = new Auction();
        ObjectJPA object = new ObjectJPA();
        object.setObjUnid(1L);
        object.setPaManagerUnid(1L);
        lot.setObjUnid(object);
        ObjCost startCost = new ObjCost();
        ObjCost minCost = new ObjCost();
        Address address = new Address();
        List<Picture> pictures = new ArrayList<>();
        Picture emptyPicture = new Picture();
        pictures.add(emptyPicture);
        pictures.add(emptyPicture);
        Picture picture = new Picture();
        DocFile docFile = new DocFile();
        picture.setDfUnid(docFile);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        pictures.add(picture);
        List<Document> documents = new ArrayList<>();
        Document document = new Document();
        document.setDfUnid(docFile);
        Document emptyDoc = new Document();
        documents.add(emptyDoc);
        documents.add(emptyDoc);
        documents.add(document);
        documents.add(document);
        documents.add(document);
        documents.add(document);
        documents.add(document);
        documents.add(document);

        ObjSaleCategory objSaleCategory = new ObjSaleCategory();
        SaleCategory saleCategory = new SaleCategory();
        saleCategory.setScCode("101001");
        objSaleCategory.setScUnid(saleCategory);

        ParticipantAgent paSaleManager = new ParticipantAgent();
        Subject saleManager = new Subject();
        paSaleManager.setSubSubUnid(saleManager);

        when(lotController.getLot(anyLong())).thenReturn(lot);
        when(lotController.getAuction(anyLong())).thenReturn(auction);
        when(lotController.getStartObjCost(anyLong())).thenReturn(startCost);
        when(lotController.getAddress(anyObject())).thenReturn(address);
        when(lotController.getPicturesForExportToEtp(anyLong())).thenReturn(pictures);
        when(lotController.getAllDocumentsForExportToEtp(anyLong(), anyLong(), anyLong())).thenReturn(documents);
        when(lotController.getMinObjCost(anyLong())).thenReturn(minCost);
        when(lotController.getObjSaleCategoryByObjUnid(anyLong())).thenReturn(objSaleCategory);
        when(eisFileService.getFileData(anyString())).thenReturn(Result.ok(new byte[0]));

        when(participantAgentController.getParticipantAgent(anyLong())).thenReturn(paSaleManager);

        Integer maxPictureCount = 9;

        LotOnlineExchangeParamsBuilder lotOnlineExchangeParamsBuilder = new LotOnlineExchangeParamsBuilder(lotController,
                maxPictureCount, participantAgentController, eisFileService);

        Result<LotOnlineLotParams, String> result = lotOnlineExchangeParamsBuilder.buildParams(1L);

        Assert.assertFalse(result.isError());
        LotOnlineLotParams params = result.getResult();
        Assert.assertEquals(params.getPictures().size(), maxPictureCount.intValue());
        Assert.assertEquals(params.getDocuments().size(), 6);
        Assert.assertEquals(params.getLot(), lot);
        Assert.assertEquals(params.getAddress(), address);
        Assert.assertEquals(params.getMinCost(), minCost);
        Assert.assertEquals(params.getSaleCategory(), new Long(saleCategory.getScCode()));
        Assert.assertEquals(params.getStartCost(), startCost);
        Assert.assertEquals(params.getSaleManager(), saleManager);
    }

    @Test
    @Ignore
    public void buildParamsWithoutPaManager() throws Exception {

        Lot lot = new Lot();
        Auction auction = new Auction();
        ObjectJPA object = new ObjectJPA();
        object.setObjUnid(1L);
        object.setPaManagerUnid(null);
        lot.setObjUnid(object);

        ObjCost startCost = new ObjCost();
        ObjCost minCost = new ObjCost();
        Address address = new Address();
        List<Picture> pictures = new ArrayList<>();
        List<Document> documents = new ArrayList<>();

        ObjSaleCategory objSaleCategory = new ObjSaleCategory();
        SaleCategory saleCategory = new SaleCategory();
        saleCategory.setScCode("101001");
        objSaleCategory.setScUnid(saleCategory);

        when(lotController.getLot(anyLong())).thenReturn(lot);
        when(lotController.getAuction(anyLong())).thenReturn(auction);
        when(lotController.getStartObjCost(anyLong())).thenReturn(startCost);
        when(lotController.getAddress(anyObject())).thenReturn(address);
        when(lotController.getPicturesForExportToEtp(anyLong())).thenReturn(pictures);
        when(lotController.getAllDocumentsForExportToEtp(anyLong(), anyLong(), anyLong())).thenReturn(documents);
        when(lotController.getMinObjCost(anyLong())).thenReturn(minCost);
        when(lotController.getObjSaleCategoryByObjUnid(anyLong())).thenReturn(objSaleCategory);
        when(eisFileService.getFileData(anyString())).thenReturn(Result.ok(new byte[0]));

        Integer maxPictureCount = 9;

        LotOnlineExchangeParamsBuilder lotOnlineExchangeParamsBuilder = new LotOnlineExchangeParamsBuilder(lotController,
                maxPictureCount, participantAgentController, eisFileService);

        Result<LotOnlineLotParams, String> result = lotOnlineExchangeParamsBuilder.buildParams(1L);

        Assert.assertFalse(result.isError());
        LotOnlineLotParams params = result.getResult();
        Assert.assertEquals(params.getSaleManager(), null);
    }
}
