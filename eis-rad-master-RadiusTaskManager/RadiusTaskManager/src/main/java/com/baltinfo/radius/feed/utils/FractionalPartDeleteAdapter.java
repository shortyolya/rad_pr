package com.baltinfo.radius.feed.utils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FractionalPartDeleteAdapter extends XmlAdapter<String, Double> {

    public Double unmarshal(String value) {
        return DatatypeConverter.parseDouble(value);
    }

    public String marshal(Double value) {
        if (value == null) {
            return null;
        }
        return String.format("%.0f", value);
    }
}
