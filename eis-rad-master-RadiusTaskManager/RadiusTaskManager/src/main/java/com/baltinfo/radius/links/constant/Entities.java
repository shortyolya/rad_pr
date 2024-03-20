package com.baltinfo.radius.links.constant;

import java.io.Serializable;

public class Entities implements Serializable {

    public static final long OBJ = 1;
    public static final long AUCTION = 3;
    public static final long DEAL = 4;
    public static final long APPLICATION = 2;
    public static final long LOT = 5;
    public static final long ACT = 9;
    public static final long DIRECT_SALE = 10;
    public static final long ART_OBJECT = 11;
    public static final long SAFETY_RECEIPT = 12;

    public long getOBJ() {
        return OBJ;
    }

    public static long getAUCTION() {
        return AUCTION;
    }

    public static long getDEAL() {
        return DEAL;
    }

    public static long getAPPLICATION() {
        return APPLICATION;
    }

    public static long getLOT() {
        return LOT;
    }

    public static long getART_OBJECT() {
        return ART_OBJECT;
    }

    public static long getDIRECT_SALE() {
        return DIRECT_SALE;
    }

    public static long getSAFETY_RECEIPT() {
        return SAFETY_RECEIPT;
    }

}