package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.ObjRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 22.03.2020
 */
public class ObjRoleDao extends AbstractDao {
    private static final Logger logger = LoggerFactory.getLogger(ObjRoleDao.class);

    public ObjRole getCreatorObjRole(EntityManager em, Long objUnid) {
        return (ObjRole) em.createNamedQuery("ObjRole.findCreatorObjRole")
                .setParameter("objUnid", objUnid)
                .getSingleResult();
    }

    public ObjRole getSaleObjRole(EntityManager em, Long objUnid) {
        return (ObjRole) em.createNamedQuery("ObjRole.findSaleObjRole")
                .setParameter("objUnid", objUnid)
                .getSingleResult();
    }

    public Optional<ObjRole> getSellerObjRole(EntityManager em, Long objUnid) {
        try {
            ObjRole objRole = (ObjRole) em.createNamedQuery("ObjRole.findSaleObjRole")
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
            return Optional.of(objRole);
        } catch (NoResultException ex) {
            logger.warn("Can't get seller obj role", ex);
            return Optional.empty();
        }
    }
}
