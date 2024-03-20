package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.MessageEmail;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class MessageEmailDao extends AbstractDao {

    public MessageEmail createMessageEmail(EntityManager em, MessageEmail messageEmail) {
        setNewHisory(new Date(), messageEmail);
        em.persist(messageEmail);
        return messageEmail;
    }
}
