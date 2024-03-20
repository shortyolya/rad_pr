package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.model.Act;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 30.11.2020
 */
public class ActDao extends AbstractDao {

    public Act saveAct(EntityManager em, Act act) {
        if (act.getActUnid() == null) {
            Long paUnid = 1L;
            setNewHisory(new Date(), act);
            act.setEntityUnid(EntityConstant.ACT.getId());
            act.setPaUnid(paUnid);
            em.persist(act);
        } else {
            setRecHisory(new Date(), act);
            em.merge(act);
        }
        return act;
    }


    public String getNextActNum(EntityManager em) {
        Query q = em.createNativeQuery("select nextval('web.seq_act_num')");
        return q.getSingleResult() + "";
    }

    public void createAct(EntityManager em, Act act) {
        Long paUnid = 1L;
        Date now = new Date();act.setEntityUnid(EntityConstant.ACT.getId());
        act.setDateB(now);
        act.setPersCodeB(paUnid);
        act.setPaUnid(paUnid);
        act.setIndActual(1);
        em.persist(act);
    }

}
