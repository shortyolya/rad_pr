package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.Reward;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 18.11.2020
 */
public class RewardDao extends AbstractDao {

    public Reward saveReward(EntityManager em, Reward reward) {
        if (reward.getRewardUnid() == null) {
            setNewHisory(new Date(), reward);
            em.persist(reward);
        } else {
            setRecHisory(new Date(), reward);
            em.merge(reward);
        }
        return reward;
    }

}
