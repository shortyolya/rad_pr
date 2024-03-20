package com.baltinfo.radius.db.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Роли и подразделения
 *
 * @author css
 */
@Entity
@Table(name = "type_part_agent", catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tpa_unid"})
})
@SequenceGenerator(name = "seq_type_part_agent", sequenceName = "seq_type_part_agent", allocationSize = 1)
@XmlRootElement
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.TypePartAgent.none", attributeNodes = {
    }),
    @NamedEntityGraph(name = "graph.TypePartAgent.all", attributeNodes = {
        @NamedAttributeNode("typTpaUnid"),
        @NamedAttributeNode("subUnid"),
        @NamedAttributeNode("subSubUnid")
    }),
    @NamedEntityGraph(name = "graph.TypePartAgent.typTpaUnid", attributeNodes = {
        @NamedAttributeNode("typTpaUnid")
    })
}) 
@NamedQueries({
    @NamedQuery(name = "TypePartAgent.findAll", query = "SELECT t FROM TypePartAgent t WHERE t.indActual = 1"),
    @NamedQuery(name = "TypePartAgent.findByTpaUnid", query = "SELECT t FROM TypePartAgent t WHERE t.indActual = 1 and tpaUnid = :tpaUnid"),
    @NamedQuery(name = "TypePartAgent.findByTypTpaUnid", query = "SELECT t FROM TypePartAgent t WHERE t.indActual = 1 and typTpaUnid = :typTpaUnid")
})
public class TypePartAgent {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_part_agent")
    @Basic(optional = false)
    @Column(name = "tpa_unid", nullable = false, precision = 2147483647, scale = 0)
    private long tpaUnid;
    @Column(name = "ind_actual")
    private Long indActual;
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
    @Column(name = "tpa_name", length = 250)
    private String tpaName;
    @Column(name = "tpa_sname", length = 50)
    private String tpaSname;
    @Column(name = "tpa_ind_service")
    private Long tpaIndService;
    @Column(name = "tpa_note", length = 500)
    private String tpaNote;
    @JoinColumn(name = "typ_tpa_unid", referencedColumnName = "tpa_unid")
    @Fetch(FetchMode.SELECT)
    @ManyToOne(fetch=FetchType.LAZY)
    private TypePartAgent typTpaUnid;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subSubUnid;
    @Column(name = "tpa_pref", length = 500)
    private String tpaPref;
    @Column(name = "tpa_schema", length = 50)
    private String tpaSchema;
    @Column(name = "tpa_city", length = 50)
    private String tpaCity;
    @Column(name = "tpa_ind_saler")
    private Boolean tpaIndSaler;
    @Column(name = "tpa_email")
    private String tpaEmail;

    public TypePartAgent() {
    }

    public TypePartAgent(long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Long getIndActual() {
        return indActual;
    }

    public void setIndActual(Long indActual) {
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

    public String getTpaName() {
        return tpaName;
    }

    public void setTpaName(String tpaName) {
        this.tpaName = tpaName;
    }

    public String getTpaSname() {
        return tpaSname;
    }

    public void setTpaSname(String tpaSname) {
        this.tpaSname = tpaSname;
    }

    public Long getTpaIndService() {
        return tpaIndService;
    }

    public void setTpaIndService(Long tpaIndService) {
        this.tpaIndService = tpaIndService;
    }

    public String getTpaNote() {
        return tpaNote;
    }

    public void setTpaNote(String tpaNote) {
        this.tpaNote = tpaNote;
    }

    public TypePartAgent getTypTpaUnid() {
        return typTpaUnid;
    }

    public void setTypTpaUnid(TypePartAgent typTpaUnid) {
        this.typTpaUnid = typTpaUnid;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Subject getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Subject subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public String getTpaPref() {
        return tpaPref;
    }

    public void setTpaPref(String tpaPref) {
        this.tpaPref = tpaPref;
    }

    public String getTpaSchema() {
        return tpaSchema;
    }

    public void setTpaSchema(String tpaSchema) {
        this.tpaSchema = tpaSchema;
    }

    public String getTpaCity() {
        return tpaCity;
    }

    public void setTpaCity(String tpaCity) {
        this.tpaCity = tpaCity;
    }

    public Boolean getTpaIndSaler() {
        return tpaIndSaler;
    }

    public void setTpaIndSaler(Boolean tpaIndSaler) {
        this.tpaIndSaler = tpaIndSaler;
    }

    public String getTpaEmail() {
        return tpaEmail;
    }

    public void setTpaEmail(String tpaEmail) {
        this.tpaEmail = tpaEmail;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypePartAgent)) {
            return false;
        }
        TypePartAgent other = (TypePartAgent) object;
        if (this.tpaUnid !=  other.tpaUnid) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypePartAgent[ tpaUnid=" + tpaUnid + " ]";
    }
    
}
