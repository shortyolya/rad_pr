package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dto.VitrinaAddressDto;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 17.09.2021
 */
public class VitrinaController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(VitrinaController.class);

    public Optional<VitrinaAddressDto> getAddressByBkrWbUnid(Long bkrWbUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerVitrina();
            Object[] address = (Object[]) em.createNativeQuery("select JSON_UNQUOTE(json_extract(legal_address_data,'$.data.fias_id')), " +
                    "       JSON_UNQUOTE(json_extract(post_address_data,'$.data.fias_id')) " +
                    " from cscart_rad_organization_addresses croa, cscart_rad_organizations cro " +
                    " where croa.organization_id = cro.organization_id " +
                    "  and cro.bkr_id = :wbUnid " +
                    "  and croa.status = 'A'")
                    .setParameter("wbUnid", bkrWbUnid)
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(new VitrinaAddressDto((String) address[0], (String) address[1]));

        } catch (Exception ex) {
            logger.error("Error getAddressByBkrWbUnid by bkrWbUnid = {}", bkrWbUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public Optional<VitrinaAddressDto> getAddressByBLoProfileId(Long profileId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerVitrina();
            Object[] address = (Object[]) em.createNativeQuery("select JSON_UNQUOTE(json_extract(legal_address_data,'$.data.fias_id')), " +
                    "       JSON_UNQUOTE(json_extract(post_address_data,'$.data.fias_id')) " +
                    " from cscart_rad_organization_addresses croa, cscart_rad_organizations cro " +
                    " where croa.organization_id = cro.organization_id " +
                    "  and cro.lot_id = :profileId " +
                    "  and croa.status = 'A'")
                    .setParameter("profileId", profileId)
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(new VitrinaAddressDto((String) address[0], (String) address[1]));

        } catch (Exception ex) {
            logger.error("Error getAddressByBLoProfileId by profileId = {}", profileId, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public Result<Long, String> getProductIdByLoLotId(Long loLotId) {
        if (loLotId == null) {
            return Result.error("Ошибка при получении идентификатора лота на витрине. loLotId = null");
        }
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerVitrina();
            Integer productId = (Integer) em.createNativeQuery("select p.product_id " +
                            "from cscart_products p " +
                            "where p.lo_lot_id = :loLotId" +
                            "  and p.status = 'A'")
                    .setParameter("loLotId", loLotId)
                    .setMaxResults(1)
                    .getSingleResult();
            return Result.ok(productId.longValue());
        } catch (Exception ex) {
            logger.error("Error getProductIdByLoLotId by loLotId = {}", loLotId, ex);
            return Result.error("Ошибка при получении идентификатора лота на витрине по loLotId = " + loLotId + ": " + getStackTrace(ex));
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Long, String> getProductIdByBkrLotId(Long bkrLotId) {
        if (bkrLotId == null) {
            return Result.error("Ошибка при получении идентификатора лота на витрине. bkrLotId = null");
        }
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerVitrina();
            Integer productId = (Integer) em.createNativeQuery("select p.product_id " +
                            "from cscart_products p " +
                            "where p.bkr_lot_id = :bkrLotId" +
                            "  and p.status = 'A'")
                    .setParameter("bkrLotId", bkrLotId)
                    .setMaxResults(1)
                    .getSingleResult();
            return Result.ok(productId.longValue());
        } catch (Exception ex) {
            logger.error("Error getProductIdByBkrLotId by bkrLotId = {}", bkrLotId, ex);
            return Result.error("Ошибка при получении идентификатора лота на витрине по bkrLotId = " + bkrLotId + ": " + getStackTrace(ex));
        } finally {
            closeEntityManager(em);
        }
    }


    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

}
