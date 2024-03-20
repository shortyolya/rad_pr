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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "part_agent_role", catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"par_unid"})})
@SequenceGenerator(name = "seq_part_agent_role", sequenceName = "seq_part_agent_role", allocationSize = 1)
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.PartAgentRole.none", attributeNodes = {
    }),
    @NamedEntityGraph(name = "graph.PartAgentRole.all", attributeNodes = {
        @NamedAttributeNode("tpaUnid"),
        @NamedAttributeNode("paUnid")
    }),
    @NamedEntityGraph(name = "graph.PartAgentRole.list", attributeNodes = {
        @NamedAttributeNode("tpaUnid")
    })
})
@XmlRootElement
public class PartAgentRole{
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part_agent_role")
    @Basic(optional = false)
    @Column(name = "par_unid", nullable = false, precision = 2147483647, scale = 0)
    private long parUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @JoinColumn(name = "pa_unid", referencedColumnName = "pa_unid", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private ParticipantAgent paUnid;
    
    public PartAgentRole() {
    }

    public PartAgentRole(long parUnid) {
        this.parUnid = parUnid;
    }

    public long getParUnid() {
        return parUnid;
    }

    public void setParUnid(long parUnid) {
        this.parUnid = parUnid;
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

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public ParticipantAgent getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(ParticipantAgent paUnid) {
        this.paUnid = paUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.PartAgentRole[ parUnid=" + parUnid + " ]";
    }
    
}
