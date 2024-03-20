package com.baltinfo.radius.documents.generator;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class SubReportInfo {

    private String name;
    private List<Map<String, String>> info;
    private Short indList;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Map<String, String>> getInfo() {
        return info;
    }

    public void setInfo(List<Map<String, String>> info) {
        this.info = info;
    }

    public Short getIndList() {
        return indList;
    }

    public void setIndList(Short indList) {
        this.indList = indList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubReport=[");
        sb.append("name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}
