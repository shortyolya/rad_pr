package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.ExplanationResponse;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 23.01.2022
 */
public class ExplanationResponseDao extends AbstractDao {

    public ExplanationResponse createExplanationResponse(EntityManager em, ExplanationResponse explanationResponse) {
        setNewHisory(new Date(), explanationResponse);
        em.persist(explanationResponse);
        return explanationResponse;
    }
}
