package com.baltinfo.radius.documents.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class Template {

    public final static String MODE_POI = "poi";
    public final static String MODE_DOCX4J = "docx4j";

    private String name;

    private List<SubReportInfo> subreports;

    private List<Map<String, String>> info;

    private Map<String, String> htmlCorrection;

    private InputStream is;

    private String mode = MODE_POI;

    private String imgFilePath;

    private List<List<Map<String, String>>> srInfo;

    private boolean isMultiple = false;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public void setSubreports(List<SubReportInfo> str) {
        this.subreports = str;
    }

    public List<Map<String, String>> getInfo() {
        return info;
    }

    public void setInfo(List<Map<String, String>> info) {
        this.info = info;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Map<String, String> getHtmlCorrection() {
        return htmlCorrection;
    }

    public void setHtmlCorrection(Map<String, String> htmlCorrection) {
        this.htmlCorrection = htmlCorrection;
    }

    public List<SubReportInfo> getSubreports() {
        if (subreports == null) {
            subreports = new ArrayList<SubReportInfo>();
        }
        return this.subreports;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public List<List<Map<String, String>>> getSrInfo() {
        return srInfo;
    }

    public void setSrInfo(List<List<Map<String, String>>> srInfo) {
        this.srInfo = srInfo;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Template=[");
        sb.append("name=").append(name);
        sb.append(", subreports=").append(subreports);
        sb.append("]");
        return sb.toString();
    }
}
