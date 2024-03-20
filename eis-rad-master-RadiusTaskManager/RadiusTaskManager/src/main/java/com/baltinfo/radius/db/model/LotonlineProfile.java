/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Профиль lot-online
 *
 * @author sya
 */
@javax.persistence.Entity
@Table(name = "lotonline_profile", schema = "web", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"lp_unid"})})
@SequenceGenerator(name = "seq_lotonline_profile", sequenceName = "seq_lotonline_profile", allocationSize = 1)
@XmlRootElement
public class LotonlineProfile implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lotonline_profile")
    @Column(name = "lp_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long lpUnid;

    @Column(name = "lp_name")
    private String lpName;

    @Column(name = "lp_user_id")
    private Long lpUserId;

    @Column(name = "lp_profile_id")
    private Long lpProfileId;

    @Column(name = "lp_sys_type")
    private Long lpSysType;

    @Column(name = "lp_token_json")
    private String lpTokenJson;

    @Column(name = "lp_token_gis")
    private String lpTokenGis;

    public LotonlineProfile() {
    }

    public LotonlineProfile(Long lpUnid) {
        this.lpUnid = lpUnid;
    }

    public Long getLpUnid() {
        return lpUnid;
    }

    public void setLpUnid(Long lpUnid) {
        this.lpUnid = lpUnid;
    }

    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    public Long getLpUserId() {
        return lpUserId;
    }

    public void setLpUserId(Long lpUserId) {
        this.lpUserId = lpUserId;
    }

    public Long getLpProfileId() {
        return lpProfileId;
    }

    public void setLpProfileIdd(Long lpProfileId) {
        this.lpProfileId = lpProfileId;
    }

    public Long getLpSysType() {
        return lpSysType;
    }

    public void setLpSysType(Long lpSysType) {
        this.lpSysType = lpSysType;
    }

    public String getLpTokenJson() {
        return lpTokenJson;
    }

    public void setLpTokenJson(String lpTokenJson) {
        this.lpTokenJson = lpTokenJson;
    }

    public String getLpTokenGis() {
        return lpTokenGis;
    }

    public void setLpTokenGis(String lpTokenGis) {
        this.lpTokenGis = lpTokenGis;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LotonlineProfile[ lpUnid=" + lpUnid + " ]";
    }

}
