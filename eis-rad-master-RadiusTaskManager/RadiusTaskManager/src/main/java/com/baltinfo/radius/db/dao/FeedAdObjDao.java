package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.FeedAdObj;
import com.baltinfo.radius.db.model.VFeedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */
public class FeedAdObjDao {
    private static final Logger logger = LoggerFactory.getLogger(FeedAdObjDao.class);

    private static final String SC_UNID_ERROR = "Отсутствует категория ДП";
    private static final String FC_UNID_ERROR = "Выгрузка для данной категории не предусмотрена";

    public List<FeedAdObj> createAdObj(EntityManager em, Long fadUnid, HashMap<Long, String> objUnidsWithErrors, List<Long> badObjUnidsList) {
        List<FeedAdObj> feedAdObjList = new ArrayList<>();
        for (Map.Entry<Long, String> entry : objUnidsWithErrors.entrySet()) {
            FeedAdObj feedAdObj = new FeedAdObj();
            feedAdObj.setFadUnid(fadUnid);
            feedAdObj.setObjUnid(entry.getKey());
            feedAdObj.setFaoErrorMsg(entry.getValue());
            if (badObjUnidsList.contains(entry.getKey())) {
                feedAdObj.setFaoIndSuccess(false);
            } else {
                feedAdObj.setFaoIndSuccess(true);
            }
            em.persist(feedAdObj);
            feedAdObjList.add(feedAdObj);
        }
        return feedAdObjList;
    }

    public List<FeedAdObj> createBadAdObj(EntityManager em, Long fadUnid, Set<Long> badObjUnidsList, String fatalError) {
        List<FeedAdObj> feedAdObjList = new ArrayList<>();
        for (Long objUnid : badObjUnidsList) {
            FeedAdObj feedAdObj = new FeedAdObj();
            feedAdObj.setFadUnid(fadUnid);
            feedAdObj.setObjUnid(objUnid);
            feedAdObj.setFaoIndSuccess(false);
            feedAdObj.setFaoErrorMsg(fatalError);
            em.persist(feedAdObj);
            feedAdObjList.add(feedAdObj);
        }
        return feedAdObjList;
    }

    public void createAdObjWithCategoryError(EntityManager em, Long fadUnid, VFeedObject vFeedObject) {
        FeedAdObj fao = new FeedAdObj();
        fao.setFadUnid(fadUnid);
        fao.setObjUnid(vFeedObject.getObjUnid());
        fao.setFaoIndSuccess(false);
        fao.setFaoErrorMsg(vFeedObject.getScUnid() == null ? SC_UNID_ERROR : FC_UNID_ERROR);
        em.persist(fao);
    }
}
