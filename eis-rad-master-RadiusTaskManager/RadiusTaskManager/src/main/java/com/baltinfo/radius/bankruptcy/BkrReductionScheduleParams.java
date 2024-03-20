package com.baltinfo.radius.bankruptcy;

import com.baltinfo.radius.db.model.ReductionSchedule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * @author Suvorina Aleksandra
 * @since 05.09.2018
 */
public class BkrReductionScheduleParams {

    private BigDecimal redSchedReductionValue;
    private BigDecimal redSchedAskPrice;
    private String redSchedDateB;
    private String redSchedDateE;
    private String redSchedDepDateE;
    private BigDecimal redSchedDepSum;
    private long redSchedUnid;

    public BkrReductionScheduleParams(ReductionSchedule reductionSchedule) {
        this.redSchedReductionValue = reductionSchedule.getRedSchedReductionValue();
        this.redSchedAskPrice = reductionSchedule.getRedSchedAskPrice();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.redSchedDateB = reductionSchedule.getRedSchedDateB() == null
                ? null
                : sdf.format(reductionSchedule.getRedSchedDateB());
        this.redSchedDateE = reductionSchedule.getRedSchedApplDateE() == null
                ? null
                : sdf.format(reductionSchedule.getRedSchedApplDateE());
        this.redSchedDepDateE = reductionSchedule.getRedSchedDepDateE() == null
                ? this.redSchedDateE
                : sdf.format(reductionSchedule.getRedSchedDepDateE());
        this.redSchedDepSum = reductionSchedule.getRedSchedDepSum();
        this.redSchedUnid = 0L;
    }

    public BigDecimal getRedSchedReductionValue() {
        return redSchedReductionValue;
    }

    public BigDecimal getRedSchedAskPrice() {
        return redSchedAskPrice;
    }

    public String getRedSchedDateB() {
        return redSchedDateB;
    }

    public String getRedSchedDateE() {
        return redSchedDateE;
    }

    public String getRedSchedDepDateE() {
        return redSchedDepDateE;
    }

    public BigDecimal getRedSchedDepSum() {
        return redSchedDepSum;
    }

    public long getRedSchedUnid() {
        return redSchedUnid;
    }
}
