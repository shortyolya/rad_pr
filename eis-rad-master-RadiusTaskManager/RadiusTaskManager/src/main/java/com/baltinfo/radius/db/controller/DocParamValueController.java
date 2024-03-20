package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.DocParamValue;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;


public class DocParamValueController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(DocParamValueController.class);

    public List<DocParamValue> getDpValueByDocUnid(Long documUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("DocParamValue.getByDocumUnid")
                    .setParameter("documUnid", documUnid)
                    .getResultList();
        } catch (Exception ex){
            logger.error("Error getDpValueByDocUnid by documUnid = {}", documUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
