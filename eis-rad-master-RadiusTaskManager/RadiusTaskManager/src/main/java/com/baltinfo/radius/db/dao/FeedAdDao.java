package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.FadStatus;
import com.baltinfo.radius.db.model.FeedAd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

public class FeedAdDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(FeedAdDao.class);

    public FeedAd createGoodAd(EntityManager em, String xml, String error, Long mevUnid, Long fcUnid, Long fcgUnid, Long subUnid) {
        Date now = new Date();
        FeedAd feedAd = new FeedAd();
        feedAd.setIndActual(1);
        feedAd.setMevUnid(mevUnid);
        feedAd.setFadXml(xml);
        feedAd.setFadNote(error);
        feedAd.setFadStatus(FadStatus.ACTIVE.getCode());
        feedAd.setFcUnid(fcUnid);
        feedAd.setFadDate(now);
        feedAd.setFcgUnid(fcgUnid);
        feedAd.setSubUnid(subUnid);
        setNewHisory(now, feedAd);
        em.persist(feedAd);
        return feedAd;
    }

    public FeedAd createBadAd(EntityManager em, String fatalError, Long mevUnid, Long fcUnid, Long fcgUnid, Long subUnid) {
        Date now = new Date();
        FeedAd feedAd = new FeedAd();
        feedAd.setIndActual(1);
        feedAd.setMevUnid(mevUnid);
        feedAd.setFadErrorText(fatalError);
        feedAd.setFadStatus(FadStatus.ERROR.getCode());
        feedAd.setFcUnid(fcUnid);
        feedAd.setFadDate(now);
        feedAd.setFcgUnid(fcgUnid);
        feedAd.setSubUnid(subUnid);
        setNewHisory(now, feedAd);
        em.persist(feedAd);
        return feedAd;
    }

    public void changeAdStatus(EntityManager em, Long fcUnid, Long mevUnid, Long fcgUnid, Long subUnid) {
        List<FeedAd> list = em.createNamedQuery("FeedAd.getTheLatestAd")
                .setParameter("fcUnid", fcUnid)
                .setParameter("mevUnid", mevUnid)
                .setParameter("fcgUnid", fcgUnid)
                .setParameter("subUnid", subUnid)
                .getResultList();
        for (FeedAd feedAd : list) {
            feedAd.setFadStatus(FadStatus.ARCHIVE.getCode());
            setRecHisory(new Date(), feedAd);
            em.merge(feedAd);
        }
    }

    public FeedAd createAd(EntityManager em, Integer fadStatus, Long mevUnid, Long subUnid) {
        Date now = new Date();
        FeedAd fad = new FeedAd();
        fad.setMevUnid(mevUnid);
        fad.setFadDate(now);
        fad.setFadStatus(fadStatus);
        fad.setSubUnid(subUnid);
        setNewHisory(new Date(), fad);
        em.persist(fad);
        return fad;
    }
}
