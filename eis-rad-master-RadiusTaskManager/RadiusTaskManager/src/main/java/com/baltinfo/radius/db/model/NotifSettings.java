/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "notif_settings", catalog = "", schema = "web")
@XmlRootElement
@SequenceGenerator(name = "seq_notif_settings", sequenceName = "seq_notif_settings", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.NotifSettings.none", attributeNodes = {
        }),
        @NamedEntityGraph(name = "graph.NotifSettings.all", attributeNodes = {
                @NamedAttributeNode("subUnid"),
                @NamedAttributeNode("tevUnid"),
                @NamedAttributeNode("tpaUnid")
        })
})
@NamedQueries({
        @NamedQuery(name = "NotifSettings.findAll", query = "SELECT n FROM NotifSettings n where n.indActual = 1")})
public class NotifSettings {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notif_settings")
    @Column(name = "ns_unid")
    private Long nsUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b")
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "ns_obj_ratio")
    private Integer nsObjRatio;
    @Column(name = "ns_remind_term")
    private Integer nsRemindTerm;
    @Column(name = "ns_ind_remind_email")
    private Integer nsIndRemindEmail;
    @Column(name = "ns_ind_remind_system")
    private Integer nsIndRemindSystem;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "tev_unid", referencedColumnName = "tev_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeEvent tevUnid;
    @JoinColumn(name = "tpa_unid", referencedColumnName = "tpa_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypePartAgent tpaUnid;

    public NotifSettings() {
    }

    public NotifSettings(Long nsUnid) {
        this.nsUnid = nsUnid;
    }

    public Long getNsUnid() {
        return nsUnid;
    }

    public void setNsUnid(Long nsUnid) {
        this.nsUnid = nsUnid;
    }

    public Integer getIndActual() {
        return indActual;
    }

    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public String getFoundB() {
        return foundB;
    }

    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    public Long getPersCodeB() {
        return persCodeB;
    }

    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    public Date getDateBRec() {
        return dateBRec;
    }

    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public Integer getNsObjRatio() {
        return nsObjRatio;
    }

    public void setNsObjRatio(Integer nsObjRatio) {
        this.nsObjRatio = nsObjRatio;
    }

    public Integer getNsRemindTerm() {
        return nsRemindTerm;
    }

    public void setNsRemindTerm(Integer nsRemindTerm) {
        this.nsRemindTerm = nsRemindTerm;
    }

    public boolean getNsIndRemindEmail() {
        return nsIndRemindEmail == 0 ? false : true;
    }

    public void setNsIndRemindEmail(Integer nsIndRemindEmail) {
        this.nsIndRemindEmail = nsIndRemindEmail;
    }

    public void setNsIndRemindEmail(boolean nsIndRemindEmail) {
        this.nsIndRemindEmail = nsIndRemindEmail ? 1 : 0;
    }

    public boolean getNsIndRemindSystem() {
        return nsIndRemindSystem == 0 ? false : true;
    }

    public void setNsIndRemindSystem(Integer nsIndRemindSystem) {
        this.nsIndRemindSystem = nsIndRemindSystem;
    }

    public void setNsIndRemindSystem(boolean nsIndRemindSystem) {
        this.nsIndRemindSystem = nsIndRemindSystem ? 1 : 0;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public TypeEvent getTevUnid() {
        return tevUnid;
    }

    public void setTevUnid(TypeEvent tevUnid) {
        this.tevUnid = tevUnid;
    }

    public TypePartAgent getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(TypePartAgent tpaUnid) {
        this.tpaUnid = tpaUnid;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nsUnid != null ? nsUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotifSettings)) {
            return false;
        }
        NotifSettings other = (NotifSettings) object;
        if ((this.nsUnid == null && other.nsUnid != null) || (this.nsUnid != null && !this.nsUnid.equals(other.nsUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.NotifSettings[ nsUnid=" + nsUnid + " ]";
    }

    public String getUserName() {
        if (subUnid != null) {
            return subUnid.getSubName();
        } else if (tpaUnid != null) {
            return tpaUnid.getTpaName();
        }
        return "";
    }

    public String getNsRemindTermStr() {
        String digitStr = Math.abs(nsRemindTerm) + "";
        if (nsRemindTerm > 0) {
            digitStr = "через " + digitStr;
        } else {
            digitStr = "за " + digitStr;
        }
        if (digitStr.charAt(digitStr.length() - 1) == '1') {
            digitStr += " рабочий день";
        } else if (digitStr.charAt(digitStr.length() - 1) == '2'
                || digitStr.charAt(digitStr.length() - 1) == '3'
                || digitStr.charAt(digitStr.length() - 1) == '4') {
            digitStr += " рабочих дня";
        } else {
            digitStr += " рабочих дней";
        }
        return digitStr;
    }

}
