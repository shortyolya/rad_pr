/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author css
 */
@Entity
@Table(name = "RUBRICATOR_EIS", schema = "WEB")
@NamedQueries({
        @NamedQuery(name = "Rubricator.findAll", query = "SELECT v FROM Rubricator v"),
        @NamedQuery(name = "Rubricator.findBySingleClassifCode", query = "SELECT v FROM Rubricator v where v.rubrSingleClassifCode = :rubrSingleClassifCode")
})
public class Rubricator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RUBR_UNID")
    private Long rubrUnid;
    @Column(name = "RUB_RUBR_UNID")
    private Long rubRubrUnid;
    @Column(name = "OC_UNID")
    private Long ocUnid;
    @Column(name = "RUBR_CODE")
    private String rubrCode;
    @Column(name = "RUBR_NAME")
    private String rubrName;
    @Column(name = "RUBR_LEVEL")
    private Integer rubrLevel;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;
    @Column(name = "RUBR_SINGLE_CLASSIF_CODE")
    private String rubrSingleClassifCode;
    @Column(name = "RUBR_FULL_NAME")
    private String rubrFullName;
    @Column(name = "RUBR_ASV_CODE")
    private String rubrAsvCode;

    public Long getRubrUnid() {
        return rubrUnid;
    }

    public void setRubrUnid(Long rubrUnid) {
        this.rubrUnid = rubrUnid;
    }

    public Long getRubRubrUnid() {
        return rubRubrUnid;
    }

    public void setRubRubrUnid(Long rubRubrUnid) {
        this.rubRubrUnid = rubRubrUnid;
    }

    public String getRubrCode() {
        return rubrCode;
    }

    public void setRubrCode(String rubrCode) {
        this.rubrCode = rubrCode;
    }

    public String getRubrName() {
        return rubrName;
    }

    public void setRubrName(String rubrName) {
        this.rubrName = rubrName;
    }

    public Integer getRubrLevel() {
        return rubrLevel;
    }

    public void setRubrLevel(Integer rubrLevel) {
        this.rubrLevel = rubrLevel;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

    public Long getOcUnid() {
        return ocUnid;
    }

    public void setOcUnid(Long ocUnid) {
        this.ocUnid = ocUnid;
    }

    public String getRubrSingleClassifCode() {
        return rubrSingleClassifCode;
    }

    public void setRubrSingleClassifCode(String rubrSingleClassifCode) {
        this.rubrSingleClassifCode = rubrSingleClassifCode;
    }

    public String getRubrFullName() {
        return rubrFullName;
    }

    public void setRubrFullName(String rubrFullName) {
        this.rubrFullName = rubrFullName;
    }

    public String getRubrAsvCode() {
        return rubrAsvCode;
    }

    public void setRubrAsvCode(String rubrAsvCode) {
        this.rubrAsvCode = rubrAsvCode;
    }
}
