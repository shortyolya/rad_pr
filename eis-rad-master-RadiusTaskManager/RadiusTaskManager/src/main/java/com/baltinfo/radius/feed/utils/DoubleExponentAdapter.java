package com.baltinfo.radius.feed.utils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <p>
 * Вспомогательный класс для устранения экспоненты в дробных переменных при формировании xml
 * </p>
 *
 * @author Kulikov Semyon
 * @since 14.05.2020
 */

public class DoubleExponentAdapter extends XmlAdapter<String, Double> {

    public Double unmarshal(String value) {
        return DatatypeConverter.parseDouble(value);
    }

    public String marshal(Double value) {
        if (value == null) {
            return null;
        }
        return String.format("%.2f", value).replace(',', '.');
    }
}
