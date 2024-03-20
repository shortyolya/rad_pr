package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.BlockAuctionController;
import com.baltinfo.radius.db.controller.DealController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ObjectController;
import com.baltinfo.radius.db.dao.AuctionDao;
import com.baltinfo.radius.db.dao.BlockAuctionDao;
import com.baltinfo.radius.db.dao.LotDao;
import com.baltinfo.radius.db.dao.ObjectJpaDao;
import org.junit.Test;

public class SendResultsToFtpServiceTest {

//    @Test
////    @Ignore
//    public void test() {
//        BlockAuctionDao blockAuctionDao = new BlockAuctionDao();
//        BlockAuctionController blockAuctionController = new BlockAuctionController(blockAuctionDao);
//        LotController lotController = new LotController(new LotDao(), new AuctionDao(), new ObjectController(new ObjectJpaDao()), new DadataService(null), new JsonExportToAssetsService(), new DealController());
//        AuctionController auctionController = new AuctionController(lotController, new AuctionDao());
//
//        ExchangeProcController exchangeProcController = new ExchangeProcController(lotController, new AuctionDao());
//        SendResultsToFtpService sendResultsToFtpService = new SendResultsToFtpService(blockAuctionController,
//                auctionController, lotController, exchangeProcController, null);
//
//        sendResultsToFtpService.sendResult();
//    }

}