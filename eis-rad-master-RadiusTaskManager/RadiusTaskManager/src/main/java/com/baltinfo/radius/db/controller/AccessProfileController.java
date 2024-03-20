package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class AccessProfileController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AccessProfileController.class);

    public boolean checkPaHasAccessProfile(Long paUnid, Long apUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VPaProfile.checkAccessProfileByPa", Long.class)
                    .setParameter("paUnid", paUnid)
                    .setParameter("apUnid", apUnid)
                    .getSingleResult() > 0;
        } catch (Exception ex) {
            logger.error("Error checkPaHasAccessProfile by paUnid = {}, apUnid = {}.", paUnid, apUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    public List<ParticipantAgent> getParticipantsWithProfile(Long apUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select pa from VPaProfile p, ParticipantAgent pa where p.apUnid = :apUnid and p.paUnid = pa.paUnid and pa.indActual = 1", ParticipantAgent.class)
                    .setParameter("apUnid", apUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getParticipantsWithProfile by apUnid = {}", apUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
