package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.LotOnlineTenderStatus;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 07.08.2020
 */

public class ExportCodesFromLOController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromLOController.class);

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

    public List<LotInfo> getLOLotsByTenderId(Long id) {
        EntityManager emLO = null;
        try {
            emLO = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return (List<LotInfo>) emLO.createQuery("SELECT l FROM LotInfo l " +
                    "WHERE l.tenderFk.id = :id and l.tenderStatus <> :statusDeleted")
                    .setParameter("id", id)
                    .setParameter("statusDeleted", LotOnlineTenderStatus.DELETED.toString())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to getLOLotsByTenderId. id = {}", id, ex);
            return null;
        } finally {
            closeEntityManager(emLO);
        }
    }
}
