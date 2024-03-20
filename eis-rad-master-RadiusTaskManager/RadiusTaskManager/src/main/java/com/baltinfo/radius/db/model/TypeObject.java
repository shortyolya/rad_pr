package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author css
 */
@Entity
@Table(name = "type_object", catalog = "", schema = "web")
@NamedQueries({
        @NamedQuery(name = "TypeObject.findByToName", query = "SELECT t FROM TypeObject t " +
                "WHERE t.indActual = 1 and t.toIndMain = 1 and trim(lower(t.toName)) = trim(lower(:toName))")
})
@XmlRootElement
public class TypeObject implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "to_unid", nullable = false)
    private Long toUnid;
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
    @Column(name = "to_name", length = 2147483647)
    private String toName;
    @Column(name = "to_sname", length = 2147483647)
    private String toSname;
    @Column(name = "to_ind_main")
    private Integer toIndMain;

    public TypeObject() {
    }

    public Long getToUnid() {
        return toUnid;
    }

    public void setToUnid(Long toUnid) {
        this.toUnid = toUnid;
    }

    @Override
    public Integer getIndActual() {
        return indActual;
    }

    @Override
    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    @Override
    public Date getDateB() {
        return dateB;
    }

    @Override
    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    @Override
    public String getFoundB() {
        return foundB;
    }

    @Override
    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    @Override
    public Long getPersCodeB() {
        return persCodeB;
    }

    @Override
    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    @Override
    public Date getDateBRec() {
        return dateBRec;
    }

    @Override
    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    @Override
    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    @Override
    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToSname() {
        return toSname;
    }

    public void setToSname(String toSname) {
        this.toSname = toSname;
    }

    public Integer getToIndMain() {
        return toIndMain;
    }

    public void setToIndMain(Integer toIndMain) {
        this.toIndMain = toIndMain;
    }
}
