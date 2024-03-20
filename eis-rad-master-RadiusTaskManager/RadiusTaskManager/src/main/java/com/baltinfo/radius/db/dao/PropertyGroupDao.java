package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.PropertyGroup;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 16.11.2020
 */
public class PropertyGroupDao extends AbstractDao{

    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupDao.class);

    public List<PropertyGroup> getPropertyGroupsBySaleCategory(EntityManager em, Long scUnid) {
        return (List<PropertyGroup>) em.createQuery("SELECT a FROM PropertyGroup AS a " +
                "WHERE a.indActual = 1 " +
                "AND a.scUnid.scUnid = :scUnid " +
                "ORDER BY a.pgNum ASC")
                .setParameter("scUnid", scUnid)
                .getResultList();
    }

    public PropertyGroup getPropertyGroupByUnid(EntityManager em, Long pgUnid) {
        return em.find(PropertyGroup.class, pgUnid);
    }
}
