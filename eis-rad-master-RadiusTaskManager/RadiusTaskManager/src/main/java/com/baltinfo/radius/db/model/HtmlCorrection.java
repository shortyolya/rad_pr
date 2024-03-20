/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author css
 */
@Entity
@Table(name = "html_correction", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hc_unid"})})
@XmlRootElement
public class HtmlCorrection {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "hc_unid", nullable = false, precision = 2147483647, scale = 0)
    private long hcUnid;
    @Column(name = "hc_from", length = 2147483647)
    private String hcFrom;
    @Column(name = "hc_to", length = 2147483647)
    private String hcTo;

    public HtmlCorrection() {
    }

    public long getHcUnid() {
        return hcUnid;
    }

    public void setHcUnid(long hcUnid) {
        this.hcUnid = hcUnid;
    }

    public String getHcFrom() {
        return hcFrom;
    }

    public void setHcFrom(String hcFrom) {
        this.hcFrom = hcFrom;
    }

    public String getHcTo() {
        return hcTo;
    }

    public void setHcTo(String hcTo) {
        this.hcTo = hcTo;
    }


    @Override
    public String toString() {
        return "com.baltinfo.model.model.HtmlCorrection[ hcUnid=" + hcUnid + " ]";
    }

}
