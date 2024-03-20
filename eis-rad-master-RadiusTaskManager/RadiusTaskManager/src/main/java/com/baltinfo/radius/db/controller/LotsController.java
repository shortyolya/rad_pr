package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.LotsDao;
import com.baltinfo.radius.db.model.Lots;
import com.baltinfo.radius.links.constant.RecipientSite;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LotsController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LotsController.class);

    private final LotsDao lotsDao;

    public LotsController(LotsDao lotsDao) {
        this.lotsDao = Objects.requireNonNull(lotsDao, "Can't get lots DAO");
    }

    public List<Lots> getLotsListByAuctionId(Long auctionId, RecipientSite site) {
        EntityManager emAH = null;
        try {
            if (site == RecipientSite.HOLDING) {
                emAH = HibernateUtil.getInstance().getEntityManagerRadHolding();
            } else {
                emAH = HibernateUtil.getInstance().getEntityManagerAH();
            }
            return lotsDao.getLotsListByAuctionId(emAH, auctionId);
        } catch (Exception e) {
            logger.error("Can't get list lots by auctionId = {} from site = {}", auctionId, site.getSiteUrl(), e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(emAH);
        }
    }
}
