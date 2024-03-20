package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.feed.cian.model.CianMarketType;
import com.baltinfo.radius.utils.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Lapenok
 * @since 24.03.2023
 */
public class CianFlatController extends AbstractController implements CianController<VCianRealEstate> {
    private static final Logger logger = LoggerFactory.getLogger(CianFlatController.class);

    public List<VCianRealEstate> getObjectsByCategoryFeedNameAndRent(String categoryFeedCode, Boolean isRent, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<VCianRealEstate> list = em.createQuery("select v from VCianRealEstate v " +
                            "where v.categoryFeedName = :categoryFeedName " +
                            "  and v.objIndRent = :isRent " +
                            "  and v.orgSubUnid = :subUnid ")
                    .setParameter("categoryFeedName", categoryFeedCode)
                    .setParameter("isRent", isRent)
                    .setParameter("subUnid", subUnid)
                    .getResultList();
            return list.stream()
                    .filter(v-> StringUtils.isEmpty(v.getShareAmount()) &&
                            (v.getMarketType() == null || !StringUtils.equalsIgnoreCase(v.getMarketType(), CianMarketType.NEW_BUILD.getCode())))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Error in getObjectsByCategoryFeedName. CategoryFeedCode = {}, isRent = {}", categoryFeedCode, isRent, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }
}