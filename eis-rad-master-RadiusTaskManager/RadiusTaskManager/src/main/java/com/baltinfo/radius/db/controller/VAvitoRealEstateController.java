package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class VAvitoRealEstateController extends AbstractController implements AvitoController<VAvitoRealEstate> {

    private static final Logger logger = LoggerFactory.getLogger(VAvitoRealEstateController.class);

    public List<VAvitoRealEstate> getObjectsByCategoryFeedName(String categoryFeedCode, Long mevUnid, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select v from VAvitoRealEstate v where v.categoryFeedCode = :categoryFeedCode " +
                    "and v.mevUnid = :mevUnid and v.orgSubUnid = :subUnid")
                    .setParameter("categoryFeedCode", categoryFeedCode)
                    .setParameter("mevUnid", mevUnid)
                    .setParameter("subUnid", subUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error in getObjectsByCategoryFeedName. CategoryFeedCode = {}, mevUnid = {}, subUnid = {}",
                    categoryFeedCode, mevUnid, subUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }

    public List<Long> getObjUnidListByFcUnid(Long fcUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VAvitoRealEstate.getObjUnidListByFcUnid")
                    .setParameter("fcUnid", fcUnid)
                    .getResultList();
        } catch (Throwable ex) {
            logger.error("Error when getting ObjUnid's by fcUnid = {}", fcUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }
}
