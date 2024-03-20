package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.dao.LotDao;
import com.baltinfo.radius.db.model.LoadLot;
import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.slowTest.configuration.DadataClientConfiguration;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 24.10.2018
 */
public class LotControllerTest {
    @Ignore
    @Test
    public void calculatePrice() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
        DadataService dadataService = new DadataService(dadataClient);
//        LotController lotController = new LotController(new LotDao(), new AuctionDao(), new ObjectController(new ObjectJpaDao()), dadataService);
//        BigDecimal price = lotController.calculatePrice(CalcPriceConstant.PREV_PRICE_PERCENT, new BigDecimal("10000"), new BigDecimal("10000"), new BigDecimal("10"));
 //       System.out.println("price = " + price);
    }

//    @Test
//    public void test() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DadataClientConfiguration.class);
//        DadataClient dadataClient = (DadataClient) context.getBean("dadataClient");
//        DadataService dadataService = new DadataService(dadataClient);
//        LotController lotController = new LotController(new LotDao(), new AuctionDao(), dadataService);
//        LoadAuctionController loadAuctionController = new LoadAuctionController(new AuctionDao(), new LoadAuctionDao());
//        List<LoadLot> loadLotList = loadAuctionController.getLoadLotForTransfer(27L);
//        List<LoadRs> loadRsList = loadAuctionController.getLoadRsForTransfer(27L);
//        for (LoadLot loadLot : loadLotList) {
//            Optional<ChangePriceAlg> changePriceAlgOptional = ChangePriceAlg.getByCode(loadLot.getLlChangePriceAlg());
//            if (changePriceAlgOptional.isPresent()) {
//                ChangePriceAlg changePriceAlg = changePriceAlgOptional.get();
//                Date dateB = null;
//                Date dateE = null;
//                BigDecimal price = null;
//                BigDecimal prePrice = null;
//                for (LoadRs loadRs : loadRsList) {
//                    if (changePriceAlg.equals(ChangePriceAlg.ALG5)) {
//                        price = lotController.calculatePrice(CalcPriceConstant.getByValue(loadRs.getLrsCalcPriceAlg()),
//                                loadLot.getLlStartCost(),
//                                prePrice == null ? loadLot.getLlStartCost() : prePrice,
//                                loadRs.getLrsSumm());
//                        if (prePrice == null) {
//                            prePrice = price;
//                        }
//                        BigDecimal depositSum = lotController.calcDepositSum(DepositTypeConstant.getByName(loadLot.getLlDepositAlg())
//                                        .orElse(null),
//                                loadLot.getLlStartCost(),
//                                price, loadRs.getLrsDepositSumm()
//                        );
//                        prePrice = price;
//                        System.out.println("price = " + price);
//                        System.out.println("depositSum = " + depositSum);
//                        System.out.println("prePrice = " + prePrice);
//                    }
//                }
//
//            }
//        }
//    }

//    @Test
    public void JsonExportToAssetsServiceTest(){
        JsonExportToAssetsService jsonExportToAssetsService = new JsonExportToAssetsService();
        EntityManager em = null;
        em = HibernateUtil.getInstance().getEntityManager();
        em.getTransaction().begin();
        LoadLot loadLot = (LoadLot) em.createNamedQuery("LoadLot.findByLlUnid")
                .setParameter("llUnid", 128920L)
                .getSingleResult();
        Result result = jsonExportToAssetsService.buildHtmlFromAsset(loadLot.getLlAssets());
        LotDao lotDao = new LotDao();
        lotDao.createObjDescr(em, (String) result.getResult(), null, 128605L, 29L);
        em.getTransaction().commit();
    }
}
