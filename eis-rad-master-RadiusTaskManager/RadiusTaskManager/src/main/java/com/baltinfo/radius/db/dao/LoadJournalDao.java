package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.model.LoadJournal;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * ДАО для создания записей в журнал загрузки
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.02.2020
 */
public class LoadJournalDao extends AbstractDao {


    public LoadJournalDao() {
    }

    public LoadJournal saveLoadJournal(EntityManager em, LoadJournal loadJournal) {
        if (loadJournal.getLjUnid() == null) {
            setNewHisory(new Date(), loadJournal);
            em.persist(loadJournal);
        } else {
            setRecHisory(new Date(), loadJournal);
            em.merge(loadJournal);
        }
        return loadJournal;
    }

    public LoadJournal updateLoadJournalStatus(EntityManager em, LoadJournal loadJournal, String errorInfo, Long loadStatus) {
        Date now = new Date();
        loadJournal.setLstUnid(loadStatus);
        if (errorInfo != null && !errorInfo.isEmpty()) {
            String result = Stream.of(loadJournal.getLjResult(), errorInfo)
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.joining("\n\n"));
            loadJournal.setLjResult(result);
        }
        setRecHisory(now, loadJournal);
        em.merge(loadJournal);
        return loadJournal;
    }
}
