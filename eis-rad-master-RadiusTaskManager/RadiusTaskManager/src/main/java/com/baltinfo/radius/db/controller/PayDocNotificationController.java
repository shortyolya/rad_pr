package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.ParticipantAgentConstant;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.model.LotonlineProfile;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.TypeDoc;
import com.baltinfo.radius.db.model.dep.PayDocNotice;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayDocNotificationController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(PayDocNotificationController.class);

    public List<PayDocNotice> getNotSentPayDocNotice() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerDep();
            return em.createQuery("SELECT pdn FROM PayDocNotice pdn WHERE pdn.indActual = 1 AND pdn.pdnStatus = 0", PayDocNotice.class)
                    .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.PayDocNotice.payDoc"))
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error getNotSentPayDocNotice", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }
    public boolean savePayDocNotice(PayDocNotice payDocNotice) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerDep();
            em.getTransaction().begin();
            payDocNotice.setDateBRec(new Date());
            payDocNotice.setPersCodeBRec(ParticipantAgentConstant.SYSTEM.getPaUnid());
            em.merge(payDocNotice);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error("Error savePayDocNotice", e);
            rollbackTransaction(em);
            return false;
        } finally {
            closeEntityManager(em);
        }
    }
}
