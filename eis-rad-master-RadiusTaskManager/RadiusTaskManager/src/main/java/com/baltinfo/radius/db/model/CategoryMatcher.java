package com.baltinfo.radius.db.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kulikov Semyon
 * @since 15.01.2020
 */

@Entity
@Table(name = "v_category_matcher", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CategoryMatcher.findByCategoryName", query = "SELECT c FROM CategoryMatcher c WHERE c.scName = :scName AND c.fcIndRent = 0"),
        @NamedQuery(name = "CategoryMatcher.findByMevUnid", query = "SELECT c FROM CategoryMatcher c WHERE c.mevUnid = :mevUnid AND c.fcIndRent = 0"),
        @NamedQuery(name = "CategoryMatcher.findByCategoryUnidAndMevUnid", query = "SELECT c FROM CategoryMatcher c WHERE c.scUnid = :scUnid AND c.mevUnid = :mevUnid AND c.fcIndRent = 0"),
        @NamedQuery(name = "CategoryMatcher.findAllCategories", query = "SELECT c FROM CategoryMatcher c")
})

public class CategoryMatcher {
    @Id
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "sc_unid")
    private Long scUnid;
    @Column(name = "sc_name")
    private String scName;
    @Column(name = "fc_code")
    private String fcCode;
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "fc_ind_rent")
    private Integer fcIndRent;

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getFcCode() {
        return fcCode;
    }

    public void setFcCode(String fcCode) {
        this.fcCode = fcCode;
    }

    public Integer getFcIndRent() {
        return fcIndRent;
    }

    public void setFcIndRent(Integer fcIndRent) {
        this.fcIndRent = fcIndRent;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public void setScUnid(Long scUnid) {
        this.scUnid = scUnid;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
    }
}
