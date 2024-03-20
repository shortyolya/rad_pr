package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Andrei Shymko
 * @since 19.11.2020
 */

public class UpdateDatesFromLOController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(UpdateDatesFromLOController.class);

    public Tender getTenderById(Long id) {
        EntityManager emLO = null;
        try {
            emLO = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return emLO.find(Tender.class, id);
        } catch (Exception ex) {
            logger.error("Error when trying to getTenderFromLO. Id = {}", id, ex);
            return null;
        } finally {
            closeEntityManager(emLO);
        }
    }

    public List<LotInfo> getLOLotsById(Long id) {
        EntityManager emLO = null;
        try {
            emLO = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return (List<LotInfo>) emLO.createQuery("SELECT l FROM LotInfo l " +
                    "WHERE l.tenderFk.id = :id")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to getTenderFromLO. Id = {}", id, ex);
            return null;
        } finally {
            closeEntityManager(emLO);
        }
    }
}
