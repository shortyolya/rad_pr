package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ClDateController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ClDateController.class);

    public Date getDatePlusWorkDays(Date initDate, int workDays) {

        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<Date> dates = em.createQuery("select d.dateDate from ClDate d where d.dateDate > :initDate and (d.dateIndHoliday is null or d.dateIndHoliday = 0) order by d.dateDate", Date.class)
                    .setParameter("initDate", initDate).setMaxResults(workDays)
                    .getResultList();
            return dates.get(dates.size() - 1);

        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

}
