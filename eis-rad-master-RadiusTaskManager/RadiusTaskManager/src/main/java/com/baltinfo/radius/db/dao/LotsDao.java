package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.Lots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class LotsDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(LotsDao.class);

    public List<Lots> getLotsListByAuctionId(EntityManager em, Long auctionId) {
        return em.createQuery(
                        "select l "
                                + "from Lots l "
                                + "where l.auctionId = :auctionId")
                .setParameter("auctionId", auctionId.intValue())
                .getResultList();
    }
}
