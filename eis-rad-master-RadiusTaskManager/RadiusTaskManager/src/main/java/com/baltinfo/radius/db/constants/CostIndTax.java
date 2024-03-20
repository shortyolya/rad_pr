package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 01.03.2019
 */
public enum CostIndTax {
    /**
     * цена без учета НДС
     */
    TAX_NOT_INCLUDED(0),
    /**
     * НДС включен
     */
    TAX_INCLUDED(1),
    /**
     * НДС не облагается
     */
    NO_TAX(2);

    public int getIndTax() {
        return indTax;
    }

    private final int indTax;

    CostIndTax(int indTax) {
        this.indTax = indTax;
    }



}
