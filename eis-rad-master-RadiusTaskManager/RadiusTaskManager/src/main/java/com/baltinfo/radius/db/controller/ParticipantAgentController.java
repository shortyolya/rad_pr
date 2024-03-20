package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * @author Suvorina Aleksandra
 * @since 10.02.2020
 */
public class ParticipantAgentController  extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipantAgentController.class);

    public ParticipantAgent getParticipantAgent(Long paUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(ParticipantAgent.class, paUnid);
        } catch (Exception ex) {
            logger.error("Error getParticipantAgent by paUnid = {}", paUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

}
