package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.constants.NonExecReasons;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionUnid;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.VAsvApplication;
import com.baltinfo.radius.db.model.VAsvAuction;
import com.baltinfo.radius.db.model.VAsvLot;
import com.baltinfo.radius.db.model.VBaResults;
import com.baltinfo.radius.loadauction.constants.AsvTypeAuction;
import com.baltinfo.radius.loadauction.model.result.Applicant;
import com.baltinfo.radius.loadauction.model.result.AsvApplicantDto;
import com.baltinfo.radius.loadauction.model.result.AsvApplicantLegalStatus;
import com.baltinfo.radius.loadauction.model.result.AsvLotResult;
import com.baltinfo.radius.loadauction.model.result.AsvParticipantType;
import com.baltinfo.radius.loadauction.model.result.AsvTorgResult;
import com.baltinfo.radius.loadauction.model.result.Bank;
import com.baltinfo.radius.loadauction.model.result.Lot;
import com.baltinfo.radius.loadauction.model.result.Tender;
import com.baltinfo.radius.loadauction.model.result.TenderResults;
import com.baltinfo.radius.loadauction.model.result.Tenders;
import com.baltinfo.radius.utils.Result;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AsvResultServiceTest {

    @Test
    public void testFormBank() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");
        blockAuction.setDebtorName("Должник");
        blockAuction.setDebtorOgrn("1023500000028");
        blockAuction.setDebtorInn("3525018003");

        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, new ArrayList<>());

        Assert.assertTrue(result.isSuccess());
        Bank bank = result.getResult().getTenderResult().getBank();
        Assert.assertEquals(blockAuction.getDebtorInn(), bank.getBankInn());
        Assert.assertEquals(blockAuction.getDebtorOgrn(), bank.getBankOgrn());
        Assert.assertEquals(blockAuction.getDebtorName(), bank.getBankName());
    }

    @Test
    public void testFormBlockTenders() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, new ArrayList<>());

        Assert.assertTrue(result.isSuccess());
        Tenders tenders = result.getResult().getTenderResult().getTenders();
        Assert.assertEquals(blockAuction.getBaAsvId(), tenders.getTradeId());
        Assert.assertEquals("АО \"РАД\"", tenders.getEtp());
        Assert.assertEquals("АО \"РАД\"", tenders.getOrgTender());
    }

    @Test
    public void testFormNonBlockTenders() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("1234");

        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, new ArrayList<>());

        Assert.assertTrue(result.isSuccess());
        Tenders tenders = result.getResult().getTenderResult().getTenders();
        Assert.assertEquals("", tenders.getTradeId());
        Assert.assertEquals("АО \"РАД\"", tenders.getEtp());
        Assert.assertEquals("АО \"РАД\"", tenders.getOrgTender());
    }

    @Test
    public void testFormTender_auctionTypeTender() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        Tender tender = asvResultService.formTender(firstAuction, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 1);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender auction = tensers.get(0);

        Assert.assertEquals(AsvTypeAuction.AUCTION.getCode(), auction.getTypeTender());
        Assert.assertEquals("4321", auction.getTradeId());
    }



    @Test
    public void testFormTender_competitionTypeTender() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction secondCompetition = new VAsvAuction();
        secondCompetition.setBaUnid(123L);
        secondCompetition.setAuctionAsvId("5432/2");
        secondCompetition.setTypeAuctionCode(TypeAuctionCode.COMPETITION.getCode());
        secondCompetition.setTypeAuctionUnid(TypeAuctionUnid.CONTEST_ENGLISH);
        secondCompetition.setAuctionStageNum(2);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Tender tender = asvResultService.formTender(secondCompetition, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 1);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender competition = tensers.get(0);

        Assert.assertEquals(AsvTypeAuction.COMPETITION.getCode(), competition.getTypeTender());
        Assert.assertEquals("5432", competition.getTradeId());
    }

    @Test
    public void testFormTender_publicTypeTender() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction publicAuction = new VAsvAuction();
        publicAuction.setBaUnid(123L);
        publicAuction.setAuctionAsvId("6543");
        publicAuction.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        publicAuction.setTypeAuctionUnid(TypeAuctionUnid.PUBLIC);
        publicAuction.setAuctionStageNum(3);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Tender tender = asvResultService.formTender(publicAuction, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 1);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender publ = tensers.get(0);

        Assert.assertEquals(AsvTypeAuction.PUBLIC.getCode(), publ.getTypeTender());
        Assert.assertEquals("6543", publ.getTradeId());
    }

    @Test
    public void testFormTender_doneTorgResult() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Tender tender = asvResultService.formTender(firstAuction, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 1);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender auction = tensers.get(0);

        Assert.assertEquals(AsvTorgResult.DONE.getAsvCode(), auction.getTorgResult());

    }

    @Test
    public void testFormTender_canceledTorgResult() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");


        VAsvAuction secondAuction = new VAsvAuction();
        secondAuction.setBaUnid(123L);
        secondAuction.setAuctionAsvId("4321/2");
        secondAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        secondAuction.setAuctionStageNum(2);
        secondAuction.setTsUnid(TypeStateConstant.TRADE_ASSIGNED.getId());

        VAsvAuction secondCompetition = new VAsvAuction();
        secondCompetition.setBaUnid(123L);
        secondCompetition.setAuctionAsvId("5432/2");
        secondCompetition.setTypeAuctionCode(TypeAuctionCode.COMPETITION.getCode());
        secondCompetition.setTypeAuctionUnid(TypeAuctionUnid.CONTEST_ENGLISH);
        secondCompetition.setAuctionStageNum(2);
        secondCompetition.setTsUnid(TypeStateConstant.TRADE_CANCELED.getId());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Tender tenderAuction = asvResultService.formTender(secondAuction, new ArrayList<>());
        Tender tenderCompetition = asvResultService.formTender(secondCompetition, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tenderAuction, tenderCompetition));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 2);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender auction = tensers.get(0);
        Tender competition = tensers.get(1);

        Assert.assertEquals(AsvTorgResult.NOT_DONE.getAsvCode(), auction.getTorgResult());
        Assert.assertEquals(AsvTorgResult.CANCELED.getAsvCode(), competition.getTorgResult());

    }
    @Test
    public void testFormTender_notDoneTorgResult() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction publicAuction = new VAsvAuction();
        publicAuction.setBaUnid(123L);
        publicAuction.setAuctionAsvId("6543");
        publicAuction.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        publicAuction.setTypeAuctionUnid(TypeAuctionUnid.PUBLIC);
        publicAuction.setAuctionStageNum(3);
        publicAuction.setTsUnid(TypeStateConstant.TRADE_ASSIGNED.getId());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Tender tender = asvResultService.formTender(publicAuction, new ArrayList<>());
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getResult().getTenderResult().getTenders().getTender().size(), 1);
        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();

        Tender publ = tensers.get(0);

        Assert.assertEquals(AsvTorgResult.NOT_DONE.getAsvCode(), publ.getTorgResult());

    }

    @Test
    public void testFormLot() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        eisLot.setLotEtpCode("РАД-123");
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        eisLot.setTsUnid(TypeStateConstant.NOT_TAKE_PLACE.getId());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Lot lot = asvResultService.formAsvLot(eisLot, new ArrayList<>());
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tensers.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertEquals(new Long(eisLot.getLotAsvId()), lotResult.getId());
        Assert.assertEquals(eisLot.getLotEtpCode(), lotResult.getRadNumber());
        Assert.assertEquals(new Long(eisLot.getLotAsvId()), lotResult.getInfoLot().get(0).getIdLot());
        Assert.assertEquals(AsvLotResult.RUNNING.getCode(), lotResult.getLotResult());

    }

    @Test
    public void testFormLot_lotResult_running() {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        eisLot.setTsUnid(TypeStateConstant.NOT_TAKE_PLACE.getId());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Lot lot = asvResultService.formAsvLot(eisLot, new ArrayList<>());
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tensers = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tensers.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getSuccessDate());
        Assert.assertNull(lotResult.getFaultDate());
        Assert.assertNull(lotResult.getDateReport());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.RUNNING.getCode(), lotResult.getLotResult());

    }

    @Test
    public void testFormLot_lotResult_canceled() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_CANCEL.getId());
        eisLot.setLotAuctionCancelCause("Отмена торгов");
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Lot lot = asvResultService.formAsvLot(eisLot, new ArrayList<>());
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getFaultDate());
        Assert.assertNull(lotResult.getDateReport());
        Assert.assertEquals(AsvLotResult.CANCELED.getCode(), lotResult.getLotResult());
        Assert.assertEquals(eisLot.getLotAuctionCancelCause(), lotResult.getLotCancelReason());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getSuccessDate());
    }

    @Test
    public void testFormLot_lotResult_notDone() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_NOT_DONE.getId());
        eisLot.setNonExecReasonUnid(NonExecReasons.NO_APPLICATIONS.getUnid());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());


        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);
        Lot lot = asvResultService.formAsvLot(eisLot, new ArrayList<>());
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getSuccessDate());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.DIDNT_TAKE_PLACE.getCode(), lotResult.getLotResult());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getFaultDate());
        Assert.assertNull(lotResult.getDateReport());
    }

    @Test
    public void testFormLot_lotResult_oneApplication() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_NOT_DONE.getId());
        eisLot.setNonExecReasonUnid(NonExecReasons.ONE_APPLICATION.getUnid());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());

        VAsvApplication firstApplication = new VAsvApplication();
        firstApplication.setTypesUnid(1L);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(firstApplication), eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getSuccessDate());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.SINGLE_PARTICIPANT.getCode(), lotResult.getLotResult());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getFaultDate());
        Assert.assertEquals(sdf.format(eisLot.getProtocolApplDate()), lotResult.getDateReport());
    }

    @Test
    public void testFormLot_lotResult_done_but_oneApplication() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());;


        VAsvApplication application = new VAsvApplication();
        application.setTypesUnid(1L);
        when(lotController.getAsvLotApplications(123L)).thenReturn(Arrays.asList(application));

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(application), eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getSuccessDate());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.SINGLE_PARTICIPANT.getCode(), lotResult.getLotResult());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getFaultDate());
        Assert.assertEquals(sdf.format(eisLot.getProtocolApplDate()), lotResult.getDateReport());
    }

    @Test
    public void testFormLot_lotResult_done() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());

        VAsvApplication firstApplication = new VAsvApplication();
        firstApplication.setTypesUnid(1L);

        VAsvApplication secondApplication = new VAsvApplication();
        secondApplication.setTypesUnid(1L);
        when(lotController.getAsvLotApplications(123L)).thenReturn(Arrays.asList(firstApplication, secondApplication));

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(firstApplication, secondApplication),
                eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant(), applicants.get(1).getApplicant()));
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getFaultDate());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.TRADE_DONE.getCode(), lotResult.getLotResult());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getSuccessDate());
        Assert.assertEquals(sdf.format(eisLot.getProtocolApplDate()), lotResult.getDateReport());
    }

    @Test
    public void testFormLot_lotResult_done_public() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction publicAuction = new VAsvAuction();
        publicAuction.setAuctionUnid(123L);
        publicAuction.setBaUnid(123L);
        publicAuction.setAuctionAsvId("6543");
        publicAuction.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        publicAuction.setTypeAuctionUnid(TypeAuctionUnid.PUBLIC);
        publicAuction.setAuctionStageNum(3);
        publicAuction.setTsUnid(TypeStateConstant.TRADE_IN_PROCESS.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());


        VAsvApplication firstApplication = new VAsvApplication();
        firstApplication.setTypesUnid(1L);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(firstApplication),
                eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(publicAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Assert.assertNull(lotResult.getFaultDate());
        Assert.assertNull(lotResult.getLotCancelReason());
        Assert.assertEquals(AsvLotResult.TRADE_DONE.getCode(), lotResult.getLotResult());
        Assert.assertEquals(sdf.format(eisLot.getProtocolResultDate()), lotResult.getSuccessDate());
        Assert.assertEquals(sdf.format(eisLot.getProtocolApplDate()), lotResult.getDateReport());
    }

    @Test
    public void testFormApplication_single() throws ParseException {

        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        eisLot.setLotSumDeposit(new BigDecimal("150.00"));
        eisLot.setStartCostValueRub(new BigDecimal("1500.00"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());


        VAsvApplication application = buildApplicationFl();

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(application), eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Applicant applicant = lotResult.getApplicants().get(0);

        Assert.assertEquals(application.getSubUnid(), applicant.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.FL.getAsvCode(), applicant.getLegalStatus());
        Assert.assertEquals(application.getShSubName(), applicant.getName());
        Assert.assertEquals(application.getShSubInn(), applicant.getInn());
        Assert.assertEquals(application.getApplicatNumber(), applicant.getApplicationNumber());
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(application.getApplicatAppTime()), applicant.getApplicationDate());
        Assert.assertEquals(eisLot.getStartCostValueRub(), applicant.getOfferedPrice());
        Assert.assertEquals(eisLot.getLotSumDeposit(), applicant.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.SINGLE.getCode(), applicant.getParticipantType());

        Assert.assertNull(applicant.getKpp());
        Assert.assertNull(applicant.getOgrn());
        Assert.assertEquals(application.getShSubDocumSeries(), applicant.getDulSeries());
        Assert.assertEquals(application.getShSubDocumNumber(), applicant.getDulNumber());
        Assert.assertEquals(application.getShSubDocumIssueAgency(), applicant.getDulByIssued());
        Assert.assertEquals(sdf.format(application.getShSubDocumIssueDate()), applicant.getDulDateIssued());
        Assert.assertEquals(sdf.format(application.getShSubBirthday()), applicant.getDateBirth());
        Assert.assertNull(applicant.getLeaderFIO());
        Assert.assertEquals(application.getShSubEMail(), applicant.getEmail());
        Assert.assertEquals(application.getShSubPhone(), applicant.getPhone());
        Assert.assertEquals(application.getShSubAddrLegal(), applicant.getLocation());
        Assert.assertEquals(application.getShSubAccountCode(), applicant.getPayerAccNumber());
        Assert.assertEquals(application.getShSubBankName(), applicant.getPayerBankName());
        Assert.assertEquals(application.getShSubBankBic(), applicant.getPayerBIK());
        Assert.assertEquals(application.getShSubBankAccount(), applicant.getPayerCorrAccNumber());
    }

    @Test
    public void testFormApplication_winner_fl() throws ParseException {

        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction firstAuction = new VAsvAuction();
        firstAuction.setBaUnid(123L);
        firstAuction.setAuctionUnid(123L);
        firstAuction.setAuctionAsvId("4321/1");
        firstAuction.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());
        firstAuction.setTypeAuctionUnid(TypeAuctionUnid.AUCTION);
        firstAuction.setAuctionStageNum(1);
        firstAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        eisLot.setLotSumDeposit(new BigDecimal("150.00"));
        eisLot.setStartCostValueRub(new BigDecimal("1500.00"));
        eisLot.setEndCostValueRub(new BigDecimal("15000.00"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.AUCTION.getCode());

        VAsvApplication applicationWinner = buildApplicationFl();
        applicationWinner.setApplicatIndWinner(1);
        applicationWinner.setApplicatAmount(new BigDecimal("15000.00"));

        VAsvApplication applicationSecond = buildApplicationYl();
        applicationSecond.setApplicatAmount(new BigDecimal("14000.00"));

        VAsvApplication applicationParticipant = buildApplicationIp();
        applicationParticipant.setApplicatAmount(new BigDecimal("13000.00"));

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(applicationWinner, applicationSecond, applicationParticipant),
                eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant(),
                applicants.get(1).getApplicant(),
                applicants.get(2).getApplicant()));
        Tender tender = asvResultService.formTender(firstAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Applicant applicantWinner = lotResult.getApplicants().get(0);

        Assert.assertEquals(applicationWinner.getSubUnid(), applicantWinner.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.FL.getAsvCode(), applicantWinner.getLegalStatus());
        Assert.assertEquals(applicationWinner.getShSubName(), applicantWinner.getName());
        Assert.assertEquals(applicationWinner.getShSubInn(), applicantWinner.getInn());
        Assert.assertEquals(applicationWinner.getApplicatNumber(), applicantWinner.getApplicationNumber());
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(applicationWinner.getApplicatAppTime()), applicantWinner.getApplicationDate());
        Assert.assertEquals(applicationWinner.getApplicatAmount(), applicantWinner.getOfferedPrice());
        Assert.assertEquals(eisLot.getLotSumDeposit(), applicantWinner.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.WINNER.getCode(), applicantWinner.getParticipantType());

        Assert.assertNull(applicantWinner.getKpp());
        Assert.assertNull(applicantWinner.getOgrn());
        Assert.assertEquals(applicationWinner.getShSubDocumSeries(), applicantWinner.getDulSeries());
        Assert.assertEquals(applicationWinner.getShSubDocumNumber(), applicantWinner.getDulNumber());
        Assert.assertEquals(applicationWinner.getShSubDocumIssueAgency(), applicantWinner.getDulByIssued());
        Assert.assertEquals(sdf.format(applicationWinner.getShSubDocumIssueDate()), applicantWinner.getDulDateIssued());
        Assert.assertEquals(sdf.format(applicationWinner.getShSubBirthday()), applicantWinner.getDateBirth());
        Assert.assertNull(applicantWinner.getLeaderFIO());
        Assert.assertEquals(applicationWinner.getShSubEMail(), applicantWinner.getEmail());
        Assert.assertEquals(applicationWinner.getShSubPhone(), applicantWinner.getPhone());
        Assert.assertEquals(applicationWinner.getShSubAddrLegal(), applicantWinner.getLocation());
        Assert.assertEquals(applicationWinner.getShSubAccountCode(), applicantWinner.getPayerAccNumber());
        Assert.assertEquals(applicationWinner.getShSubBankName(), applicantWinner.getPayerBankName());
        Assert.assertEquals(applicationWinner.getShSubBankBic(), applicantWinner.getPayerBIK());
        Assert.assertEquals(applicationWinner.getShSubBankAccount(), applicantWinner.getPayerCorrAccNumber());

        Applicant applicantSecond = lotResult.getApplicants().get(1);
        Assert.assertEquals(applicationSecond.getSubUnid(), applicantSecond.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.YL.getAsvCode(), applicantSecond.getLegalStatus());
        Assert.assertEquals(applicationSecond.getShSubName(), applicantSecond.getName());
        Assert.assertEquals(applicationSecond.getShSubInn(), applicantSecond.getInn());
        Assert.assertEquals(applicationSecond.getApplicatNumber(), applicantSecond.getApplicationNumber());
        sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(applicationSecond.getApplicatAppTime()), applicantSecond.getApplicationDate());
        Assert.assertEquals(applicationSecond.getApplicatAmount(), applicantSecond.getOfferedPrice());
        Assert.assertEquals(eisLot.getLotSumDeposit(), applicantSecond.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.SECOND.getCode(), applicantSecond.getParticipantType());

        Assert.assertNull(applicantSecond.getKpp());
        Assert.assertNull(applicantSecond.getOgrn());
        Assert.assertNull(applicantSecond.getLocation());
        Assert.assertNull(applicantSecond.getSecondary());
        Assert.assertNull(applicantSecond.getSecondaryDulNumber());
        Assert.assertNull(applicantSecond.getSecondaryDulSeries());
        Assert.assertNull(applicantSecond.getSecondaryDulByIssued());
        Assert.assertNull(applicantSecond.getSecondaryDulDateIssued());
        Assert.assertNull(applicantSecond.getSecondaryDocType());
        Assert.assertNull(applicantSecond.getSecondaryDocNumber());
        Assert.assertNull(applicantSecond.getSecondaryDocDate());
        Assert.assertNull(applicantSecond.getLeaderFIO());
        Assert.assertNull(applicantSecond.getEmail());
        Assert.assertNull(applicantSecond.getPhone());
        Assert.assertNull(applicantSecond.getPayerAccNumber());
        Assert.assertNull(applicantSecond.getPayerBankName());
        Assert.assertNull(applicantSecond.getPayerBIK());
        Assert.assertNull(applicantSecond.getPayerCorrAccNumber());

        Applicant applicantParticipant = lotResult.getApplicants().get(2);
        Assert.assertEquals(applicationParticipant.getSubUnid(), applicantParticipant.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.IP.getAsvCode(), applicantParticipant.getLegalStatus());
        Assert.assertEquals(applicationParticipant.getShSubName(), applicantParticipant.getName());
        Assert.assertEquals(applicationParticipant.getShSubInn(), applicantParticipant.getInn());
        Assert.assertEquals(applicationParticipant.getApplicatNumber(), applicantParticipant.getApplicationNumber());
        sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(applicationParticipant.getApplicatAppTime()), applicantParticipant.getApplicationDate());
        Assert.assertEquals(applicationParticipant.getApplicatAmount(), applicantParticipant.getOfferedPrice());
        Assert.assertEquals(eisLot.getLotSumDeposit(), applicantParticipant.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.PARTICIPANT.getCode(), applicantParticipant.getParticipantType());

        Assert.assertNull(applicantParticipant.getOgrn());
        Assert.assertNull(applicantParticipant.getLocation());
        Assert.assertNull(applicantParticipant.getSecondary());
        Assert.assertNull(applicantParticipant.getSecondaryDulNumber());
        Assert.assertNull(applicantParticipant.getSecondaryDulSeries());
        Assert.assertNull(applicantParticipant.getSecondaryDulByIssued());
        Assert.assertNull(applicantParticipant.getSecondaryDulDateIssued());
        Assert.assertNull(applicantParticipant.getEmail());
        Assert.assertNull(applicantParticipant.getPhone());
        Assert.assertNull(applicantParticipant.getPayerAccNumber());
        Assert.assertNull(applicantParticipant.getPayerBankName());
        Assert.assertNull(applicantParticipant.getPayerBIK());
        Assert.assertNull(applicantParticipant.getPayerCorrAccNumber());
    }

    @Test
    public void testFormApplication_winner_yl() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction publicAuction = new VAsvAuction();
        publicAuction.setAuctionUnid(123L);
        publicAuction.setBaUnid(123L);
        publicAuction.setAuctionAsvId("6543");
        publicAuction.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        publicAuction.setTypeAuctionUnid(TypeAuctionUnid.PUBLIC);
        publicAuction.setAuctionStageNum(3);
        publicAuction.setTsUnid(TypeStateConstant.TRADE_IN_PROCESS.getId());

        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());

        VAsvApplication winnerAppl = buildApplicationYl();
        winnerAppl.setApplicatAmount(new BigDecimal("14000.00"));
        winnerAppl.setApplicatIndWinner(1);

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(winnerAppl),
                eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(publicAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Applicant applicantWinner = lotResult.getApplicants().get(0);
        Assert.assertEquals(winnerAppl.getSubUnid(), applicantWinner.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.YL.getAsvCode(), applicantWinner.getLegalStatus());
        Assert.assertEquals(winnerAppl.getShSubName(), applicantWinner.getName());
        Assert.assertEquals(winnerAppl.getShSubInn(), applicantWinner.getInn());
        Assert.assertEquals(winnerAppl.getApplicatNumber(), applicantWinner.getApplicationNumber());
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(winnerAppl.getApplicatAppTime()), applicantWinner.getApplicationDate());
        Assert.assertEquals(winnerAppl.getApplicatAmount(), applicantWinner.getOfferedPrice());
        Assert.assertEquals(eisLot.getLotSumDeposit(), applicantWinner.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.WINNER.getCode(), applicantWinner.getParticipantType());

        Assert.assertEquals(winnerAppl.getShSubCodeKpp(), applicantWinner.getKpp());
        Assert.assertEquals(winnerAppl.getShSubOgrn(), applicantWinner.getOgrn());
        Assert.assertEquals(winnerAppl.getShSubAddrLegal(), applicantWinner.getLocation());
        Assert.assertEquals(winnerAppl.getShPaSubNameF() + " " + winnerAppl.getShPaSubNameI() + " " + winnerAppl.getShPaSubNameO(), applicantWinner.getSecondary());
        Assert.assertEquals(winnerAppl.getShPaSubDocumNumber(), applicantWinner.getSecondaryDulNumber());
        Assert.assertEquals(winnerAppl.getShPaSubDocumSeries(), applicantWinner.getSecondaryDulSeries());
        Assert.assertEquals(winnerAppl.getShPaSubDocumIssueAgency(), applicantWinner.getSecondaryDulByIssued());
        Assert.assertEquals(sdf.format(winnerAppl.getShPaSubDocumIssueDate()), applicantWinner.getSecondaryDulDateIssued());
        Assert.assertEquals(winnerAppl.getShPaDocName(), applicantWinner.getSecondaryDocType());
        Assert.assertEquals(winnerAppl.getShPaDocNumber(), applicantWinner.getSecondaryDocNumber());
        Assert.assertEquals(sdf.format(winnerAppl.getShPaDocDate()), applicantWinner.getSecondaryDocDate());
        Assert.assertEquals(winnerAppl.getShSubManF() + " " + winnerAppl.getShSubManI() + " " + winnerAppl.getShSubManO(), applicantWinner.getLeaderFIO());
        Assert.assertEquals(winnerAppl.getShSubEMail(), applicantWinner.getEmail());
        Assert.assertEquals(winnerAppl.getShSubPhone(), applicantWinner.getPhone());
        Assert.assertEquals(winnerAppl.getShSubAccountCode(), applicantWinner.getPayerAccNumber());
        Assert.assertEquals(winnerAppl.getShSubBankName(), applicantWinner.getPayerBankName());
        Assert.assertEquals(winnerAppl.getShSubBankBic(), applicantWinner.getPayerBIK());
        Assert.assertEquals(winnerAppl.getShSubBankAccount(), applicantWinner.getPayerCorrAccNumber());
    }

    @Test
    public void testFormApplication_winner_ip() throws ParseException {
        AuctionController auctionController = mock(AuctionController.class);
        LotController lotController = mock(LotController.class);

        VBaResults blockAuction = new VBaResults();
        blockAuction.setBaUnid(123L);
        blockAuction.setBaAsvId("B123");

        VAsvAuction publicAuction = new VAsvAuction();
        publicAuction.setAuctionUnid(123L);
        publicAuction.setBaUnid(123L);
        publicAuction.setAuctionAsvId("6543");
        publicAuction.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        publicAuction.setTypeAuctionUnid(TypeAuctionUnid.PUBLIC);
        publicAuction.setAuctionStageNum(3);
        publicAuction.setTsUnid(TypeStateConstant.TRADE_IN_PROCESS.getId());


        VAsvLot eisLot = new VAsvLot();
        eisLot.setLotUnid(123L);
        eisLot.setLotNumber("1");
        eisLot.setLotAsvId("1234");
        eisLot.setLotAsvStageId("1234");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        eisLot.setProtocolResultDate(sdf.parse("01.08.2022"));
        eisLot.setProtocolApplDate(sdf.parse("31.07.2022"));
        eisLot.setTsUnid(TypeStateConstant.LOT_DONE.getId());
        eisLot.setTypeAuctionCode(TypeAuctionCode.PUBLIC_SALE.getCode());
        eisLot.setLotSumDeposit(new BigDecimal("150.00"));

        VAsvApplication winnerAppl = buildApplicationIp();
        winnerAppl.setApplicatAmount(new BigDecimal("14000.00"));
        winnerAppl.setApplicatIndWinner(1);
        winnerAppl.setRedSchedDepSum(new BigDecimal("50.00"));
        when(lotController.getAsvLotApplications(123L)).thenReturn(Arrays.asList(winnerAppl));

        AsvResultService asvResultService = new AsvResultService(auctionController, lotController);

        List<AsvApplicantDto> applicants = asvResultService.formApplicants(Arrays.asList(winnerAppl),
                eisLot);
        Lot lot = asvResultService.formAsvLot(eisLot, Arrays.asList(applicants.get(0).getApplicant()));
        Tender tender = asvResultService.formTender(publicAuction, Arrays.asList(lot));
        Result<TenderResults, String> result = asvResultService.formTenderResults(blockAuction, Arrays.asList(tender));

        Assert.assertTrue(result.isSuccess());

        List<Tender> tenders = result.getResult().getTenderResult().getTenders().getTender();
        Tender auction = tenders.get(0);

        Lot lotResult = auction.getLots().get(0);
        Applicant applicantWinner = lotResult.getApplicants().get(0);
        Assert.assertEquals(winnerAppl.getSubUnid(), applicantWinner.getRadId());
        Assert.assertEquals(AsvApplicantLegalStatus.IP.getAsvCode(), applicantWinner.getLegalStatus());
        Assert.assertEquals(winnerAppl.getShSubName(), applicantWinner.getName());
        Assert.assertEquals(winnerAppl.getShSubInn(), applicantWinner.getInn());
        Assert.assertEquals(winnerAppl.getApplicatNumber(), applicantWinner.getApplicationNumber());
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        Assert.assertEquals(sdfTime.format(winnerAppl.getApplicatAppTime()), applicantWinner.getApplicationDate());
        Assert.assertEquals(winnerAppl.getApplicatAmount(), applicantWinner.getOfferedPrice());
        Assert.assertEquals(winnerAppl.getRedSchedDepSum(), applicantWinner.getAccountedDeposit());
        Assert.assertEquals(AsvParticipantType.WINNER.getCode(), applicantWinner.getParticipantType());

        Assert.assertEquals(winnerAppl.getShSubOgrn(), applicantWinner.getOgrn());
        Assert.assertEquals(winnerAppl.getShSubAddrLegal(), applicantWinner.getLocation());
        Assert.assertEquals(winnerAppl.getShPaSubNameF() + " " + winnerAppl.getShPaSubNameI() + " " + winnerAppl.getShPaSubNameO(), applicantWinner.getSecondary());
        Assert.assertEquals(winnerAppl.getShPaSubDocumNumber(), applicantWinner.getSecondaryDulNumber());
        Assert.assertEquals(winnerAppl.getShPaSubDocumSeries(), applicantWinner.getSecondaryDulSeries());
        Assert.assertEquals(winnerAppl.getShPaSubDocumIssueAgency(), applicantWinner.getSecondaryDulByIssued());
        Assert.assertEquals(sdf.format(winnerAppl.getShPaSubDocumIssueDate()), applicantWinner.getSecondaryDulDateIssued());
        Assert.assertEquals(winnerAppl.getShSubEMail(), applicantWinner.getEmail());
        Assert.assertEquals(winnerAppl.getShSubPhone(), applicantWinner.getPhone());
        Assert.assertEquals(winnerAppl.getShSubAccountCode(), applicantWinner.getPayerAccNumber());
        Assert.assertEquals(winnerAppl.getShSubBankName(), applicantWinner.getPayerBankName());
        Assert.assertEquals(winnerAppl.getShSubBankBic(), applicantWinner.getPayerBIK());
        Assert.assertEquals(winnerAppl.getShSubBankAccount(), applicantWinner.getPayerCorrAccNumber());
    }

    private VAsvApplication buildApplicationFl() throws ParseException {
        VAsvApplication application = new VAsvApplication();
        application.setSubUnid(123L);
        application.setTypesUnid(1L);
        application.setApplicatUnid(1L);
        application.setShSubName("Иванов Иван Иванович");
        application.setShSubInn("323438345435");
        application.setShSubDocumSeries("1234");
        application.setShSubDocumNumber("123456");
        application.setShSubDocumIssueAgency("Отделом УФМС");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        application.setShSubDocumIssueDate(sdf.parse("01.01.2022"));
        application.setShSubBirthday(sdf.parse("01.01.2000"));
        application.setShSubEMail("test@test.test");
        application.setShSubPhone("111 111-11-11");
        application.setShSubAddrFact("Фактический адрес");
        application.setShSubAddrLegal("Юридический адрес");
        application.setShSubAccountCode("1234567890987654321");
        application.setShSubBankName("Банк");
        application.setShSubBankBic("123456789");
        application.setShSubBankAccount("987654321234567890");
        application.setApplicatNumber("ЭТП-243859-ИД");
        sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        application.setApplicatAppTime(sdf.parse("01.08.2022 10:01:33"));
        return application;
    }

    private VAsvApplication buildApplicationYl() throws ParseException {
        VAsvApplication application = new VAsvApplication();
        application.setSubUnid(123L);
        application.setApplicatUnid(2L);
        application.setTypesUnid(2L);
        application.setShSubName("ООО Участник");
        application.setShSubInn("555438345435");
        application.setShSubOgrn("2345678906");
        application.setShSubCodeKpp("555444333");
        application.setShSubEMail("testyl@test.test");
        application.setShSubPhone("222 111-11-11");
        application.setShSubAddrFact("Фактический адрес ЮЛ");
        application.setShSubAddrLegal("Юридический адрес ЮЛ");
        application.setShSubAccountCode("2224567890987654321");
        application.setShSubBankName("Банк");
        application.setShSubBankBic("222456789");
        application.setShSubBankAccount("222654321234567890");
        application.setApplicatNumber("ЭТП-243860-ИД");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        application.setApplicatAppTime(sdf.parse("01.08.2022 10:00:33"));

        application.setShSubManF("Петров");
        application.setShSubManI("Петр");
        application.setShSubManO("Петрович");

        application.setShPaSubNameF("Иванов");
        application.setShPaSubNameI("Иван");
        application.setShPaSubNameO("Иванович");
        application.setShPaSubDocumSeries("1234");
        application.setShPaSubDocumNumber("123456");
        application.setShPaSubDocumIssueAgency("Отделом УФМС");
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        application.setShPaSubDocumIssueDate(sdf.parse("01.01.2022"));
        application.setShPaDocName("Доверенность");
        application.setShPaDocNumber("123");
        application.setShPaDocDate(sdf.parse("02.01.2022"));

        return application;
    }

    private VAsvApplication buildApplicationIp() throws ParseException {
        VAsvApplication application = new VAsvApplication();
        application.setSubUnid(123L);
        application.setApplicatUnid(3L);
        application.setTypesUnid(3L);
        application.setShSubName("Индивидуальный предпирниматель Иванов Иван Иванович");
        application.setShSubInn("444438345435");
        application.setShSubOgrn("234567890644");
        application.setShSubEMail("testшз@test.test");
        application.setShSubPhone("333 111-11-11");
        application.setShSubAddrFact("Фактический адрес ИП");
        application.setShSubAddrLegal("Юридический адрес ИП");
        application.setShSubAccountCode("3334567890987654321");
        application.setShSubBankName("Банк");
        application.setShSubBankBic("333456789");
        application.setShSubBankAccount("333654321234567890");
        application.setApplicatNumber("ЭТП-243862-ИД");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        application.setApplicatAppTime(sdf.parse("01.08.2022 10:05:33"));

        application.setShPaSubNameF("Иванов");
        application.setShPaSubNameI("Иван");
        application.setShPaSubNameO("Иванович");
        application.setShPaSubDocumSeries("1234");
        application.setShPaSubDocumNumber("123456");
        application.setShPaSubDocumIssueAgency("Отделом УФМС");
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        application.setShPaSubDocumIssueDate(sdf.parse("01.01.2022"));
        return application;
    }


}