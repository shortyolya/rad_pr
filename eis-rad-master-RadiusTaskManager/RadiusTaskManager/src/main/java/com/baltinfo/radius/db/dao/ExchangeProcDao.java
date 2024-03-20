package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.ExchangeProc;

import javax.persistence.EntityManager;

/**
 * @author Suvorina Aleksandra
 * @since 27.08.2018
 */
public class ExchangeProcDao extends AbstractDao {

    public ExchangeProc getExchangeProc(EntityManager em, Long epUnid) {
        return em.find(ExchangeProc.class, epUnid);
    }

}
