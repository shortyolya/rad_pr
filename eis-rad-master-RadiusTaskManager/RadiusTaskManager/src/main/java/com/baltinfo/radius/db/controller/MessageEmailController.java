package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.MessageEmailDao;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 02.03.2020
 */

public class MessageEmailController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(MessageEmailController.class);

    private final MessageEmailDao messageEmailDao;

    public MessageEmailController(MessageEmailDao messageEmailDao) {
        this.messageEmailDao = messageEmailDao;
    }

    public Result<Void, String> addMessageToDb(List<MessageEmail> emails) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            for(MessageEmail email : emails) {
                messageEmailDao.createMessageEmail(em, email);
            }
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error addMessageToDb", ex);
            rollbackTransaction(em);
            return Result.error("Error add message to db. Error: " + ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }
}
