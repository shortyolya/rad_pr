package com.baltinfo.radius.db.dto;

import com.baltinfo.radius.db.constants.CalcPriceConstant;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * DTO для загрузки графика снижения цены к лотам по 5 алгоритму
 * </p>
 *
 * @author Lapenok Igor
 * @since 17.08.2018
 */
public class LoadRsDto {
    private final DateDto lrsChangePriceDate;
    private final DateDto lrsApplDate;
    private final BigDecimal lrsSumm;
    private final BigDecimal lrsDepositSumm;
    private final CalcPriceConstant calcPriceType;
    private final List<Long> lotsNumbers;

    public LoadRsDto(DateDto lrsChangePriceDate, DateDto lrsApplDate, BigDecimal lrsSumm, BigDecimal lrsDepositSumm, CalcPriceConstant calcPriceType, List<Long> lotsNumbers) {
        this.lrsChangePriceDate = lrsChangePriceDate;
        this.lrsApplDate = lrsApplDate;
        this.lrsSumm = lrsSumm;
        this.lrsDepositSumm = lrsDepositSumm;
        this.calcPriceType = calcPriceType;
        this.lotsNumbers = lotsNumbers;
    }

    public DateDto getLrsChangePriceDate() {
        return lrsChangePriceDate;
    }

    public DateDto getLrsApplDate() {
        return lrsApplDate;
    }

    public BigDecimal getLrsSumm() {
        return lrsSumm;
    }

    public BigDecimal getLrsDepositSumm() {
        return lrsDepositSumm;
    }

    public CalcPriceConstant getCalcPriceType() {
        return calcPriceType;
    }

    public List<Long> getLotsNumbers() {
        return lotsNumbers;
    }
}
