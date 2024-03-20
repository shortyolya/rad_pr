package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.dto.FeedObjectDto;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *     Dao для работы с ObjectJPA
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.02.2020
 */
public class ObjectJpaDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(ObjectJpaDao.class);

    private static final Long persCode = 1L;

    public Result<ObjectJPA, String> createObjectsFromAsset(EntityManager em, List<Asset> assetList) {
        try {
            Date now = new Date();
            ObjectJPA obj = new ObjectJPA();
            obj.setDateB(now);
            obj.setIndActual(1);
            obj.setDateBRec(now);
            obj.setPersCodeB(persCode);
            obj.setPersCodeBRec(persCode);
            obj.setEntityUnid(EntityConstant.OBJECT.getId());
            obj.setObjIndMain(1);
            em.persist(obj);
            return Result.ok(obj);
        } catch (Exception e) {
            logger.error("Can't create lot", e);
            return Result.error("Не удалось сохранить информацию о лоте в БД.");
        }
    }

    public Result<ObjSaleCategory, String> createObjSaleCategory(EntityManager em, ObjectJPA obj,
                                                                 SaleCategory saleCategory, boolean indMain) {
        if (saleCategory != null) {
            ObjSaleCategory objSaleCategory = new ObjSaleCategory();
            objSaleCategory.setObjUnid(obj.getObjUnid());
            objSaleCategory.setOscIndMain(indMain);
            objSaleCategory.setScUnid(saleCategory);
            setNewHisory(new Date(), objSaleCategory);
            em.persist(objSaleCategory);
            return Result.ok(objSaleCategory);
        } else {
            return Result.error("Sale category is null! Can't create objSaleCategory");
        }
    }

    public SaleCategory getSaleCategoryByUnid(EntityManager em, Long llSaleCategory) {
        return (SaleCategory) em.createNamedQuery("SaleCategory.findByUnid")
                .setParameter("scUnid", llSaleCategory)
                .getSingleResult();
    }

    public List<ObjSaleCategory> getObjSaleCategories(EntityManager em, Long objUnid) {
        return em.createNamedQuery("ObjSaleCategory.findByObjUnid")
                .setParameter("objUnid", objUnid)
                .getResultList();
    }

    public List<FeedObjectDto> getObjectsForFeed(EntityManager em) {
        Query resultList = em.createNativeQuery("select o.obj_Unid, o.ls_Unid,                                   " +
                "omeAvito.ome_Ind_Exists as to_avito,                            " +
                "omeCian.ome_Ind_Exists as to_cian,                              " +
                "omeJCat.ome_Ind_Exists as to_yandex                           " +
                " from web.object o                                              " +
                "  left join web.obj_marketing_events omeAvito                   " +
                "	on omeAvito.obj_unid = o.obj_unid                            " +
                "	and omeAvito.mev_unid = 2                                    " +
                "	and omeAvito.ind_actual = 1                                  " +
                "  left join web.obj_marketing_events omeCian                    " +
                "	on omeCian.obj_unid = o.obj_unid                             " +
                "	and omeCian.mev_unid = 4                                     " +
                "	and omeCian.ind_actual = 1                                   " +
                "  left join web.obj_marketing_events omeJCat                  " +
                "	on omeJCat.obj_unid = o.obj_unid                           " +
                "	and omeJCat.mev_unid = 6                                   " +
                "	and omeJCat.ind_actual = 1                                 " +
                "where o.ind_Actual = 1                                          " +
                "and o.obj_Ind_Main = 1                                          " +
                "and exists                                                      " +
                "(select ome.obj_unid from web.obj_marketing_events ome          " +
                "where ome.ind_Actual = 1                                        " +
                "and ome.obj_Unid = o.obj_Unid                                   " +
                "and ome.ome_Ind_Exists )                                        ");
        List<Object[]> result =  resultList.getResultList();
        return result.stream()
                .map(FeedObjectDto::new)
                .collect(Collectors.toList());
    }

    public FeedObjectDto getObjectForFeed(EntityManager em, Long objUnid) {
        Query resultList = em.createNativeQuery("select o.obj_Unid, o.ls_Unid,                                   " +
                "omeAvito.ome_Ind_Exists as to_avito,                            " +
                "omeCian.ome_Ind_Exists as to_cian,                              " +
                "omeJCat.ome_Ind_Exists as to_yandex                           " +
                " from web.object o                                              " +
                "  left join web.obj_marketing_events omeAvito                   " +
                "	on omeAvito.obj_unid = o.obj_unid                            " +
                "	and omeAvito.mev_unid = 2                                    " +
                "	and omeAvito.ind_actual = 1                                  " +
                "  left join web.obj_marketing_events omeCian                    " +
                "	on omeCian.obj_unid = o.obj_unid                             " +
                "	and omeCian.mev_unid = 4                                     " +
                "	and omeCian.ind_actual = 1                                   " +
                "  left join web.obj_marketing_events omeJCat                  " +
                "	on omeJCat.obj_unid = o.obj_unid                           " +
                "	and omeJCat.mev_unid = 6                                   " +
                "	and omeJCat.ind_actual = 1                                 " +
                "where o.ind_Actual = 1                                          " +
                "and o.obj_Ind_Main = 1                                          " +
                "and o.obj_unid = :objUnid                                       " +
                "and exists                                                      " +
                "(select ome.obj_unid from web.obj_marketing_events ome          " +
                "where ome.ind_Actual = 1                                        " +
                "and ome.obj_Unid = o.obj_Unid                                   " +
                "and ome.ome_Ind_Exists )                                        ")
                .setParameter("objUnid", objUnid);
        Object[] result =  (Object[]) resultList.getSingleResult();
        return new FeedObjectDto(result);
    }
}
