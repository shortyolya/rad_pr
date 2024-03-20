package com.baltinfo.radius.db.controller;


import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 30.01.2020
 */
public class VCianRealEstateController extends AbstractController implements CianController<VCianRealEstate> {
    private static final Logger logger = LoggerFactory.getLogger(VCianRealEstateController.class);

    public List<VCianRealEstate> getObjectsByCategoryFeedNameAndRent(String categoryFeedCode, Boolean isRent, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select v from VCianRealEstate v " +
                            "where v.categoryFeedName = :categoryFeedName " +
                            "  and v.objIndRent = :isRent " +
                            "  and v.orgSubUnid = :subUnid")
                    .setParameter("categoryFeedName", categoryFeedCode)
                    .setParameter("isRent", isRent)
                    .setParameter("subUnid", subUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error in getObjectsByCategoryFeedName. CategoryFeedCode = {}, isRent = {}", categoryFeedCode, isRent, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }
}
