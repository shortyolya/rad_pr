package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.VAvitoAuto;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 27.11.2020
 */

public class VAvitoAutoController extends AbstractController implements AvitoController<VAvitoAuto>{

    private static final Logger logger = LoggerFactory.getLogger(VAvitoAutoController.class);

    @Override
    public List<VAvitoAuto> getObjectsByCategoryFeedName(String categoryFeedCode, Long mevUnid, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select v from VAvitoAuto v where v.categoryFeedCode = :categoryFeedCode " +
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

    @Override
    public List<Long> getObjUnidListByFcUnid(Long fcUnid) {
        return new ArrayList<>();
    }
}
