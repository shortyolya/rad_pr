package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.Rate;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author AAA
 * @since 13.01.2020
 */
public class RateController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(RateController.class);

    public RateController() {
    }

    public Result<BigDecimal, String> calcObjCostValueRub(EntityManager em, Date date, BigDecimal endCostValue, Long curUnid) {
        try {
            List<Rate> resultList = em.createQuery("select r from Rate r where r.indActual = 1 and r.curUnid =:curUnid " +
                            "and r.rateDate = :date order by r.rateDate desc")
                    .setParameter("curUnid", curUnid)
                    .setParameter("date", date)
                    .setMaxResults(1)
                    .getResultList();
            if (resultList.isEmpty()) throw new Exception("Не найден курс валюты на дату" + date.toString());
            return Result.ok(endCostValue.multiply(resultList.get(0).getRateValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (Exception ex) {
            logger.error("Error calcObjCostValueRub", ex);
            return Result.error(ex.getMessage());
        }
    }
}
