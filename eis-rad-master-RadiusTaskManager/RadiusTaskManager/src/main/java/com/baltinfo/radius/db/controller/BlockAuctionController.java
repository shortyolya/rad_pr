package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.BlockAuctionDao;
import com.baltinfo.radius.db.dto.BlockAuctionDto;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.VBaResults;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     Контроллер для создания и обноления блочных торгов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.01.2020
 */
public class BlockAuctionController extends AbstractController{

    private static Logger logger = LoggerFactory.getLogger(BlockAuctionController.class);
    private final BlockAuctionDao blockAuctionDao;

    public BlockAuctionController(BlockAuctionDao blockAuctionDao) {
        this.blockAuctionDao = Objects.requireNonNull(blockAuctionDao, "Can't get blockAuctionDao");
    }

    public List<Lot> getBlockAuctionLotsByBaUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select l from Lot l where l.indActual = 1 and l.auctionUnid in " +
                    "(select a.auctionUnid from Auction a where a.baUnid = :baUnid and a.indActual = 1)")
                    .setParameter("baUnid", baUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get block auction lots by unid = {}", baUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
    public BlockAuction getBlockAuctionByUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return blockAuctionDao.getBlockAuctionByUnid(em, baUnid);
        } catch (Exception ex) {
            logger.error("Can't get block auction by unid = {}", baUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public List<VBaResults> getAsvBlockAuctionsWithResults() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VBaResults.findAll", VBaResults.class)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't getAsvBlockAuctionsWithResults", ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
