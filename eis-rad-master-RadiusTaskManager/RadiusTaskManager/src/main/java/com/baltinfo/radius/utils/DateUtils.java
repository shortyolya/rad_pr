package com.baltinfo.radius.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author sas
 * @since 04.10.2018
 */
public class DateUtils {

    public static Date truncate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)
                , 0
                , 0
                , 0);
        return calendar.getTime();
    }

    public static Date minusDay(Date date, int dayCount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -dayCount);
        return calendar.getTime();
    }

    public static Date plusDay(Date date, int dayCount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayCount);
        return calendar.getTime();
    }
}
